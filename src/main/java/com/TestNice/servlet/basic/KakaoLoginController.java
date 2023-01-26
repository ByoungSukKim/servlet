package com.TestNice.servlet.basic;

import com.TestNice.servlet.entity.Login;
import com.TestNice.servlet.eventClass.LoginEvent;
import com.TestNice.servlet.eventPublisher.EventLoginService;
import com.TestNice.servlet.service.LoginService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class KakaoLoginController {

    private final LoginService loginService;
    private final EventLoginService eventLoginService;

    @Autowired
    public KakaoLoginController(LoginService loginService, EventLoginService eventLoginService) {
        this.loginService = loginService;
        this.eventLoginService = eventLoginService;
    }
    /*@GetMapping(value = "/oauth/kakao")
    public ModelAndView loginForKakao () {
        ModelAndView mav = new ModelAndView();
        return mav;
    }
*/
    @PostMapping(value = "/login/Account")
    public ModelAndView loginAccount (@RequestParam(value = "id") String id, @RequestParam(value = "password") String password) {
        System.out.println("id"+  id);
        System.out.println("pw"+  password);

        //ModelAndView mav = new ModelAndView();
        ModelAndView mav = new ModelAndView("jsonView");

        //파라미터 값으로 넘겨줄 엔티티 세팅
        /*Login login0 = new Login();
        login0.setId(id);
        login0.setPassword(password);*/

        LoginEvent loginEvent = new LoginEvent(this);
        loginEvent.setId(id);
        loginEvent.setId(password);


        /*Optional<Login> loginList = Optional.empty();*/
        Optional<LoginEvent> loginList = Optional.empty();
        loginList = Optional.ofNullable(eventLoginService.publishLoginEvent(id, password));

        //비밀 번호 체크

        //DB에서 패스워드 가져오기
        LoginEvent login1 = loginList.get();
        String dbPassword= login1.getPw();

        System.out.println("dbPassword = " + dbPassword);

        //화면에서 패스워드 가져오기
        String screenPassword = password;
        /*Login loginResult = new Login();*/
        LoginEvent loginResult = new LoginEvent(this);

        if (dbPassword.equals(screenPassword)) {

            loginResult.setId(id);
            System.out.println(" 비교 완료");

        } else {

            loginResult.setId("error");

        }

        Gson gson = new Gson();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", loginResult.getId());

        mav.setViewName("/loginResult.jsp");
        mav.addObject("loginResult", jsonObject);
        return mav;
    }

}
