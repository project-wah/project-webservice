package com.project.wah.projectwebservice.web.dto.user;

import com.project.wah.projectwebservice.domain.user.Role;
import com.project.wah.projectwebservice.domain.user.User;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class UserListResponseDto {
    private Long id;
    private String name;
    private String nickname;
    private String email;
    private Role role;
    private LocalDate createdDate;
    private int messageSize;

    public UserListResponseDto(User entitiy) {
        this.id = entitiy.getId();
        this.name = entitiy.getName();
        this.nickname = entitiy.getNickname();
        this.email = entitiy.getEmail();
        this.role = entitiy.getRole();
        this.createdDate = entitiy.getCreatedDate().toLocalDate();
        this.messageSize = entitiy.getSendMessage().size();
    }
}
