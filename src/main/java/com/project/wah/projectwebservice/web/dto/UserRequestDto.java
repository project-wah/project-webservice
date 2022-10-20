//package com.project.wah.projectwebservice.web.dto;
//
//
//import com.project.wah.projectwebservice.domain.user.Role;
//import com.project.wah.projectwebservice.domain.user.User;
//import lombok.Builder;
//
//public class UserRequestDto {
//
//    private Long id;
//    private String name;
//    private String email;
//    private String picture;
//    private Role role;
//
//    @Builder
//    public UserRequestDto(String name, String email, String picture, Role role) {
//        this.name = name;
//        this.email = email;
//        this.picture = picture;
//        this.role = role;
//    }
//
//    public User toEntity() {
//        return User.builder()
//                .name(name)
//                .email(email)
//                .picture(picture)
//                .role(Role.USER)
//                .build();
//    }
//
//
//
//}
