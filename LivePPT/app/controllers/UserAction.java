package controllers;

import play.libs.Json;
import play.mvc.*;
import sun.misc.BASE64Encoder;


import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ObjectNode;

import models.UserAuthentication;
import models.mysql.UserTable;

public class UserAction extends Controller {



	public static Result index() {
		// return ok(index.render("MY new application is ready."));
		return TODO;
	}

	@BodyParser.Of(BodyParser.Json.class)
	public static Result login() {
		

		// final Map<String, String[]> values =
		// request().body().asFormUrlEncoded();
		// final String username = values.get("username")[0];
		// final String password = values.get("password")[0];
		JsonNode json = request().body().asJson();
		
		System.out.println("Here is the POST login process!");
		ObjectNode result = Json.newObject();
		if (json == null) {	
			
			result.put("status", "104		找不到参数");
			
		} else {
			
			result = UserAuthentication.login(json);
		}
		if (result.get("status").equals("ok")){
			return ok(result);
		}else 
			return badRequest(result);

	}

	public static Result register() {
		// return ok(index.render("MY new application is ready."));
		System.out.println("Here is the POST register process!");
		final Map<String, String[]> values = request().body()
				.asFormUrlEncoded();
		UserTable ut = new UserTable();
		ut.username = values.get("username")[0];
		ut.password = values.get("password")[0];
		try {
			ut.save();
			return ok("注册成功!");
		} catch (Exception e) {
			return ok("103			用户名已存在");
		}

	}

}