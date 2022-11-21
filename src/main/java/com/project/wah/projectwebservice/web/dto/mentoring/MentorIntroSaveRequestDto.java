package com.project.wah.projectwebservice.web.dto.mentoring;

import com.project.wah.projectwebservice.domain.mentoring.MentorIntro;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MentorIntroSaveRequestDto {

    private String mentor;
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
    public MentorIntroSaveRequestDto(String mentor, String title, String job, String career, String office, String content, Integer hour, Integer minutes, Integer price, Integer person) {
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

    public MentorIntro toEntity() {
        return MentorIntro.builder()
                .mentor(mentor)
                .title(title)
                .job(job)
                .career(career)
                .office(office)
                .content(content)
                .hour(hour)
                .minutes(minutes)
                .price(price)
                .person(person)
                .build();
    }

}
