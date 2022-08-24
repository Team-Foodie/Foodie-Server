package com.foodie.foodie.api.upload;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UploadService {
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.s3.dir}")
    private String dir;

    private final AmazonS3Client s3Client;

    public String upload(MultipartFile multipartFile, String originFileName, String fileSize, boolean isThumbnail) {

        try {
            String fileName = originFileName.substring(originFileName.lastIndexOf("\\") + 1);

            // 날짜 폴더 생성
            String folderPath = makeFolder();

            //UUID
            String uuid = UUID.randomUUID().toString();

            //저장할 파일 이름
            String saveName = dir + File.separator + folderPath + File.separator + uuid + fileName;

            Path savePath = Paths.get(saveName);

            multipartFile.transferTo(savePath);// 실제 이미지 저장

            ObjectMetadata objMeta = new ObjectMetadata();
            if (StringUtils.hasText(fileSize)) {
                objMeta.setContentLength(Long.parseLong(fileSize));
            }

            if (isThumbnail) {
                //섬네일 생성 -> 섬네일 파일 이름은 _thumb_ 이 붙음
                String thumbnailSaveName = dir + File.separator + folderPath + File.separator + uuid +"_thumb_"+ fileName;

                File thumbnailFile = new File(thumbnailSaveName);
                // 섬네일 생성
                Thumbnailator.createThumbnail(savePath.toFile(), thumbnailFile, 150, 150);

                s3Client.putObject(bucket, thumbnailSaveName, new FileInputStream(thumbnailFile), objMeta);
                return s3Client.getUrl(bucket, thumbnailSaveName).toString();
            }
            s3Client.putObject(bucket, saveName, multipartFile.getInputStream(), objMeta);
            return s3Client.getUrl(bucket, saveName).toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String makeFolder() {

        String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));

        String folderPath = str.replace("/", File.separator);

        // make folder ----
        File uploadPathFolder = new File(dir,folderPath);

        if(!uploadPathFolder.exists()){
            uploadPathFolder.mkdirs();
        }

        return folderPath;
    }

}
