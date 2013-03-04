package models;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.node.ObjectNode;


public class Client {

	
	
	public void postService(String url, ObjectNode json) throws Exception {
		
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);

		MultipartEntity reqEntity = new MultipartEntity();
		httpPost.setEntity(reqEntity);

		System.out.println("post ready" );
		System.out.println(json.toString());
		System.out.println(json.get("ppt_id").asText() );
		/** String param name */
		StringBody ppt_id = new StringBody(json.get("ppt_id").asText());
		System.out.println(json.get("ppt_id").asText() );
		reqEntity.addPart("ppt_id", ppt_id);
		
		System.out.println("executing: " + httpPost.getRequestLine());
		
		HttpResponse response = httpClient.execute(httpPost);
		HttpEntity responseEntity = response.getEntity();

		System.out.println("----------------------------------------");
		System.out.println(response.getStatusLine());

		if (responseEntity != null){
			System.out.println("Response content: "
					+ inputStream2String(responseEntity.getContent()));
		}

		httpClient.getConnectionManager().shutdown();
	}

	public static String inputStream2String(InputStream is) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int i = -1;
		while ((i = is.read()) != -1) {
			baos.write(i);
		}
		return baos.toString();
	}

}
