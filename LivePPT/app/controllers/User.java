package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class User extends Controller {
  
    public static Result index() {
        // return ok(index.render("MY new application is ready."));
        return TODO;
    }

    public static Result login() {
        // return ok(index.render("MY new application is ready."));
        System.out.println("Here is the POST login process!");
        System.out.println("Here is the POST login process!");
        System.out.println("Here is the POST login process!");
        System.out.println("Here is the POST login process!");
        System.out.println("Here is the POST login process!");
        return ok("TODO");
    }

    public static Result resign() {
        // return ok(index.render("MY new application is ready."));
        return TODO;
    }

    // public static Result test(String username) {
    //     // return ok(index.render("MY new application is ready."));
    //     System.out.println(username);
    //     return TODO;
    // }
  
}