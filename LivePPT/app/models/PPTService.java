package models;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

import models.mysql.PPTTable;

import org.codehaus.jackson.node.ObjectNode;

import play.libs.Json;

import com.aliyun.openservices.ClientConfiguration;
import com.aliyun.openservices.ClientException;
import com.aliyun.openservices.oss.OSSClient;
import com.aliyun.openservices.oss.OSSException;
import com.aliyun.openservices.oss.model.ObjectMetadata;
import com.aliyun.openservices.oss.model.Bucket;

public class PPTService {
	private static String ACCESS_ID = StaticValue.ACCESS_ID;
	private static String ACCESS_KEY = StaticValue.ACCESS_KEY;
	private static final String OSS_ENDPOINT = "http://storage.aliyun.com/";
	// 可以使用ClientConfiguration对象设置代理服务器、最大重试次数等参数。
    private static ClientConfiguration config = new ClientConfiguration();
    private static OSSClient client = new OSSClient( ACCESS_ID, ACCESS_KEY);
    
 // 如果Bucket不存在，则创建它。
    private static void ensureBucket(OSSClient client, String bucketName)
            throws OSSException, ClientException{

        if (client.isBucketExist(bucketName)){
            return;
        }

        //创建bucket
        client.createBucket(bucketName);
    }
    
    
	// 上传文件至OSS
    public static ObjectNode uploadToOssService(String key, File file,ObjectNode result) {
    	
    	String bucketName = "liveppt";
    	ensureBucket(client,bucketName);
    	Bucket bucket = new Bucket(bucketName);
        System.out.println(client.isBucketExist(bucketName));

        ObjectMetadata objectMeta = new ObjectMetadata();
        objectMeta.setContentLength(file.length());
        // 可以在metadata中标记文件类型
//        objectMeta.setContentType("image/jpeg");

        
        InputStream input;
		try {
			input = new FileInputStream(file);
			String rt = client.putObject(bucketName, key, input, objectMeta).getETag();
	        result.put("status", "200");
	        result.put("status_message", "ok");
	        
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.put("status", "109");
	        result.put("status_message", e.toString());
		}
		return result;
    }
    
    public static ObjectNode updateSql(Map<String, String[]> values, File file){
    	ObjectNode result = Json.newObject();

    	PPTTable pt = new PPTTable();
    	pt.name = values.get("ppt_name")[0];
    	pt.owner_id = Long.parseLong(values.get("id")[0]);
    	try {
			pt.save();
			result.put("status", "200");
			result.put("status_message", "ok");
			result.put("ppt_id", pt.id);
		} catch (Exception e) {
			System.out.println(e);
			result.put("status", "109");
			result.put("status_message", e.toString());
		}
        return result;
    } 
    
    public static ObjectNode updateSqlConvert(Map<String, String[]> values){
    	ObjectNode result = Json.newObject();
    	long ppt_id = Long.parseLong(values.get("ppt_id")[0]);
    	long ppt_pages = Long.parseLong(values.get("ppt_pages")[0]);
    	
    	try {
    		PPTTable pt = PPTTable.find.byId(ppt_id);
    		pt.convert_status = (long) 1;
    		pt.ppt_pages = ppt_pages;
    		pt.save();
    		result.put("status", "200");
			result.put("status_message", "ok");
		} catch (Exception  e) {
			result.put("status", "110");
			result.put("status_message", "找不到ppt");
		}
    	
    	return result;
    }
    
    
}
