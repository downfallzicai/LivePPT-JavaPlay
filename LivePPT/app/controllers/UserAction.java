package controllers;

import play.libs.Json;
import play.mvc.*;


import java.util.Map;

import org.codehaus.jackson.node.ObjectNode;

import models.UserAuthentication;

public class UserAction extends Controller {



	public static Result index() {
		// return ok(index.render("MY new application is ready."));
		return TODO;
	}

	
	public static Result login() {
		
		final Map<String, String[]> values =
		request().body().asFormUrlEncoded();
		ObjectNode result = Json.newObject();
		if (values == null) {	
			result.put("status", "104		找不到参数");
		} else {
			result = UserAuthentication.login(values);
		}
		;
		if (result.get("status").asText().equals("ok")){
			return ok(result);
		}else 
			return badRequest(result);

	}

	public static Result register() {
		final Map<String, String[]> values = request().body()
				.asFormUrlEncoded();
		ObjectNode result = Json.newObject();
		result = UserAuthentication.register(values);
		if (result.get("status").asText().equals("ok")){
			return ok(result);
		}else 
			return badRequest(result);
	}

}