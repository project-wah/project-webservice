package com.project.wah.projectwebservice.web.dto.user;

import com.project.wah.projectwebservice.domain.user.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserListResponseDto {
    private Long id;
    private String name;
    private String email;
    private String picture;
    private LocalDateTime modifiedDate;

    public UserListResponseDto(User entitiy) {
        this.id = entitiy.getId();
        this.name = entitiy.getName();
        this.email = entitiy.getEmail();
        this.picture = entitiy.getPicture();
        this.modifiedDate = entitiy.getModifiedDate();
    }
}
