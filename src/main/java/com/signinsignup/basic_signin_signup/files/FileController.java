package com.signinsignup.basic_signin_signup.files;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController("files")
public class FileController {

    @Autowired
    private FileServiceImp fileService;

    @Value("${project.image}")
    private String path;
    
    @PostMapping("upload")
    public void uploadImage(@RequestParam("image") MultipartFile image,HttpServletResponse response) throws IOException{
        HashMap<String,String>  s = this.fileService.uploadImage(path, image);

        response.setContentType("application/json");
        new ObjectMapper().writeValue(response.getOutputStream(), s);

    }

    @GetMapping(value="/images/{imageName}",produces=MediaType.IMAGE_PNG_VALUE)
    public void downloadImage(
        @PathVariable("imageName") String imageName,
        HttpServletResponse response
        
        ) throws IOException{
            InputStream ressource = this.fileService.getRessource(path, imageName);
            response.setContentType(MediaType.IMAGE_PNG_VALUE);
            StreamUtils.copy(ressource, response.getOutputStream());

    }

   
    
}
