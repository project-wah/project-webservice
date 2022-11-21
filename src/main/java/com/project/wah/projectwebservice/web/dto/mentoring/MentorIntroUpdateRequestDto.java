package com.project.wah.projectwebservice.web.dto.mentoring;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MentorIntroUpdateRequestDto {

    private String title;
    private String job;
    private String career;
    private String office;
    private String content;
    private Integer hour;
    private Integer minutes;
    private Integer price;
    private Integer person;

    @Builder
    public MentorIntroUpdateRequestDto(String title, String job, String career, String office, String content, Integer hour, Integer minutes, Integer price, Integer person) {
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
