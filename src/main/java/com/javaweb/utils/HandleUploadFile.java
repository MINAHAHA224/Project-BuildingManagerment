package com.javaweb.utils;

import com.javaweb.constant.SystemConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

@Component
public class HandleUploadFile {

    @Autowired
    private ServletContext   servletContext;
    public String toHandleUploadFile(MultipartFile file, String targetFolder) {

        String defaultImageHomePath  = servletContext.getInitParameter("PathSaveImage") + "/HouseDefault.jpg";
        String defaultImagePathCheck = servletContext.getInitParameter("PathSaveImage") + "/";
        String currentImageCheck =  servletContext.getInitParameter("PathSaveImage") + "/" +targetFolder +"/"+ file.getOriginalFilename();
        String rootPath = servletContext.getInitParameter("PathSaveImage");
        String finalName = "";
        byte[] bytes;
        try {
         //   if ( file ==  null || file.isEmpty() )
            if ( file.getOriginalFilename().isEmpty()){
                File imageHouseDefault =  new File(defaultImageHomePath);
                if (!imageHouseDefault.exists()){
                    return  "";
                }
                bytes = Files.readAllBytes(imageHouseDefault.toPath());

            }else {
                File imageCheck =  new File(currentImageCheck);
                if (imageCheck.exists()){

                    return  file.getOriginalFilename();

                }else {

                    bytes = file.getBytes();
                }

            }


            String testRootPath = rootPath + File.separator + targetFolder;
            File dir = new File(rootPath + File.separator + targetFolder);
            if (!dir.exists())
                dir.mkdirs();
            // Create the file on server


          //  finalName = System.currentTimeMillis() + "-" + (file!=null && !file.isEmpty() ? file.getOriginalFilename() : "HouseDefault.jpg");
            finalName = System.currentTimeMillis() + "-" + (!file.getOriginalFilename().isEmpty()? file.getOriginalFilename() : "HouseDefault.jpg");
            File serverFile = new File(dir.getAbsolutePath() + File.separator + finalName);


            BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(serverFile));
            stream.write(bytes);
            stream.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return finalName;
    }
}
