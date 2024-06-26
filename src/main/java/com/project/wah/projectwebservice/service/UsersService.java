package com.project.wah.projectwebservice.service;


import com.project.wah.projectwebservice.config.auth.LoginUser;
import com.project.wah.projectwebservice.config.auth.dto.SessionUser;
import com.project.wah.projectwebservice.domain.user.Role;
import com.project.wah.projectwebservice.domain.user.User;
import com.project.wah.projectwebservice.domain.user.UserRepository;
import com.project.wah.projectwebservice.web.dto.user.UserListResponseDto;
import com.project.wah.projectwebservice.web.dto.user.UsersResponseDto;
import com.project.wah.projectwebservice.web.dto.user.UsersUpdateRequestDto;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Service
public class UsersService {

    private final UserRepository userRepository;

    // 유저 상세 정보 수정
    @Transactional
    public Long update(SessionUser sessionUser, UsersUpdateRequestDto requestDto, HttpSession httpSession){
        User user = userRepository.findById(sessionUser.getId()).orElseThrow(() ->
                new IllegalArgumentException("해당 유저가 없습니다. id="+ sessionUser.getId()));

        user.detailUpdate(requestDto.getUsername(),requestDto.getNickname(), requestDto.getAboutme(), requestDto.getGithubemail(), requestDto.getBlogaddress(), requestDto.getPhonenumber());

        // 초기에 GUEST가 상세 정보를 등록할 경우에만 USER 권한으로 변경된다.
        if(user.getRole() == Role.GUEST) {
            user.roleUserUpdate();
        }

        //세션 삭제(httpSession.setattribute를 할 경우 세션 등록은 되나, 권한 초기화를 위해 세션 삭제를 함)
        httpSession.invalidate();

        return sessionUser.getId();
    }

    // 유저 권한 수정
    @Transactional
    public Long roleUpdate(Long id, UsersUpdateRequestDto requestDto) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 유저가 없습니다. id="+ id));

        user.roleUpdate(requestDto.getRole());

        return id;
    }

   // 유저 상세 조회
    @Transactional
    public UsersResponseDto findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 유저가 없습니다. id="+ id));

        return new UsersResponseDto(user);
    }

    // 유저 전체 조회
    @Transactional
    public Page<UserListResponseDto> findAllDesc(Pageable pageable) {
        Page<User> userList = userRepository.findAllDesc(pageable);

        Page<UserListResponseDto> userPagingList = userList.map(user -> new UserListResponseDto(user));

        return userPagingList;
    }

    // 회원 탈퇴
    @Transactional
    public Long delete (SessionUser sessionUser, HttpSession httpSession) {
        User user = userRepository.findById(sessionUser.getId()).orElseThrow(() ->
                new IllegalArgumentException("해당 유저가 없습니다. id="+ sessionUser.getId()));

        userRepository.delete(user);

        //세션 삭제
        httpSession.invalidate();

        return sessionUser.getId();
    }

    // Admin 페이지에서의 User 테이블 삭제(유저 id에 따른 회원 탈퇴)
    @Transactional
    public Long adminDelete(Long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 유저가 없습니다. id="+ id));

        userRepository.delete(user);

        return id;
    }

    // 유저 이름(name) 검색
    @Transactional
    public Page<UserListResponseDto> searchName(String search, Pageable pageable) {
        Page<User> userList = userRepository.findByNameContaining(search, pageable);

        Page<UserListResponseDto> userPagingList = userList.map(user -> new UserListResponseDto(user));

        return userPagingList;
    }
}
