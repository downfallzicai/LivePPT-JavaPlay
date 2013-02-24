package controllers;

import play.*;
import play.mvc.*;

import views.html.*;
import java.util.Map;


public class User extends Controller {
  
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

    public static Result resign() {
        // return ok(index.render("MY new application is ready."));
        return TODO;
    }

    public static Result test(String username) {
        // return ok(index.render("MY new application is ready."));
        System.out.println(username);
        return TODO;
    }
  
}