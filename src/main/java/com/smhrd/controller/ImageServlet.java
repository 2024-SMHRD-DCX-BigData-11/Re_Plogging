package com.smhrd.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/imageServlet")
public class ImageServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	byte[] decodedBytes = Base64.getDecoder().decode(request.getParameter("imageName"));
        String decodedStr = new String(decodedBytes);
    	
    	
    	
    	String imagePath = "C:/UploadImage/ai/ResultImg/" + decodedStr;
        File imageFile = new File(imagePath);

        if (imageFile.exists()) {
            response.setContentType("image/jpeg");
            FileInputStream fis = new FileInputStream(imageFile);
            OutputStream os = response.getOutputStream();

            byte[] buffer = new byte[1024];
            int count = 0;
            while ((count = fis.read(buffer)) != -1) {
                os.write(buffer, 0, count);
            }
            os.close();
            fis.close();
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
