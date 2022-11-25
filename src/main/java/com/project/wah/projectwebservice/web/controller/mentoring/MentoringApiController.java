package com.project.wah.projectwebservice.web.controller.mentoring;

import com.project.wah.projectwebservice.config.auth.LoginUser;
import com.project.wah.projectwebservice.config.auth.dto.SessionUser;
import com.project.wah.projectwebservice.service.MentoringService;
import com.project.wah.projectwebservice.web.dto.mentoring.MentoringDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class MentoringApiController {

    private final MentoringService mentoringService;

    //CREATE
    @PostMapping("/v1/mentoring")
    public ResponseEntity save(@RequestBody MentoringDto.Request dto, @LoginUser SessionUser sessionUser) {
        return ResponseEntity.ok(mentoringService.save(dto, sessionUser.getName()));
    }

    //READ
    @GetMapping("/v1/mentoring/{id}")
    public ResponseEntity read(@PathVariable Long id) {
        return ResponseEntity.ok(mentoringService.findById(id));
    }

    //UPDATE
    @PutMapping("/v1/mentoring/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody MentoringDto.Request dto) {
        mentoringService.update(id, dto);
        return ResponseEntity.ok(id);
    }

    //DELETE
    @DeleteMapping("/v1/mentoring/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        mentoringService.delete(id);
        return ResponseEntity.ok(id);
    }

}
