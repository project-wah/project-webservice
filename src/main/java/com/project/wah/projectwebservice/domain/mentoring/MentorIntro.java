package com.project.wah.projectwebservice.domain.mentoring;

import com.project.wah.projectwebservice.domain.BaseTimeEntity;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class MentorIntro extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    // 멘토 실명
    //@NotNull
    @Column(nullable = false)
    private String mentor;

    // 멘토 소개 제목
    @NotNull
    @Column(length = 500, nullable = false)
    private String title;

    // 직무
    //@NotNull
    @Column(nullable = false)
    private String job;

    // 경력
    //@NotNull
    @Column(nullable = false)
    private String career;

    // 현재 직장 (공개 여부 설정 가능)
    @Column
    private String office;

    // 멘토 소개 상세
    //@NotNull
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    // 회당 멘토링 시간(hour)
    //@NotNull
    @Column(nullable = false)
    private Integer hour;

    // 회당 멘토링 시간(minute)
    //@NotNull
    @Column(nullable = false)
    private Integer minutes;

    // 회당 멘토링 가격
    //@NotNull
    @Column(nullable = false)
    private Integer price;

    // 회당 멘토링 가능 인원
    //@NotNull
    @Column(nullable = false)
    private Integer person;

    @Builder
    public MentorIntro(String mentor, String title, String job, String career, String office, String content, Integer hour, Integer minutes, Integer price, Integer person) {
        this.mentor = mentor;
        this.title = title;
        this.job = job;
        this.career = career;
        this.office = office;
        this.content = content;
        this.hour = hour;
        this.minutes = minutes;
        this.price = price;
        this.person = person;
    }

    public void update(String title, String job, String career, String office, String content, Integer hour, Integer minutes, Integer price, Integer person) {
        this.title = title;
        this.job = job;
        this.career = career;
        this.office = office;
        this.content = content;
        this.hour = hour;
        this.minutes = minutes;
        this.price = price;
        this.person = person;
    }
}
