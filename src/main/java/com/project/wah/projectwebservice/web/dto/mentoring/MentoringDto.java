package com.project.wah.projectwebservice.web.dto.mentoring;

import com.project.wah.projectwebservice.domain.mentoring.Mentoring;
import com.project.wah.projectwebservice.domain.user.User;
import lombok.*;

import java.time.LocalDateTime;

public class MentoringDto {

    /**
     * 멘토링 소개글 등록 및 수정 담당 Request
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Builder
    public static class Request {
        private Long id;
        private String title;

        //글 작성자(멘토 본인 계정에 연동된 NAME)
        private String author;

        private String content;
        private String createdDate;
        private String modifiedDate;

        //멘토 직장 관련
        private String job;
        private String career;
        private String office;

        private User user;

        public Mentoring toEntity() {
            Mentoring mentoring = Mentoring.builder()
                    .id(id)
                    .title(title)
                    .author(author)
                    .content(content)
                    .job(job)
                    .career(career)
                    .office(office)
                    .user(user)
                    .build();

            return mentoring;
        }
    }

    /**
     * 멘토링 소개글 정보를 반환하는 Response
     */
    @Getter
    public static class Response {
        private final Long id;
        private final String title;
        private final String author;
        private final String content;
        private final LocalDateTime createdDate;
        private final LocalDateTime modifiedDate;
        private final String job;
        private final String career;
        private final String office;
        private final Long userId;
        
        public Response(Mentoring mentoring) {
            this.id = mentoring.getId();
            this.title = mentoring.getTitle();
            this.author = mentoring.getAuthor();
            this.content = mentoring.getContent();
            this.createdDate = mentoring.getCreatedDate();
            this.modifiedDate = mentoring.getModifiedDate();
            this.job = mentoring.getJob();
            this.career = mentoring.getCareer();
            this.office = mentoring.getOffice();
            this.userId = mentoring.getUser().getId();
        }
    }
}
