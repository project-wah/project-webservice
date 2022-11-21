package com.project.wah.projectwebservice.web.dto.mentoring;

import com.project.wah.projectwebservice.domain.mentoring.MentorIntro;
import lombok.Getter;

@Getter
public class MentorIntroResponseDto {

    private Long id;
    private String title;
    private String mentor;
    private String job;
    private String career;
    private String office;
    private String content;
    private Integer hour;
    private Integer minutes;
    private Integer price;
    private Integer person;

    public MentorIntroResponseDto(MentorIntro entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.mentor = entity.getMentor();
        this.job = entity.getJob();
        this.career = entity.getCareer();
        this.office = entity.getOffice();
        this.content = entity.getContent();
        this.hour = entity.getHour();
        this.minutes = entity.getMinutes();
        this.price = entity.getPrice();
        this.person = entity.getPerson();
    }
}
