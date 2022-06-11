package com.signinsignup.basic_signin_signup.files;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImp implements FileService{

    @Override
    public  HashMap<String,String> uploadImage(String path, MultipartFile file) throws IOException {
        String name = file.getOriginalFilename();
        String id = UUID.randomUUID().toString();
        String ext = name.substring(name.lastIndexOf('.'));
        String fileName = id + ext;
        
        String fullPath = path + File.separator +fileName;

        File f = new File(path);

        if(!f.exists()){
            f.mkdir();
        }
        Files.copy(file.getInputStream(), Paths.get(fullPath));

        HashMap<String,String> s =  new HashMap<>();
        s.put("name", name);
        s.put("url",fullPath);
        return s;
    }

    @Override
    public InputStream getRessource(String path, String fileName) throws FileNotFoundException {
        String fullPath = path + File.separator + fileName;
        return (InputStream) new FileInputStream(fullPath);
    }

}
