package com.project.wah.projectwebservice.web;

import com.project.wah.projectwebservice.service.MentorIntroService;
import com.project.wah.projectwebservice.web.dto.mentoring.MentorIntroResponseDto;
import com.project.wah.projectwebservice.web.dto.mentoring.MentorIntroSaveRequestDto;
import com.project.wah.projectwebservice.web.dto.mentoring.MentorIntroUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class MentorIntroApiController {

    private final MentorIntroService mentorIntroService;

    @PostMapping("/api/v1/mentorIntro")
    public Long save(@RequestBody MentorIntroSaveRequestDto requestDto) {
        return mentorIntroService.save(requestDto);
    }

    @PutMapping("/api/v1/mentorIntro/{id}")
    public Long update(@PathVariable Long id, @RequestBody MentorIntroUpdateRequestDto requestDto) {
        return mentorIntroService.update(id, requestDto);
    }

    @GetMapping("/api/v1/mentorIntro/{id}")
    public MentorIntroResponseDto findById (@PathVariable Long id) {
        return mentorIntroService.findById(id);
    }

    @DeleteMapping("/api/v1/mentorIntro/{id}")
    public Long delete(@PathVariable Long id) {
        mentorIntroService.delete(id);
        return id;
    }
}
