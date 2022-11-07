package com.project.wah.projectwebservice;

import com.project.wah.projectwebservice.domain.mentoring.MentorIntro;
import com.project.wah.projectwebservice.domain.mentoring.MentorIntroRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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
}
