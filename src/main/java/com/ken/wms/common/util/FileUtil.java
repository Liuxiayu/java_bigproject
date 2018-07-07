package com.ken.wms.common.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class FileUtil {

    /**
     * 灏� org.springframework.web.multipart.MultipartFile 绫诲瀷鐨勬枃浠惰浆鎹负 java.io.File 绫诲瀷鐨勬枃浠�
     *
     * @param multipartFile org.springframework.web.multipart.MultipartFile 绫诲瀷鐨勬枃浠�
     * @return 杩斿洖杞崲鍚庣殑 java.io.File 绫诲瀷鐨勬枃浠�
     * @throws IOException IOException
     */
    public static File convertMultipartFileToFile(MultipartFile multipartFile) throws IOException {
        File convertedFile = new File(multipartFile.getOriginalFilename());
        multipartFile.transferTo(convertedFile);
        return convertedFile;
    }
}
