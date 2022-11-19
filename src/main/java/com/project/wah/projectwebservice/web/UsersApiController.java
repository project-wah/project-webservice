package com.project.wah.projectwebservice.web;


import com.project.wah.projectwebservice.config.auth.LoginUser;
import com.project.wah.projectwebservice.config.auth.dto.SessionUser;
import com.project.wah.projectwebservice.service.UsersService;
import com.project.wah.projectwebservice.web.dto.user.UsersResponseDto;
import com.project.wah.projectwebservice.web.dto.user.UsersUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController
public class UsersApiController {

    private final UsersService usersService;

    //User 테이블의 일부 칼럼 수정(nickname, aboutme, mentoremail, githubeamil, blogaddress)
    @PutMapping("/api/v1/users")
    public Long update(@LoginUser SessionUser sessionUser, @RequestBody UsersUpdateRequestDto requestDto, HttpSession httpSession){
        return usersService.update(sessionUser, requestDto, httpSession);
    }

    //User 권한 수정
    @PutMapping("/api/v1/users/update/{id}")
    public Long roleUpdate(@PathVariable Long id, @RequestBody UsersUpdateRequestDto requestDto) {
        return usersService.roleUpdate(id, requestDto);
    }

    //User 테이블 개별 조회
    @GetMapping("/api/v1/users/{id}")
    public UsersResponseDto findById (@PathVariable Long id){
        return usersService.findById(id);
    }

    //User 테이블 삭제
    @DeleteMapping("/api/v1/users")
    public Long delete(@LoginUser SessionUser sessionUser, HttpSession httpSession) {
        return usersService.delete(sessionUser, httpSession);
    }


}
