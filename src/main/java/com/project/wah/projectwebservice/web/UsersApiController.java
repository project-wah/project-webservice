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

    // 유저 정보 수정
    @PutMapping("/api/v1/users")
    public Long update(@LoginUser SessionUser sessionUser, @RequestBody UsersUpdateRequestDto requestDto, HttpSession httpSession){
        return usersService.update(sessionUser, requestDto, httpSession);
    }

    // 유저 권한 수정
    @PutMapping("/api/v1/users/{id}")
    public Long roleUpdate(@PathVariable Long id, @RequestBody UsersUpdateRequestDto requestDto) {
        return usersService.roleUpdate(id, requestDto);
    }

    // 유저 상세 조회
    @GetMapping("/api/v1/users/{id}")
    public UsersResponseDto findById (@PathVariable Long id){
        return usersService.findById(id);
    }

    // 유저 삭제(회원이 자신을 탈퇴시킴)
    @DeleteMapping("/api/v1/users")
    public Long delete(@LoginUser SessionUser sessionUser, HttpSession httpSession) {
        return usersService.delete(sessionUser, httpSession);
    }

    // 유저 삭제(관리자가 유저를 탈퇴시킴)
    @DeleteMapping("/api/v1/users/{id}")
    public Long adminDelete(@PathVariable Long id) {
        return usersService.adminDelete(id);
    }


}
