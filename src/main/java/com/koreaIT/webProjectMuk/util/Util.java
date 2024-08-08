package com.koreaIT.webProjectMuk.util;

import java.security.MessageDigest;

public class Util {
	public static boolean isEmpty(String str) {
		return str == null || str.trim().length() == 0;
	}

	public static String jsReplace(String msg, String uri) {
		
		if (msg == null) {
			msg = "";
		}
		
		if (uri == null) {
			uri = "";
		}
		
		return String.format("""
					<script>
						const msg = '%s'.trim();
						
						if (msg.length > 0) {
							alert(msg);
						}
						
						location.replace('%s');
					</script>
				""" , msg, uri);
	}
	
	public static String jsHistoryBack(String msg) {
		
		if (msg == null) {
			msg = "";
		}
		
		return String.format("""
					<script>
						const msg = '%s'.trim();
						
						if (msg.length > 0) {
							alert(msg);
						}
						
						history.back();
					</script>
				""" , msg);
	}
	
    public static String getSHA256Hash(String originStr) {
        try {
            // SHA-256 알고리즘을 사용하는 MessageDigest 인스턴스 생성
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // 입력 문자열을 바이트 배열로 변환 후 해시 계산
            byte[] hash = digest.digest(originStr.getBytes("UTF-8"));

            // 바이트 배열을 16진수 문자열로 변환
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
	
}