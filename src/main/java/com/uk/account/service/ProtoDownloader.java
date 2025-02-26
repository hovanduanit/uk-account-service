package com.uk.account.service;

import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Service
public class ProtoDownloader {

    private static final String PROTO_ZIP_URL = "http://localhost:3100/proto/all";
    private static final String LOCAL_PROTO_PATH = "src/main/proto/";
    private static final String LOCAL_ZIP_PATH = "src/main/resources/proto.zip";

    public void downloadAndExtractProtoFiles() throws Exception {
        downloadZipFile();
        unzipFile();
        Files.deleteIfExists(Paths.get(LOCAL_ZIP_PATH));
        System.out.println("✅ Proto files updated successfully!");
    }

    private void downloadZipFile() throws Exception {
        File zipFile = new File(ProtoDownloader.LOCAL_ZIP_PATH);
        zipFile.getParentFile().mkdirs();

        try (FileOutputStream out = new FileOutputStream(zipFile)) {
            FileCopyUtils.copy(new URL(ProtoDownloader.PROTO_ZIP_URL).openStream(), out);
        }

        System.out.println("Downloaded zip file: " + ProtoDownloader.LOCAL_ZIP_PATH);
    }

    private void unzipFile() throws Exception {
        File destFolder = new File(ProtoDownloader.LOCAL_PROTO_PATH);
        if (!destFolder.exists()) destFolder.mkdirs();

        try (ZipInputStream zipIn = new ZipInputStream(new FileInputStream(ProtoDownloader.LOCAL_ZIP_PATH))) {
            ZipEntry entry;
            while ((entry = zipIn.getNextEntry()) != null) {
                String filePath = Paths.get(ProtoDownloader.LOCAL_PROTO_PATH, entry.getName()).toString();

                if (!entry.isDirectory()) {
                    extractFile(zipIn, filePath);
                } else {
                    new File(filePath).mkdirs();
                }
                zipIn.closeEntry();
            }
        }
        System.out.println("Extracted proto files to: " + ProtoDownloader.LOCAL_PROTO_PATH);
    }

    private void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
        File file = new File(filePath);

        // Tạo thư mục cha nếu chưa tồn tại
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs(); // Tạo tất cả thư mục cha cần thiết
        }

        // Ghi nội dung file
        try (FileOutputStream fos = new FileOutputStream(file)) {
            byte[] buffer = new byte[4096];
            int len;
            while ((len = zipIn.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }
        }
    }
}
