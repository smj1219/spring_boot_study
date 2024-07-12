package com.example.boot11.service;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.example.boot11.dto.FileDto;
import com.example.boot11.repository.FileDao;

@Service
public class FileServiceImpl implements FileService {
	@Autowired private FileDao dao;
	@Value("${file.location}")
	private String fileLocation;
	

	@Override
	public void upload(FileDto dto) {
		MultipartFile myFile=dto.getMyFile();
		//원본 파일명
		String orgFileName=myFile.getOriginalFilename();
		//파일의 크기
		long fileSize=myFile.getSize();
		//저장할 파일의 이름을 Universal Unique 한 ID 로 저장하기 위해
		String saveFileName=UUID.randomUUID().toString();
		//저장할 파일의 전체 경로 구성하기 "C:\Users\ user\playground\ upload"+"\"+"saveFileName"
		//File.separator_운영체제에 따라 다른 파일 구분자를 입력해준다(window=> \ , 리눅스 => / )
		String filePath=fileLocation + File.separator + saveFileName;
		try {
			//업로드된 파일을 이동시킬 목적지 File 객체 생성
			File dest=new File(filePath);
			//MultipartFile 객체의 메소드를 통해서 실제로 이동시키기(전송하기)
			myFile.transferTo(dest);
		}catch(Exception e) {
			e.printStackTrace();
		}
		dao.insertFile(dto);
		
	}

	@Override
	public void removeFile(FileDto dto) {
		
		
	}


	@Override
	public List<FileDto> getAll() {
		
		return dao.getFileList();
	}

}
