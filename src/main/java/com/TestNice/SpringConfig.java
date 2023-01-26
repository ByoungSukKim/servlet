package com.TestNice;

import com.TestNice.servlet.repository.BssStationRepository;
import com.TestNice.servlet.repository.JDBCLoginRepository;
import com.TestNice.servlet.repository.LoginRepository;
import com.TestNice.servlet.repository.OauthLoginRepository;
import com.TestNice.servlet.service.LoginService;
import com.TestNice.servlet.service.OAuthService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final DataSource dataSource;

    public SpringConfig(DataSource dataSource) {

        this.dataSource = dataSource;

    }

    @Bean
    public LoginService loginService() {
        return new LoginService(JDBCLoginRepository());
    }


    @Bean
    public OAuthService oAuthService() {
        return new OAuthService(oauthLoginRepository());
    }




    @Bean
    public JDBCLoginRepository JDBCLoginRepository() {
        return new JDBCLoginRepository(dataSource);
    }

    @Bean
    public OauthLoginRepository oauthLoginRepository() {
        return new OauthLoginRepository(dataSource);
    }

    @Bean
    public BssStationRepository bssStationRepository() {
        return new BssStationRepository(dataSource);
    }

    @Bean
    MappingJackson2JsonView jsonView(){
        return new MappingJackson2JsonView();
    }




}
