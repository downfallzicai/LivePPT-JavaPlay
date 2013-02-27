package controllers;

import java.io.File;
import java.io.FileNotFoundException;

import com.aliyun.openservices.ClientException;
import com.aliyun.openservices.oss.OSSException;

import models.PPTService;

import play.mvc.Controller;
import play.mvc.Result;

public class PPTAction extends Controller{
	public static Result uploadFile() {
		String key = "1/1.ppt";
		File file = new File("D:\\ex\\1.pptx");
		
		try {
			return ok(PPTService.uploadFileService(key, file.toString()));
		} catch (OSSException | ClientException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.toString());
			return ok(e.toString());
		}
		
	}

}
