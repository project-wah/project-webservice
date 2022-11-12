package com.project.wah.projectwebservice.domain.mentoring;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MentorIntroRepository extends JpaRepository<MentorIntro, Long> {

    // 현재 등록된 멘토 리스트 조회
    @Query("SELECT m FROM MentorIntro m ORDER BY m.id DESC")
    List<MentorIntro> findAllDesc();

}
