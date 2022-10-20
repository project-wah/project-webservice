package com.project.wah.projectwebservice.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    ADMIN("ROLE_ADMIN", "관리자"),
    GUEST("ROLE_GUEST", "손님"),
    BAN("ROLE_BANUSER", "정지 회원"),
    MENTOR("ROLE_MENTOR", "멘토 회원"),
    USER("ROLE_USER", "일반사용자");

    private final String key;
    private final String title;


}
