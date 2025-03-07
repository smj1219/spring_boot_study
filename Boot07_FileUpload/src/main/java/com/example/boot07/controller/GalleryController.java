package com.example.boot07.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class GalleryController {
	/*
	 *  custom.properties 파일에 있는 파일 업로드
	 *  경로를 읽어와서 필드에 담는다
	 */
	@Value("${file.location}")
	private String fileLocation;
	
	@ResponseBody
	// 여러개의 값을 전달할 수 있다. , 로 구분한다.
	@GetMapping(
		value="/gallery/images/{imageName}",
		produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE, 
				MediaType.IMAGE_GIF_VALUE}	
	)
	//@GetMapping("/gallery/images/{imageName}")_위와 동일한 동작을 하지만 1가지의 벨류만 전달할 수 있다.
	public byte[] image(@PathVariable("imageName") String name) throws IOException {
		// imageName 경로 변수에 들어 있는 문자열이 매개변수 String name 에 전달된다.
		//읽어들일 파일의 절대 경로 
		String absolutePath=fileLocation + File.separator + name;
		// 파일에서 읽어들일 InputStream 
		InputStream is=new FileInputStream(absolutePath);
		
		return IOUtils.toByteArray(is);
	}
	
	@GetMapping("/gallery/uploadform")
	public String uploadForm() {
		return "gallery/uploadform";
	}
	
	/*
	 *  <input type="file" name="image">  의 name 속성의 value 가 image 이기 때문에
	 *  
	 *  MultipartFile  image  로 매개 변수를 선언하면 된다. 
	 */
	@PostMapping("/gallery/upload")
	public String upload(MultipartFile image, Model m) {
		//저장할 파일의 이름 겹치지 않는 유일한 문자열로 얻어내기
		String saveFileName=UUID.randomUUID().toString();
		//저장할 파일의 전체 경로 구성하기
		String filePath=fileLocation+File.separator+saveFileName;
		try {
			//업로드된 파일을 이동시킬 목적지 File 객체
			File f=new File(filePath);
			//MultipartFile 객체의 메소드를 통해서 실제로 이동시키기(전송하기)
			image.transferTo(f);
		}catch(Exception e) {
			e.printStackTrace();
		}
		m.addAttribute("saveFileName", saveFileName);
		return "gallery/upload";
	}
	
}








