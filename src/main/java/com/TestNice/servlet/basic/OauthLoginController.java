package com.TestNice.servlet.basic;

import com.TestNice.servlet.entity.Kakao;
import com.TestNice.servlet.entity.Naver;
import com.TestNice.servlet.repository.OauthLoginRepository;
import com.TestNice.servlet.service.OAuthService;
import com.TestNice.servlet.token.NaverOAuthToken;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.awt.*;
import java.util.Optional;


@RestController
/*@AllArgsConstructor*/
@RequestMapping("/login.html/oauth")
public class OauthLoginController {
    /**
     * 카카오 callback
     * [GET] /oauth/kakao/callback
     */

    private final OAuthService oAuthService;

    @Autowired
    public OauthLoginController(OAuthService oAuthService) {
        this.oAuthService = oAuthService;
    }

    @ApiOperation(value = "카카오 소셜 로그인", notes ="Aouth 2.0을 통한 카카오 로그인 구현")
    @ResponseBody
    @GetMapping("/kakao")
    public ModelAndView kakaoCallback(@RequestParam String code) {

        ModelAndView mav = new ModelAndView("jsonView");

        System.out.println("code = " + code);
        String token = oAuthService.getKakaoAccessToken(code);
        Kakao kakaoUser = oAuthService.createKakaoUser(token);
        Optional<Kakao> kakao = oAuthService.loginKakaoUser(kakaoUser);
        String email1 = kakaoUser.getKakao();
        Kakao kakaoLogin = kakao.get();
        String email2 = kakaoLogin.getKakao();
        String id = kakaoLogin.getId();
        System.out.println("id = " + id);
        System.out.println("email2 = " + email2);

        Kakao kakaologinResult = new Kakao();

        if (email1.equals(email2)) {

            kakaologinResult.setId(id);

        } else {

            kakaologinResult.setId("error");

        }
        Gson gson = new Gson();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", kakaologinResult.getId());

        mav.setViewName("/loginResult.jsp");
        mav.addObject("loginResult", jsonObject);
        System.out.println("Controller End ");
        return mav;

    }

    @ApiOperation(value = " 네이버 소셜 로그인 ", notes ="Aouth 2.0을 통한 네이버 로그인 구현")
    @ResponseBody
    @GetMapping("/naver")
    public ModelAndView naverCallback(@RequestParam String code, @RequestParam String state) throws JsonProcessingException {
        String token = oAuthService.getNaverAccessToken(code);
        Naver naverUser = oAuthService.createNaverUser(token);
        Optional<Naver> naver = oAuthService.loginNaverUser(naverUser);
        Naver naver1 = naver.get();
        String phone = naver1.getPhone();
        String id = naver1.getId();
        System.out.println("id = " + id);
        System.out.println("phone = " + phone);
        String UserphoneNum = naverUser.getPhone();
        UserphoneNum = UserphoneNum.replace("-","");

        Naver naverResult = new Naver();
        if (UserphoneNum.equals(phone)) {

            naverResult.setId(id);

        } else {

            naverResult.setId("error");

        }
        

        /*ModelAndView mav = new ModelAndView();*/
        ModelAndView mav = new ModelAndView("jsonView");
        Gson gson = new Gson();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", naverResult.getId());
        mav.setViewName("/loginResult.jsp");
        /*mav.addObject("loginResult", naverResult);*/
        mav.addObject("loginResult", jsonObject);
        return mav;
    }

}
