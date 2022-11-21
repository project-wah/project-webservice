package com.project.wah.projectwebservice.web;

import com.project.wah.projectwebservice.config.auth.LoginUser;
import com.project.wah.projectwebservice.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@RequiredArgsConstructor
@Controller
public class IndexController {

    //대문 페이지 생성
    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {

        if(user != null) {
            model.addAttribute("useName", user.getName());
            model.addAttribute("userdName", user);
        }
        return "index";
    }

}