package models;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import models.mysql.UserTable;

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
	
	public static ObjectNode login(Map<String, String[]> values){
		ObjectNode result = Json.newObject();
		final String username = values.get("username")[0];
		final String password = values.get("password")[0];
		if (username == null) {
			result.put("status", "102		用户不存在");
		} else {
			try {
				List<UserTable> list = UserTable.find.where()
						.eq("username", username).findList();
				if (list.size() >= 1) {
					if (list.get(0).password.equals(password)) {
						long nt = new Date().getTime();						
						String hashkey = EncoderByMd5(nt + username);
						result.put("status", "ok");
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
	
	public static ObjectNode register(Map<String, String[]> values){
		UserTable ut = new UserTable();
		ObjectNode result = Json.newObject();
		ut.username = values.get("username")[0];
		ut.password = values.get("password")[0];
		try {
			ut.save();
			result = login(values);
		} catch (Exception e) {
			result.put("status", "103		用户名已存在");
		}
		return result;
	}

}
