package com.koreaIT.webProjectMuk.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class PhotoService {
	
	private RestTemplate restTemplate;

	@Value("${nhnCloud.appKey}")
	private String appKey;
	
	@Value("${nhnCloud.secretKey}")
	private String secretKey;
	
	public PhotoService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	public String createFolder() {
        // URL 설정 
		String folderPath = "/myfolder2";
        String url = "https://api-image.nhncloudservice.com/image/v2.0/appkeys/" + appKey + "/folders";

        // 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", secretKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        // 요청 데이터 설정
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("path", folderPath);

        // 요청 엔터티 생성
        HttpEntity<Map<String, String>> entity = new HttpEntity<>(requestBody, headers);

        // POST 요청 보내기
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        // 응답 처리
        System.out.println("성공");
        return response.getBody();
    }

}
