package controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;

import org.codehaus.jackson.node.ObjectNode;

import com.aliyun.openservices.ClientException;
import com.aliyun.openservices.oss.OSSException;

import models.PPTService;
import models.mysql.UserTable;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

public class PPTAction extends Controller {
	public static Result upload() {
		final Map<String, String[]> values = request().body()
				.asFormUrlEncoded();
//		long id = Long.parseLong(values.get("id")[0]);
//		String access_token = values.get("access_token")[0];
		String fileName = values.get("ppt_file")[0];
		ObjectNode result = Json.newObject();
		
		System.out.println("upload");
//		try {
//			UserTable ut = UserTable.find.byId(id);
//			if (ut.access_token.equals(access_token)) {
//				if (ut.expires_in>new Date().getTime()){
//					File file = new File(fileName);
//					fileName = file.getName();
//					System.out.println(fileName);
//					String key = "1/1.ppt";
//					result = PPTService.uploadService(key, file.toString());
//				} else {
//					result.put("status", "108");
//					result.put("status_message", "登陆令牌超时");
//				}
//				
//			} else {
//				result.put("status", "101");
//				result.put("status_message", "密码错误");
//				
//			}
//
//		} catch (OSSException | ClientException | FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			result.put("status", "102");
//			System.out.println(e.toString());
//		} catch (Exception e) {
//			result.put("status", "102");
//		}
		
		File file = new File(fileName);
		fileName = file.getName();
		System.out.println(fileName);
		String key = "1/1.ppt";
		try {
			result = PPTService.uploadService(key, file.toString());
			System.out.println("上传成功");
		} catch (OSSException | ClientException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("上传错误");
			System.out.println(e);
			result.put("status","109");
			result.put("status_message","未登录错误");
		}
		
		return ok(result);
		
		
	}
	
}
