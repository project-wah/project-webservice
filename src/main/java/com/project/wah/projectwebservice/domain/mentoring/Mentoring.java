package com.project.wah.projectwebservice.domain.mentoring;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.wah.projectwebservice.domain.BaseTimeEntity;
import com.project.wah.projectwebservice.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
public class Mentoring extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    //멘토링 소개글 제목
    @Column(length = 500, nullable = false)
    private String title;

    //글 작성자(멘토 본인 계정에 연동된 NAME)
    @Column(nullable = false)
    private String author;

    //멘토링 소개글 상세
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    //직무
    @Column(nullable = false)
    private String job;

    //경력
    @Column(nullable = false)
    private String career;

    //현재 직장
    @Column
    private String office;

    /**
     * User의 id를 Mentoring의 FK로 지정
     * 한 명의 멘토는 자신의 소개글을 단 하나만 작성 가능하기 때문에 일대일 관계
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * 오류 수정하면서 새롭게 추가한 부분
     * @param user
     */
    public void setUser(User user) {
        this.user = user;
    }

    //멘토링 소개글 수정 관련
    public void update(String title, String content, String job, String career, String office) {
        this.title = title;
        this.content = content;
        this.job = job;
        this.career = career;
        this.office = office;
    }
}

