package com.TestNice.servlet.basic;

import com.TestNice.servlet.entity.Kakao;
import com.TestNice.servlet.repository.OauthLoginRepository;
import com.TestNice.servlet.service.OAuthService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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

    @ResponseBody
    @GetMapping("/kakao")
    public ModelAndView kakaoCallback(@RequestParam String code) {

        ModelAndView mav = new ModelAndView();

        System.out.println("code = " + code);
        String token = oAuthService.getKakaoAccessToken(code);
        Kakao kakaoUser = oAuthService.createKakaoUser(token);
        Optional<Kakao> kakao = oAuthService.loginKakaoUser(kakaoUser);
        String email1 = kakaoUser.getKakao();
        Kakao kakaoLogin = kakao.get();
        String email2 = kakaoLogin.getKakao();
        String id = kakaoLogin.getId();

        Kakao kakaologinResult = new Kakao();

        if (email1.equals(email2)) {

            kakaologinResult.setId(id);

        } else {

            kakaologinResult.setId("error");

        }

        mav.setViewName("/loginResult.jsp");
        mav.addObject("loginResult", kakaologinResult);
        return mav;

    }

    @ResponseBody
    @GetMapping("/naver")
    public ModelAndView naverCallback(@RequestParam String code, @RequestParam String state) {

        System.out.println("naver code = " + code);
        System.out.println("naver state = " + state);
        ModelAndView mav = new ModelAndView();
        return mav;
    }
}
