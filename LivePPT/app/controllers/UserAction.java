package controllers;

import play.*;
import play.mvc.*;

import views.html.*;
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

        // return ok(index.render());
        // return ok("Hello! Fevers~");
        return ok("Hello "+username+", your password is "+password+". Is it right?");
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
        	return ok("Successful Save!");
        }catch (Exception e){
        	return ok("Save Error!The username was exist!");
        }
        
        
    }
  
}