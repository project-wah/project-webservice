package com.project.wah.projectwebservice.web.dto.mentoring;

import com.project.wah.projectwebservice.domain.mentoring.MentorIntro;
import lombok.Getter;

@Getter
public class MentorIntroListResponseDto {
    private Long id;
    private String title;
    private String mentor;
    private String job;
    private String career;
    private String office;

    public MentorIntroListResponseDto(MentorIntro entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.mentor = entity.getMentor();
        this.job = entity.getJob();
        this.career = entity.getCareer();
        this.office = entity.getOffice();
    }
}
