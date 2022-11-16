package com.project.wah.projectwebservice.web;

import com.project.wah.projectwebservice.service.MentorIntroService;
import com.project.wah.projectwebservice.web.dto.mentoring.MentorIntroResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 멘토링 페이지에 관련된 컨트롤러
 */
@RequiredArgsConstructor
@Controller
public class MentoringController {

    private final MentorIntroService mentorIntroService;

    @GetMapping("/mentoring")
    public String mentoring(Model model) {
        model.addAttribute("mentoring", mentorIntroService.findAllDesc());
        return "mentoring";
    }

    //멘토 지원하기
    @GetMapping("/mentoring/apply")
    public String mentoringSave() {
        return "mentoring-apply";
    }

    //멘토 소개글 수정
    @GetMapping("/mentoring/update/{id}")
    public String mentoringUpdate(@PathVariable Long id, Model model) {
        MentorIntroResponseDto dto = mentorIntroService.findById(id);
        model.addAttribute("mentoringUpdate", dto);

        return "mentoring-update";
    }
}
