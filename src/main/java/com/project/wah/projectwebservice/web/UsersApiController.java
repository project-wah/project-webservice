package com.project.wah.projectwebservice.web;


import com.project.wah.projectwebservice.config.auth.LoginUser;
import com.project.wah.projectwebservice.config.auth.dto.SessionUser;
import com.project.wah.projectwebservice.service.UsersService;
import com.project.wah.projectwebservice.web.dto.UsersResponseDto;
import com.project.wah.projectwebservice.web.dto.UsersUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController
public class UsersApiController {

    private final UsersService usersService;

    //User 테이블의 일부 칼럼 수정(nickname, aboutme, mentoremail, githubeamil, blogaddress)
    @PutMapping("/api/v1/users")
    public int update(@LoginUser SessionUser sessionUser, @RequestBody UsersUpdateRequestDto requestDto, HttpSession httpSession){
        return usersService.update(sessionUser, requestDto, httpSession);
    }

    //User 테이블 개별 조회
    @GetMapping("/api/v1/users/{id}")
    public UsersResponseDto findById (@PathVariable int id){
        return usersService.findById(id);
    }

    //User 테이블 삭제
    @DeleteMapping("/api/v1/users")
    public int delete(@LoginUser SessionUser sessionUser, HttpSession httpSession) {
        return usersService.delete(sessionUser, httpSession);
    }



}
