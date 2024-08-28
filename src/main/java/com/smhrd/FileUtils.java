package com.smhrd;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class FileUtils {
	
	public static String fileUpload( MultipartHttpServletRequest request, String parameter, String dir ) {
		String fileName = "";
		
		MultipartFile file = request.getFile( parameter );
	    
		if( file != null ) {
			File dirStr = new File ( Constants.upload_path + File.separator + dir );
		    if( !dirStr.exists() ) {
		    	dirStr.mkdir();
		    }
		    
			fileName = UUID.randomUUID().toString() + file.getOriginalFilename();
			File saveFile = new File(dirStr, fileName);
		    try {
				byte[] fileByte = file.getBytes();
				file.transferTo(saveFile);
				FileOutputStream  os = null; 
				os.write(fileByte);
				os.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		} 
		return fileName;
	}
}
