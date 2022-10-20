package com.project.wah.projectwebservice.web.dto;

import com.project.wah.projectwebservice.domain.user.User;
import lombok.Getter;

@Getter
public class UsersResponseDto {
    private int id;
    private String username;
    private String nickname;
    private String aboutme;
    // private String mentoremail;
    private String githubemail;
    private String blogaddress;
    private int phonenumber;

    public UsersResponseDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.nickname = user.getNickname();
        this.aboutme = user.getAboutme();
        // this.mentoremail = user.getMentoremail();
        this.githubemail = user.getGithubemail();
        this.blogaddress = user.getBlogaddress();
        this.phonenumber = user.getPhonenumber();
    }
}
