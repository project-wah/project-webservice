package com.project.wah.projectwebservice.web.dto.user;

import com.project.wah.projectwebservice.domain.user.Role;
import com.project.wah.projectwebservice.domain.user.User;
import com.sun.istack.NotNull;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class UserListResponseDto {
    private final Long id;
    private final String name;
    private final String nickname;
    private final String email;
    private final Role role;
    private final LocalDate createdDate;
    private final int messageSize;

    public UserListResponseDto(User entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.nickname = entity.getNickname();
        this.email = entity.getEmail();
        this.role = entity.getRole();
        this.createdDate = entity.getCreatedDate().toLocalDate();
        this.messageSize = entity.getSendMessage().size();
    }
}
