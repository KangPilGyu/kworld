package org.web.kworld.login.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.impl.GoogleTemplate;
import org.springframework.social.google.api.plus.Person;
import org.springframework.social.google.api.plus.PlusOperations;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Service;

@Service
public class GoogleLoginService {
	// 구글 로그인 서비스
	
    private GoogleConnectionFactory googleConnectionFactory;
    private OAuth2Parameters googleOAuth2Parameters;
    private AccessGrant accessGrant;
    private String accessToken;
    
    
    @Autowired
    public void setGoogleConnectionFactory(GoogleConnectionFactory googleConnectionFactory) {
		this.googleConnectionFactory = googleConnectionFactory;
	}
    @Autowired
	public void setGoogleOAuth2Parameters(OAuth2Parameters googleOAuth2Parameters) {
		this.googleOAuth2Parameters = googleOAuth2Parameters;
	}

	private OAuth2Operations oauthOperations;
    
    public String getGoogleUrl() {
    	// 로그인 버튼 url 만들기
    	oauthOperations = googleConnectionFactory.getOAuthOperations();
        return oauthOperations.buildAuthenticateUrl(GrantType.AUTHORIZATION_CODE, googleOAuth2Parameters);
    }
    
    public void makeAccessToken(String code) {
    	// 구글 서버 oath 토큰 받아오기...
    	 oauthOperations = googleConnectionFactory.getOAuthOperations();
         AccessGrant accessGrant = oauthOperations.exchangeForAccess(code, googleOAuth2Parameters.getRedirectUri(),
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
         
    }
    public void getUserProfile() {	
    	 // oauth 사용한 api 사용
        org.springframework.social.connect.Connection<Google> connection = googleConnectionFactory.createConnection(this.accessGrant);
        Google google = connection == null ? new GoogleTemplate(this.accessToken) : connection.getApi();
        
        PlusOperations plusOperations = google.plusOperations();
        Person profile = plusOperations.getGoogleProfile();
        System.out.println(profile.getAccountEmail());
        System.out.println(profile.getGender());
        System.out.println("User Uid : " + profile.getId());
        System.out.println("User Name : " + profile.getDisplayName());
        System.out.println("User Profile : " + profile.getImageUrl()); 
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
