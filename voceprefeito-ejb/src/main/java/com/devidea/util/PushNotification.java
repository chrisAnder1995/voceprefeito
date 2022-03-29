package com.devidea.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class PushNotification {
	public static void sendNotification(String title, String body, List<String> emails) {
		String ids = "";
				
		for (String email : emails) {
			if (!ids.equals("")) {
				ids += ",";
			}
			
			ids += "\"" + email + "\"";
		}
		
		sendNotificationToAPlatform(title,body,ids,0);
		sendNotificationToAPlatform(title,body,ids,1);
	}
	
	private static void sendNotificationToAPlatform(String title, String body, String emails, int platform) {		
		URL url;
		try {
			url = new URL("http://api.pushbots.com/3/push/transactional");
		
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestProperty("x-pushbots-appid", "5d274380b7941246be5ef373");
			con.setRequestProperty("x-pushbots-secret", "583070f4f8b92a6969778fad9ae1f06d");
			
			String urlParameters = "{" +
										"\"topic\": \"WO\","+
									    "\"message\": "+
									                "{"+
									                 "\"title\": \"" + title + "\","+
									                  "\"body\": \"" + body + "\""+
									                "}"+
									            ","+
									            "\"platform\": "+platform+","+
									            "\"recipients\": {"+
									                    "\"aliases\": [" + emails + "]"+
									                  "}"+
							        "}";
			
			// Send post request
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.close();

			int responseCode = con.getResponseCode();
			System.out.println(responseCode);

			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
