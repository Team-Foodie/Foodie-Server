package com.foodie.foodie.infrastructure.s3;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.IOUtils;
import com.foodie.foodie.exception.BadFileExtensionException;
import com.foodie.foodie.exception.FileIsEmptyException;
import com.foodie.foodie.exception.ImageNotFoundException;
import com.foodie.foodie.global.property.aws.AwsS3Properties;
import lombok.RequiredArgsConstructor;
import org.imgscalr.Scalr;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class S3UtilService {

    private final AwsS3Properties awsS3Properties;

    private static final int EXP_TIME = 1000 * 60 * 2;

    private final AmazonS3Client amazonS3Client;

    public String upload(MultipartFile file) {
        String ext = verificationFile(file);

        String randomName = UUID.randomUUID().toString();
        String filename = randomName + "." + ext;

        BufferedImage outputImage = makeThumbnail(file);

        ByteArrayOutputStream os = new ByteArrayOutputStream();

        try {
            ImageIO.write(outputImage, "png", os);
        } catch (IOException e) {
            throw new ImageNotFoundException("Image Not Found");
        }

        InputStream is = new ByteArrayInputStream(os.toByteArray());


        amazonS3Client.putObject(new PutObjectRequest(awsS3Properties.getBucket(), awsS3Properties.getFoodieFolder() + filename, is, null).withCannedAcl(CannedAccessControlList.AuthenticatedRead));

        return filename;
    }

    public String generateObjectUrl(String fileName) {
        if (fileName == null) return null;
        Date expiration = new Date();
        expiration.setTime(expiration.getTime() + EXP_TIME);

        GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(awsS3Properties.getUrl(), fileName).withMethod(HttpMethod.GET).withExpiration(expiration);

        URL url = amazonS3Client.generatePresignedUrl(generatePresignedUrlRequest);

        return url.toString();
    }

    public byte[] getObject(String fileName) {
        try {
            S3Object object = amazonS3Client.getObject(awsS3Properties.getBucket(), fileName);
            return IOUtils.toByteArray(object.getObjectContent());
        } catch (RuntimeException | IOException e) {
            throw new ImageNotFoundException("Image Not Found");
        }
    }

    public void delete(String objectName) {
        amazonS3Client.deleteObject(awsS3Properties.getBucket(), "foodie/" + objectName);
    }

    private BufferedImage makeThumbnail(MultipartFile file) {
        BufferedImage srcImg;

        try {
            srcImg = ImageIO.read(file.getInputStream());
        } catch (IOException e) {
            throw new FileIsEmptyException("File is Empty");
        }

        int dw = 300;
        int dh = 400;

        int ow = srcImg.getWidth();
        int oh = srcImg.getHeight();

        int nw = ow;
        int nh = (ow * dh) / dw;

        if (nh > oh) {
            nw = (oh * dw) / dh;
            nh = oh;
        }

        BufferedImage cropImg = Scalr.crop(srcImg, (ow - nw) / 2, (oh - nh) / 2, nw, nh);

        return Scalr.resize(cropImg, dw, dh);
    }

    private String verificationFile(MultipartFile file) {
        if (file == null || file.isEmpty() || file.getOriginalFilename() == null)
            throw new FileIsEmptyException("File is Empty");
        String originalFilename = Objects.requireNonNull(file).getOriginalFilename();
        String ext = Objects.requireNonNull(originalFilename).substring(originalFilename.lastIndexOf(".") + 1);

        if (!(ext.equals("jpg") || ext.equals("HEIC") || ext.equals("jpeg") || ext.equals("png") || ext.equals("heir") ||
                ext.equals("gif") || ext.equals("bmp") || ext.equals("tiff") || ext.equals("mp4")))
            throw new BadFileExtensionException("Bad File Extensions");
        return ext;
    }
}
