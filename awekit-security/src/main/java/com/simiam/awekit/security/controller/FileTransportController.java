package com.simiam.awekit.security.controller;

import com.simiam.awekit.BaseConstants;
import com.simiam.awekit.api.ResponseBody;
import com.simiam.awekit.api.ResponseCode;
import com.simiam.awekit.security.service.UploadFileValidator;
import com.simiam.awekit.util.DateUtils;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>Title: FileTransportController</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.CMCC Co., Ltd. (c) 2020</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2020/1/4 3:04 下午</p>
 */
@RestController
@RequestMapping("/api")
public class FileTransportController extends AbstractSecurityController implements InitializingBean, ApplicationContextAware {
    private static final Logger logger = LoggerFactory.getLogger(FileTransportController.class);

    public static final String FILE_DIR = "/files";
    public static final String IMAGE_DIR = "/images";

    private ApplicationContext applicationContext;

    private List<UploadFileValidator> fileValidatorList = Lists.newArrayList();

    @RequestMapping(path = "/upload/image")
    public ResponseBody<Map<String, Object>> uploadImage(@RequestParam("image") MultipartFile file)
            throws IOException {
        ResponseBody<Map<String, Object>> responseBody = newResponseBody();
        storeFile(file, IMAGE_DIR, responseBody);
        return responseBody;
    }

    @RequestMapping(path = "/upload/file")
    public ResponseBody<Map<String, Object>> uploadFile(@RequestParam("file") MultipartFile file)
            throws IOException {
        ResponseBody<Map<String, Object>> responseBody = newResponseBody();
        Map<String, Object> dataMap = responseBody.getData();
        String saveFilePath = storeFile(file, FILE_DIR, responseBody);
        if (Strings.isNullOrEmpty(saveFilePath) || CollectionUtils.isEmpty(fileValidatorList)) {
            return responseBody;
        }
        try {
            for (UploadFileValidator uploadFileValidator : fileValidatorList) {
                if (!uploadFileValidator.needValidate()) {
                    continue;
                }
                Map<String, Object> validateResult = uploadFileValidator.validate(saveFilePath);
                if (CollectionUtils.isEmpty(validateResult)) {
                    continue;
                }
                dataMap.putAll(validateResult);
                if (validateResult.containsKey(BaseConstants.ERROR_MSG)) {
                    String errorMsg = (String) validateResult.get(BaseConstants.ERROR_MSG);
                    logger.error("The validator[{}] found invalid content: {}, so it does not need to store file!",
                            uploadFileValidator.getClass().getSimpleName(), validateResult);
                    responseBody.setCode(ResponseCode.CODE_BUSINESS_ERROR);
                    responseBody.setMessage(ResponseCode.MSG_BUSINESS_ERROR + "：" + errorMsg);
                    break;
                }
            }
        } finally {
            if (!responseBody.getCode().equals(ResponseCode.CODE_OK)) {
                Files.deleteIfExists(Paths.get(saveFilePath));
            }
        }
        return responseBody;
    }

    @PostMapping(path = {"/upload/delete"})
    public ResponseBody<Map<String, Object>> deleteUploadFile(String filePath)
            throws FileNotFoundException {
        ResponseBody<Map<String, Object>> responseBody = newResponseBody();
        if (Strings.isNullOrEmpty(filePath)) {
            return responseBody;
        }
        String deleteFilePath = ResourceUtils.getURL("file:." + filePath).getPath();
        deleteFilePath = StringUtils.cleanPath(new File(deleteFilePath).getAbsolutePath());
        File toDeleteFile = new File(deleteFilePath);
        if (toDeleteFile.exists()) {
            toDeleteFile.delete();
            logger.debug("Success to delete upload file: {}", deleteFilePath);
        }
        return responseBody;
    }

    @GetMapping(path = "/download")
    public ResponseEntity<Resource> downloadFile(String filePath)
            throws IOException {
        String downloadFilePath = ResourceUtils.getURL("file:./").getPath() + filePath;
        downloadFilePath = StringUtils.cleanPath(new File(downloadFilePath).getAbsolutePath());
        logger.debug("Download file: {}", downloadFilePath);
        File downloadFile = new File(downloadFilePath);
        if (!downloadFile.exists()) {
            throw new FileNotFoundException(ResponseCode.MSG_FILE_NOT_EXIST + filePath);
        }
        Resource resource = new UrlResource(downloadFile.toURI());
        if (!resource.exists()) {
            throw new FileNotFoundException(ResponseCode.MSG_FILE_NOT_EXIST + filePath);
        }
        String contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        if (Strings.isNullOrEmpty(contentType)) {
            contentType = "application/octet-stream";
        }
        String fileName = URLEncoder.encode(downloadFile.getName(), BaseConstants.UTF_8).replaceAll("\\+", "%20");
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .body(resource);
    }

    private String storeFile(MultipartFile file, String storeSubDir, ResponseBody<Map<String, Object>> responseBody)
            throws IOException {
        Map<String, Object> dataMap = responseBody.getData();
        String username = (String) dataMap.getOrDefault(BaseConstants.USERNAME, "");
        if (!Strings.isNullOrEmpty(username)) {
            username = "/" + username;
        }
        String uploadDir = ResourceUtils.getURL("file:./upload/").getPath();
        uploadDir = StringUtils.cleanPath(new File(uploadDir).getAbsolutePath());
        uploadDir = uploadDir + storeSubDir + username;
        logger.debug("Upload image dir: {}", uploadDir);
        File uploadDirFile = new File(uploadDir);
        String saveFilePath = null;
        if (uploadDirFile.exists() || uploadDirFile.mkdirs()) {
            String uploadFileName = file.getOriginalFilename();
            assert uploadFileName != null;
            String saveFileName = StringUtils.stripFilenameExtension(uploadFileName);
            String extName = StringUtils.getFilenameExtension(uploadFileName);
            saveFileName = saveFileName + "_" + DateFormatUtils.format(new Date(), DateUtils.yyyyMMddHHmmss) + "." + extName;
            File saveFile = new File(uploadDirFile, saveFileName);
            file.transferTo(saveFile);
            dataMap.put("path", "/upload" + storeSubDir + username + "/" + saveFileName);
            saveFilePath = saveFile.getAbsolutePath();
            logger.debug("Saved file path: {}", saveFilePath);
        } else {
            responseBody.setCode(ResponseCode.CODE_BUSINESS_ERROR);
            responseBody.setMessage(ResponseCode.MSG_FILE_NOT_EXIST + "：" + uploadDirFile.getAbsolutePath());
        }
        return saveFilePath;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (applicationContext != null) {
            Map<String, UploadFileValidator> map = applicationContext.getBeansOfType(UploadFileValidator.class);
            if (!CollectionUtils.isEmpty(map)) {
                this.fileValidatorList = Lists.newArrayList(map.values());
                this.fileValidatorList.sort(Comparator.comparingInt(UploadFileValidator::order));
            }
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
