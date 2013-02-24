package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

import java.util.List;
import java.util.Map;

import models.mysql.UserTable;


public class UserAction extends Controller {
  
    public static Result index() {
        // return ok(index.render("MY new application is ready."));
        return TODO;
    }

    public static Result login() {
        // return ok(index.render("MY new application is ready."));
        System.out.println("Here is the POST login process!");
        final Map<String, String[]> values = request().body().asFormUrlEncoded();
        final String username = values.get("username")[0];
        final String password = values.get("password")[0];
         
        try {
        	List<UserTable> list = UserTable.find.where().eq("username", username).findList();
        	if (list.size()>=1){
            	
            	if (list.get(0).password.equals(password)){
            		return ok("Welcome back!"+ username);
            	} else {
            		return ok("密码错误 !");
            	}
            }else {
            	return ok("用户名:"+username+"不存在.");
            }
        }catch (Exception e){
        	return ok("用户名:"+username+"不存在.");
        }
        
    }

    public static Result register() {
        // return ok(index.render("MY new application is ready."));
    	System.out.println("Here is the POST register process!");
    	final Map<String, String[]> values = request().body().asFormUrlEncoded();
        UserTable ut = new UserTable();
        ut.username = values.get("username")[0];
        ut.password = values.get("password")[0];
        try{
        	ut.save();
        	return ok("注册成功!");
        }catch (Exception e){
        	return ok("注册失败!"+e);
        }
        
    }
  
}