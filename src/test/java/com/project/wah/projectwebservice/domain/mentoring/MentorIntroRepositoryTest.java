package com.project.wah.projectwebservice.domain.mentoring;

import com.project.wah.projectwebservice.domain.mentoring.MentorIntro;
import com.project.wah.projectwebservice.domain.mentoring.MentorIntroRepository;
import org.apache.tomcat.jni.Local;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MentorIntroRepositoryTest {
    @Autowired
    MentorIntroRepository mentorIntroRepository;

    @AfterEach
    public void cleanup() {
        mentorIntroRepository.deleteAll();
    }

    @DisplayName("저장한 멘토링 소개글 불러오기 테스트")
    @Test
    public void saveMentorIntro() {
        //given
        String title = "멘토링 소개 제목";
        String content = "멘토링 본문입니다. 나는 새삥 모든게 다 새삥";

        mentorIntroRepository.save(MentorIntro.builder()
                .mentor("코돌이")
                .title(title)
                .job("프론트/백엔드")
                .career("주니어 3년차")
                .office("네이버")
                .content(content)
                .hour(1)
                .minutes(30)
                .price(15000)
                .person(1)
                .build());

        //when
        List<MentorIntro> mentorIntroList = mentorIntroRepository.findAll();

        //then
        MentorIntro mentorIntro = mentorIntroList.get(0);
        assertThat(mentorIntro.getTitle()).isEqualTo(title);
        assertThat(mentorIntro.getContent()).isEqualTo(content);
    }

    @DisplayName("JPA Auditing 테스트")
    @Test
    public void register_BaseTimeEntity() {

        //given
        LocalDateTime now = LocalDateTime.of(2020, 1, 1, 0, 0, 0);
        mentorIntroRepository.save(MentorIntro.builder()
                .mentor("코돌이")
                .title("title")
                .job("프론트/백엔드")
                .career("주니어 3년차")
                .content("안녕하세요")
                .hour(1)
                .minutes(30)
                .price(15000)
                .person(1)
                .build());

        //when
        List<MentorIntro> mentorIntroList = mentorIntroRepository.findAll();

        //then
        MentorIntro mentorIntro = mentorIntroList.get(0);

        System.out.println(">>>>>>>>>> createDate=" + mentorIntro.getCreatedDate() + ", modifiedDate=" + mentorIntro.getModifiedDate());
        assertThat(mentorIntro.getCreatedDate().isAfter(now));
        assertThat(mentorIntro.getModifiedDate().isAfter(now));

    }
}
