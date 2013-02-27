package models;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

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
    
    
	// 上传文件
    public static String uploadFileService(String key, String filename)
            throws OSSException, ClientException, FileNotFoundException {
    	
    	
    	String bucketName = "liveppt";
    	ensureBucket(client,bucketName);
    	Bucket bucket = new Bucket(bucketName);
        File file = new File(filename);
        System.out.println(client.isBucketExist(bucketName));

        ObjectMetadata objectMeta = new ObjectMetadata();
        objectMeta.setContentLength(file.length());
        // 可以在metadata中标记文件类型
//        objectMeta.setContentType("image/jpeg");

        
        InputStream input = new FileInputStream(file);
        String result = client.putObject(bucketName, key, input, objectMeta).getETag();
        System.out.println(result);
        return result;
        
    }
}
