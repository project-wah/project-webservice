package com.project.wah.projectwebservice.web;

import com.project.wah.projectwebservice.service.MentorIntroService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 멘토링 페이지에 관련된 컨트롤러
 */
@RequiredArgsConstructor
@Controller
public class MentoringController {

    private final MentorIntroService mentorIntroService;

    @GetMapping("/mentoring")
    public String mentoring(Model model) {
        model.addAttribute("mentorIntro", mentorIntroService.findAllDesc());
        return "mentoring";
    }

    //멘토 지원하기
    @GetMapping("/mentoring/apply")
    public String mentoringSave() {
        return "mentoring-apply";
    }
}
