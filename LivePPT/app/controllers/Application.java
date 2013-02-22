package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {
  
    public static Result index() {
    	System.out.println("Here is index page!");
        return ok(index.render());
    }

    public static Result help() {
        return ok(help.render("MY new application is ready."));
    }
  //   public static Result indext() {
		// return okrender();
  //   }
	public static Result tasks() {
	    return TODO;
	}
	  
	public static Result newTask() {
		return TODO;
	}
	  
  	public static Result deleteTask(Long id) {
		return TODO;
	}
  
}