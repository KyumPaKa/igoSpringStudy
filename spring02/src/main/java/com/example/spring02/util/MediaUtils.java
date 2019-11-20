package com.example.spring02.util;

import java.util.Map;

import org.springframework.http.MediaType;

import java.util.HashMap;

public class MediaUtils {

	private static Map<String, MediaType> mediaMap;
	// 클래스를 로딩할 때 제일 먼저 실행되는 코드
	static {
		mediaMap = new HashMap<String, MediaType>();
		mediaMap.put("JPG", MediaType.IMAGE_JPEG);
		mediaMap.put("GIF", MediaType.IMAGE_GIF);
		mediaMap.put("PNG", MediaType.IMAGE_PNG);
	}
	
	public static MediaType getMediaType(String type) {
		return mediaMap.get(type.toUpperCase());
	}
	
}
