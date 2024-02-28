package com.member.cozastore.service;

import com.member.cozastore.service.Imp.FileStorageServiceImp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileStorageService implements FileStorageServiceImp {
    @Value("${root.folder}")
    private String rootFolder;

    @Override
    public byte[] loadImage(String imageName) throws IOException {
        String imagePath = rootFolder + "\\" + imageName;
        return Files.readAllBytes(new File(imagePath).toPath());
    }


}
