package cn.nostromo.cookietest.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/")
public class CookieTestController {

    @GetMapping("/")
    public String echo( HttpServletResponse response) {

        addCookie(response, "Vader", "Sith", "Strict");
        addCookie(response, "Skywalker", "Jedi", "Lax");
        addCookie(response, "Solo", "Smuggler", "None");

        return "Join the dark side, we have cookies!";
    }

    private void addCookie(HttpServletResponse response, String name, String value, String sameSite) {
        ResponseCookie none = ResponseCookie.from(name, value)
                .maxAge(3600)
                .domain("localhost")
                .sameSite(sameSite)
                .secure(true)
                .path("/")
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, none.toString());
    }


}

