package com.project.wah.projectwebservice.web.dto.user;

import com.project.wah.projectwebservice.domain.user.Role;
import com.project.wah.projectwebservice.domain.user.User;
import lombok.Getter;

@Getter
public class UsersResponseDto {
    private Long id;
    private String username;
    private String nickname;
    private String aboutme;
    private String githubemail;
    private String blogaddress;
    private int phonenumber;
    private Role role;

    public UsersResponseDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.nickname = user.getNickname();
        this.aboutme = user.getAboutme();
        this.githubemail = user.getGithubemail();
        this.blogaddress = user.getBlogaddress();
        this.phonenumber = user.getPhonenumber();
        this.role = user.getRole();
    }
}
