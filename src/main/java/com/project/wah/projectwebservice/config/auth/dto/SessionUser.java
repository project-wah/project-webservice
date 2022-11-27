package com.project.wah.projectwebservice.config.auth.dto;

import com.project.wah.projectwebservice.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

// SessionUser = UsersResponseDTO(유저 조회 DTO)
@Getter
public class SessionUser implements Serializable {
    private Long id;
    private String name;
    private String email;
    private String picture;

    // 회원 상세 정보 수정
    private String username;
    private String nickname;
    private String aboutme;
    private String githubemail;
    private String blogaddress;
    private int phonenumber;


    public SessionUser(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
        // 회원 상세 정보
        this.username = user.getUsername();
        this.nickname = user.getNickname();
        this.aboutme = user.getAboutme();
        this.githubemail = user.getGithubemail();
        this.blogaddress = user.getBlogaddress();
        this.phonenumber = user.getPhonenumber();
    }


}
