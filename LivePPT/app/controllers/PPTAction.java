package controllers;

import java.io.File;
import java.util.Map;

import org.codehaus.jackson.node.ObjectNode;

import com.aliyun.openservices.ClientException;
import com.aliyun.openservices.oss.OSSException;

import models.Client;
import models.PPTService;
import models.UserAuthentication;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http.RequestBody;
import play.mvc.Result;

public class PPTAction extends Controller {
	public static Result upload() {
		System.out.println("upload");
		ObjectNode result = Json.newObject();
		RequestBody body = request().body();
		final Map<String, String[]> values = body.asFormUrlEncoded();
		String fileName;
		System.out.println(body);
		if (values==null||!values.containsKey("id")||!values.containsKey("access_token")||!values.containsKey("ppt_file"))	{
			result.put("status", "104");
			result.put("status_message", "缺少参数");
			System.out.println("no values");
			return badRequest(result);
		} else {
			fileName = values.get("ppt_file")[0];
		}
		System.out.println(values);
		result = UserAuthentication.authentication(values);
		if (!result.get("status").asText().equals("200")){
			return badRequest(result);
		}
		File file = new File(fileName);	
		result = PPTService.updateSql(values, file);
		if (!result.get("status").asText().equals("200")){
			return badRequest(result);
		}
		fileName = file.getName();
		System.out.println(fileName);
		String key = result.get("ppt_id")+"//"+result.get("ppt_id")+".ppt";
		try {
			result = PPTService.uploadToOssService(key, file);
			System.out.println("上传成功");
		} catch (OSSException | ClientException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("上传错误");
			System.out.println(e);
			result.put("status","109");
			result.put("status_message",e.toString());
		}
		if (!result.get("status").asText().equals("200")){
			return badRequest(result);
		}
		result = sendConvert(result);
		if (!result.get("status").asText().equals("200")){
			return badRequest(result);
		}		
		return ok(result);
	}
	
	public static Result convertStatus() {
		ObjectNode result =Json.newObject();
		Map<String, String[]> values = request().body()
				.asFormUrlEncoded();
		result = PPTService.updateSqlConvert(values);
		return ok(result);
	}
	
	public static ObjectNode sendConvert(ObjectNode result){
		Client cl = new Client();
		try {
			cl.postService("", result);
		} catch (Exception e){
			result.remove("status");
			result.remove("status_message");
			result.put("status","109");
			result.put("status_message", "转换失败");
		}
		return result;
	}

}
