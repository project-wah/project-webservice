package com.project.wah.projectwebservice.domain.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // Oauth2를 활용한 회원 가입 시 이메일 중복 방지
    Optional<User> findByEmail(String email);

    Optional<User> findByNickname(String nickname);

    // 유저 전체 조회
    @Query("SELECT u FROM User u ORDER BY u.id DESC")
    Page<User> findAllDesc(Pageable pageable);

    // 유저 이름 검색하여 조회
    Page<User> findByNameContaining(@Param("search") String search, Pageable pageable);

}
