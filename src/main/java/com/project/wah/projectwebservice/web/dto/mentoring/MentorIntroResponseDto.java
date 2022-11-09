package com.project.wah.projectwebservice.web.dto.mentoring;

import com.project.wah.projectwebservice.domain.mentoring.MentorIntro;
import lombok.Getter;

@Getter
public class MentorIntroResponseDto {

    private Long id;
    private String mentor;
    private String title;
    private String career;

    public MentorIntroResponseDto(MentorIntro entity) {
        this.id = entity.getId();
        this.mentor = entity.getMentor();
        this.title = entity.getTitle();
        this.career = entity.getCareer();
    }
}
