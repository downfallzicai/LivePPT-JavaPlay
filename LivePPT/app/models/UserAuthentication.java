package models;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

import models.mysql.UserTable;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ObjectNode;

import play.libs.Json;
import sun.misc.BASE64Encoder;

public class UserAuthentication {
	
	public static String EncoderByMd5(String str)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		// 确定计算方法
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		BASE64Encoder base64en = new BASE64Encoder();
		// 加密后的字符串
		String newstr = base64en.encode(md5.digest(str.getBytes("utf-8")));
		return newstr;
	}
	
	public static ObjectNode login(JsonNode json){
		ObjectNode result = Json.newObject();
		String username = json.findPath("username").getTextValue();
		String password = json.findPath("password").getTextValue();
		if (username == null) {
			result.put("status", "102		用户不存在");
		} else {
			try {
				List<UserTable> list = UserTable.find.where()
						.eq("username", username).findList();
				if (list.size() >= 1) {

					if (list.get(0).password.equals(password)) {
						long nt = new Date().getTime();
						System.out.println(nt);
						
						String hashkey = EncoderByMd5(nt + username);
						result.put("status", "OK");
						result.put("access_token", hashkey);
				
					} else {
						result.put("status", "101		密码错误 ");
					}
				} else {
					result.put("status", "102		用户不存在");
				}
			} catch (Exception e) {
				result.put("status", "102		用户不存在");
			}
		}
		
		
		return result;
		
	}

}
