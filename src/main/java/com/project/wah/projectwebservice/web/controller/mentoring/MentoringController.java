package com.project.wah.projectwebservice.web.controller.mentoring;

import com.project.wah.projectwebservice.config.auth.LoginUser;
import com.project.wah.projectwebservice.config.auth.dto.SessionUser;
import com.project.wah.projectwebservice.domain.mentoring.Mentoring;
import com.project.wah.projectwebservice.service.MentoringService;
import com.project.wah.projectwebservice.web.dto.mentoring.MentoringDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 멘토링 페이지 화면과 관련된 Controller
 */
@Slf4j
@RequiredArgsConstructor
@Controller
public class MentoringController {

    private final MentoringService mentoringService;

    @GetMapping("/mentoring")
    public String mentoring(Model model, @PageableDefault(sort = "id", direction = Sort.Direction.DESC)
                        Pageable pageable, @LoginUser SessionUser sessionUser) {
        Page<Mentoring> list = mentoringService.pageList(pageable);

        if(sessionUser != null) {
            model.addAttribute("user", sessionUser);
        }

        model.addAttribute("mentoring", list);
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());

        //이전 또는 다음 페이지 유무에 따라 true, false 반환
        model.addAttribute("hasNext", list.hasNext());
        model.addAttribute("hasPrev", list.hasPrevious());

        return "/mentoring/mentoring";
    }

    //멘토링 소개글 작성
    @GetMapping("/mentoring/create")
    public String create(@LoginUser SessionUser sessionUser, Model model) {
        if (sessionUser != null) {
            model.addAttribute("user", sessionUser);
        }
        return "/mentoring/mentoring-create";
    }

    //멘토링 소개글 상세보기
    @GetMapping("/mentoring/read/{id}")
    public String read(@PathVariable Long id, @LoginUser SessionUser sessionUser, Model model) {
        MentoringDto.Response dto = mentoringService.findById(id);

        //사용자 확인
        if (sessionUser != null) {
            model.addAttribute("user", sessionUser);
            
            //멘토링 소개글 작성자 본인이 맞는지 확인
            if (dto.getUserId().equals(sessionUser.getId())) {
                model.addAttribute("author", true);
            }
        }

        model.addAttribute("mentoring", dto);
        return "/mentoring/mentoring-read";
    }

    //멘토링 소개글 수정
    @GetMapping("/mentoring/update/{id}")
    public String update(@PathVariable Long id, @LoginUser SessionUser sessionUser, Model model) {
        MentoringDto.Response dto = mentoringService.findById(id);
        if(sessionUser != null) {
            model.addAttribute("user", sessionUser);
        }
        model.addAttribute("mentoring", dto);

        return "/mentoring/mentoring-update";
    }

    //멘토링 소개 페이지에서 검색
    @GetMapping("/mentoring/search")
    public String search(String keyword, Model model, @PageableDefault(sort = "id", direction = Sort.Direction.DESC)
                         Pageable pageable, @LoginUser SessionUser sessionUser) {
        Page<Mentoring> searchList = mentoringService.search(keyword, pageable);

        if(sessionUser != null) {
            model.addAttribute("user", sessionUser);
        }

        model.addAttribute("searchList", searchList);
        model.addAttribute("keyword", keyword);
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("hasNext", searchList.hasNext());
        model.addAttribute("hasPrev", searchList.hasPrevious());

        return "/mentoring/mentoring-search";
    }
}
