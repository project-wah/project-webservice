package com.project.wah.projectwebservice.web;


import com.project.wah.projectwebservice.config.auth.LoginUser;
import com.project.wah.projectwebservice.config.auth.dto.SessionUser;
import com.project.wah.projectwebservice.service.UsersService;
import com.project.wah.projectwebservice.web.dto.user.UsersResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UsersService usersService;

    //회원 정보 수정
    @GetMapping("/users/update")
    public String usersUpdate(Model model, @LoginUser SessionUser user){

        if(user != null) {
            model.addAttribute("useName", user.getName());
            model.addAttribute("userdName", user);
        }
        return "user-update";
    }

    //유저 상세 보기
    @GetMapping("/users/detail/read/{id}")
    public String userDetailRead(@PathVariable Long id, Model model, @LoginUser SessionUser user) {
        if(user != null) {
            model.addAttribute("useName", user.getName());
            model.addAttribute("userdName", user);
        }

        UsersResponseDto dto = usersService.findById(id);

        model.addAttribute("useDetail", dto);

        return "user-detailread";

    }
}
