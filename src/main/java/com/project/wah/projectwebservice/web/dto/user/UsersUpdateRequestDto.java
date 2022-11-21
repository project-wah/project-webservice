package com.project.wah.projectwebservice.web.dto.user;

import com.project.wah.projectwebservice.domain.user.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UsersUpdateRequestDto {
    private String username;
    private String nickname;
    private String aboutme;
    private String githubemail;
    private String blogaddress;
    private int phonenumber;
    private Role role;


    @Builder
    public UsersUpdateRequestDto(String username,String nickname, String aboutme, String githubemail,
                                 String  blogaddress, int phonenumber, Role role) {
        this.username = username;
        this.nickname = nickname;
        this.aboutme = aboutme;
        this.githubemail = githubemail;
        this.blogaddress = blogaddress;
        this.phonenumber = phonenumber;
        this.role = role;
    }
}
