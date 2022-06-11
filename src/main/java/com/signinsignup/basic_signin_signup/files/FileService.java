package com.signinsignup.basic_signin_signup.files;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    HashMap<String,String> uploadImage(String path, MultipartFile file) throws IOException;
    InputStream getRessource(String path , String fileName)throws FileNotFoundException;
}
