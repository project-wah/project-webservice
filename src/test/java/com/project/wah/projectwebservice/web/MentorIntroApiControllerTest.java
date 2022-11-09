package com.project.wah.projectwebservice.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.wah.projectwebservice.domain.mentoring.MentorIntro;
import com.project.wah.projectwebservice.domain.mentoring.MentorIntroRepository;
import com.project.wah.projectwebservice.web.dto.mentoring.MentorIntroSaveRequestDto;
import com.project.wah.projectwebservice.web.dto.mentoring.MentorIntroUpdateRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MentorIntroApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MentorIntroRepository mentorIntroRepository;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @AfterEach
    public void tearDown() throws Exception {
        mentorIntroRepository.deleteAll();
    }

    //given
    String mentor = "김똑똑";
    String title = "백엔드 개발자 커리어 상담가능합니다.";
    String job = "프론트/백엔드";
    String career = "주니어 3년차";
    String content = "안녕하세요, 네이버에서 백엔드 개발을 담당하고 있는 김똑똑입니다.";
    Integer hour = 1;
    Integer minutes = 30;
    Integer price = 15000;
    Integer person = 1;

    @DisplayName("멘토링 소개 페이지 등록")
    @Test
    @WithMockUser(roles="USER")
    public void register_mentorIntro() throws Exception {
        //given
        MentorIntroSaveRequestDto requestDto = MentorIntroSaveRequestDto.builder()
                .mentor(mentor)
                .title(title)
                .job(job)
                .career(career)
                .content(content)
                .hour(hour)
                .minutes(minutes)
                .price(price)
                .person(person)
                .build();

        String url = "http://localhost:" + port + "/api/v1/mentorIntro";

        //when
        mvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        //then
        List<MentorIntro> all = mentorIntroRepository.findAll();
        assertThat(all.get(0).getMentor()).isEqualTo(mentor);
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getJob()).isEqualTo(job);
        assertThat(all.get(0).getCareer()).isEqualTo(career);
        assertThat(all.get(0).getContent()).isEqualTo(content);
        assertThat(all.get(0).getHour()).isEqualTo(hour);
        assertThat(all.get(0).getMinutes()).isEqualTo(minutes);
        assertThat(all.get(0).getPrice()).isEqualTo(price);
        assertThat(all.get(0).getPerson()).isEqualTo(person);

    }

    @DisplayName("멘토링 소개 페이지 수정")
    @Test
    @WithMockUser(roles="USER")
    public void update_mentorIntro() throws Exception {
        //given
        MentorIntro savedIntro = mentorIntroRepository.save(MentorIntro.builder()
                .mentor(mentor)
                .title(title)
                .job(job)
                .career(career)
                .content(content)
                .hour(hour)
                .minutes(minutes)
                .price(price)
                .person(person)
                .build());

        Long updateId = savedIntro.getId();
        String expectedTitle = "title2";
        String expectedContent = "content2";

        MentorIntroUpdateRequestDto requestDto = MentorIntroUpdateRequestDto.builder().title(expectedTitle).content(expectedContent).build();

        String url = "http://localhost:" + port + "/api/v1/mentorIntro/" + updateId;

        //when
        mvc.perform(put(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        //then
        List<MentorIntro> all = mentorIntroRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
        assertThat(all.get(0).getContent()).isEqualTo(expectedContent);
    }
}
