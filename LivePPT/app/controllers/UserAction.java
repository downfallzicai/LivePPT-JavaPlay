package controllers;

import play.*;
import play.mvc.*;
import sun.misc.BASE64Encoder;

import views.html.*;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import models.mysql.UserTable;


public class UserAction extends Controller {
	
	public static String EncoderByMd5(String str) throws NoSuchAlgorithmException, 
	UnsupportedEncodingException{
	        //确定计算方法
	        MessageDigest md5=MessageDigest.getInstance("MD5");
	        BASE64Encoder base64en = new BASE64Encoder();
	        //加密后的字符串
	        String newstr=base64en.encode(md5.digest(str.getBytes("utf-8")));
	        return newstr;
	}
  
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
            		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            		System.out.println(df.getCalendar().toString());
            		String hashkey = EncoderByMd5(df.getCalendar().toString()+username);
            		return ok(hashkey);
            	} else {
            		return ok("101		密码错误 ");
            	}
            }else {
            	return ok("102		用户不存在");
            }
        }catch (Exception e){
        	return ok("102		用户不存在");
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
        	return ok("103			用户名已存在");
        }
        
    }
  
}