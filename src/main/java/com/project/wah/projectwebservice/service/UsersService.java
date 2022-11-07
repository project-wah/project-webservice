package com.project.wah.projectwebservice.service;


import com.project.wah.projectwebservice.config.auth.LoginUser;
import com.project.wah.projectwebservice.config.auth.dto.SessionUser;
import com.project.wah.projectwebservice.domain.user.Role;
import com.project.wah.projectwebservice.domain.user.User;
import com.project.wah.projectwebservice.domain.user.UserRepository;
import com.project.wah.projectwebservice.web.dto.user.UsersResponseDto;
import com.project.wah.projectwebservice.web.dto.user.UsersUpdateRequestDto;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Service
public class UsersService {

    private final UserRepository userRepository;

    @Transactional
    public Long update(SessionUser sessionUser, UsersUpdateRequestDto requestDto, HttpSession httpSession){
        User user = userRepository.findById(sessionUser.getId()).orElseThrow(() ->
                new IllegalArgumentException("해당 유저가 없습니다. id="+ sessionUser.getId()));

        user.detailUpdate(requestDto.getUsername(),requestDto.getNickname(), requestDto.getAboutme(), requestDto.getGithubemail(), requestDto.getBlogaddress(), requestDto.getPhonenumber(), requestDto.getRole());

        //세션 삭제(httpSession.setattribute를 할 경우 세션 등록은 되나, 권한 초기화를 위해 세션 삭제를 함)
        httpSession.invalidate();

        return sessionUser.getId();
    }

    @Transactional
    public UsersResponseDto findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 유저가 없습니다. id="+ id));

        return new UsersResponseDto(user);
    }

    @Transactional
    public Long delete (SessionUser sessionUser, HttpSession httpSession) {
        User user = userRepository.findById(sessionUser.getId()).orElseThrow(() ->
                new IllegalArgumentException("해당 유저가 없습니다. id="+ sessionUser.getId()));

        userRepository.delete(user);

        //세션 삭제
        httpSession.invalidate();

        return sessionUser.getId();
    }
}
