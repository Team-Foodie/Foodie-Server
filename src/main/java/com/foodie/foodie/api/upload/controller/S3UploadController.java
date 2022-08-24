package com.foodie.foodie.api.upload.controller;

import com.foodie.foodie.api.upload.UploadService;
import com.foodie.foodie.api.upload.model.UploadResponse;
import com.foodie.foodie.common.model.RestResponseData;
import com.foodie.foodie.common.model.ResultCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/s3-upload")
public class S3UploadController {
    public final UploadService uploadService;

    @PostMapping("")
    public ResponseEntity<RestResponseData<UploadResponse>> uploadFile(@RequestParam("imageList") MultipartFile[] multipartFileList,
                                                                       @RequestParam(defaultValue = "false", value = "isThumbnail") boolean isThumbnail,
                                                                       @RequestParam(required = false) String fileSize) {

        UploadResponse uploadResponse = new UploadResponse();
        for(MultipartFile multipartFile : multipartFileList) {

            // 이미지 파일만 업로드 가능
            if(!StringUtils.hasText(multipartFile.getContentType()) || !multipartFile.getContentType().startsWith("image")){
                // 이미지가 아닌경우 403 Forbidden 반환
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }

            String url = uploadService.upload(multipartFile, multipartFile.getOriginalFilename(),
                    fileSize, isThumbnail);
            uploadResponse.addUrlToList(url, isThumbnail);
        }
        return new RestResponseData<>(ResultCode.SUCCESS, uploadResponse).buildResponseEntity(HttpStatus.CREATED);
    }
}
