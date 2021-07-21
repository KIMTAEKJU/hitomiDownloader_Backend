package com.hitomiDownlaoder.service.userInfo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Override
    public Map<String, Object> getUserInfo (String accessToken) {

        //    요청하는 클라이언트마다 가진 정보가 다를 수 있기에 HashMap타입으로 선언
        HashMap<String, Object> userInfo = new HashMap<String, Object>();
        String reqURL = UserInfoConstants.KAKAO_USERINFO_URL;
        Map<String, Object> resultMap = null;
        String nickname = null;
        String profileImage = null;
        String email = null;

        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            //    요청에 필요한 Header에 포함될 내용
            conn.setRequestProperty("Authorization", "Bearer " + accessToken);

            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);

            ObjectMapper mapper = new ObjectMapper();
            resultMap = mapper.readValue(result, new TypeReference<Map<String,Object>>() {});

            Map<String, Object> properties = (Map<String, Object>) resultMap.get("properties");
            Map<String, Object> kakaoAccount = (Map<String, Object>) resultMap.get("kakao_account");

            nickname = (String) properties.get("nickname");
            profileImage = (String) properties.get("profile_image");
            email = (String) kakaoAccount.get("email");

            userInfo.put("nickname", nickname);
            userInfo.put("email", email);
            userInfo.put("profile_image", profileImage);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return userInfo;
    }
}
