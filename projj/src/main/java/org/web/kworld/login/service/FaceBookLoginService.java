package org.web.kworld.login.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.MissingAuthorizationException;
import org.springframework.social.connect.Connection;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Service;

@Service
public class FaceBookLoginService {
	// 페이스북 로그인 서비스
    private FacebookConnectionFactory faceBookconnectionFactory;
    private OAuth2Parameters facebookOAuth2Parameters;
    private OAuth2Operations oauthOperations;
    private AccessGrant accessGrant;
    private String accessToken;

    @Autowired
    public void setFaceBookconnectionFactory(FacebookConnectionFactory faceBookconnectionFactory) {
		this.faceBookconnectionFactory = faceBookconnectionFactory;
	}
    @Autowired
	public void setFacebookOAuth2Parameters(OAuth2Parameters facebookOAuth2Parameters) {
		this.facebookOAuth2Parameters = facebookOAuth2Parameters;
	}

	public String getFaceBookUrl() {
    	// 로그인 버튼 url 만들기
    	oauthOperations = faceBookconnectionFactory.getOAuthOperations();
        return oauthOperations.buildAuthenticateUrl(GrantType.AUTHORIZATION_CODE, facebookOAuth2Parameters);
    }
    
    public void makeAccessToken(String code) {
    	// 구글 서버 oath 토큰 받아오기...
    	 oauthOperations = faceBookconnectionFactory.getOAuthOperations();
         AccessGrant accessGrant = oauthOperations.exchangeForAccess(code, facebookOAuth2Parameters.getRedirectUri(),
                 null);
  
         String accessToken = accessGrant.getAccessToken();
         Long expireTime = accessGrant.getExpireTime();
  
         //토큰 refresh 작업
         if (expireTime != null && expireTime < System.currentTimeMillis()) {
             accessToken = accessGrant.getRefreshToken();
             System.out.printf("accessToken is expired. refresh token = {}", accessToken);
         }
         this.accessGrant=accessGrant;
         this.accessToken=accessToken;
         System.out.println(this.accessToken);
         
    }
    public void getUserProfile() {	
    	 // oauth 사용한 api 사용
    	Connection<Facebook> connection =  faceBookconnectionFactory.createConnection(this.accessGrant);
    	Facebook facebook = connection == null ? new FacebookTemplate(accessToken) : connection.getApi();
    	
    	//UserOperations userOperations = facebook.userOperations();
        try

        {            
            String [] fields = { "id", "email",  "name"};
            User userProfile = facebook.fetchObject("me", User.class, fields);
            System.out.println("유저이메일 : " + userProfile.getEmail());
            System.out.println("유저 id : " + userProfile.getId());
            System.out.println("유저 name : " + userProfile.getName());
            
        } catch (MissingAuthorizationException e) {
            e.printStackTrace();
        }

   }
    
    public void closeToken() {
   	 try {
            System.out.println("Closing Token....");
            String revokeUrl = "https://accounts.google.com/o/oauth2/revoke?token=" + this.accessToken + "";
            URL url = new URL(revokeUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoOutput(true);
 
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
   }
    
    
}
