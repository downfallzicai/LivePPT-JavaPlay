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
			result.put("status", "102");
			result.put("statusMessage", "用户不存在");
		} else {
			try {
				List<UserTable> list = UserTable.find.where()
						.eq("username", username).findList();
				if (list.size() >= 1) {
					if (list.get(0).password.equals(password)) {
						result.put("status", "ok");
						long nt = new Date().getTime();
						String access_token;
						long expires_in;
						long id = list.get(0).id;
						UserTable ut = UserTable.find.byId(id);
						if (ut.access_token!=null&&ut.expires_in>nt){
							access_token = ut.access_token;
							expires_in = ut.expires_in;
						} else {
							access_token = EncoderByMd5(nt + username);
							expires_in = nt+259200000;		//有效期为3日
							ut.access_token = access_token;
							ut.expires_in = expires_in;
							ut.save();	
						}
						
						result.put("access_token", access_token);
						result.put("expires_in", expires_in);
						result.put("id", id);
						
					} else {
						result.put("status", "101");
						result.put("statusMessage", "密码错误 ");
					}
				} else {
					result.put("status", "102");
					result.put("statusMessage", "用户不存在");
				}
			} catch (Exception e) {
				result.put("status", "102");
				result.put("statusMessage", "用户不存在");
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
			result.put("status", "103");
			result.put("statusMessage", "用户名已存在");
		}
		return result;
	}
	
	public static ObjectNode modifyPwd(Map<String, String[]> values){
		long id = Long.parseLong(values.get("id")[0]);
		ObjectNode result = Json.newObject();
		UserTable ut = UserTable.find.byId(id);
		String access_token = values.get("access_token")[0];
		try {
			if (access_token.equals(ut.access_token)){
				if (ut.expires_in<new Date().getTime()){
					ut.password = values.get("password")[0];
					ut.save();
					result.put("status", "ok");
				} else {
					result.put("status", "106");
					result.put("statusMessage", "登陆令牌超出有效期");
				}
			} else {
				result.put("status", "105");
				result.put("statusMessage", "登陆令牌错误");
			}
		}catch (Exception e){
			result.put("status", "107");
			result.put("statusMessage", "新密码写入错误");
		}		
		return result;
	}
	
}
