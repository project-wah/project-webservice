package com.project.wah.projectwebservice.web;

import com.project.wah.projectwebservice.config.auth.LoginUser;
import com.project.wah.projectwebservice.config.auth.dto.SessionUser;
import com.project.wah.projectwebservice.domain.user.User;
import com.project.wah.projectwebservice.service.UsersService;
import com.project.wah.projectwebservice.web.dto.user.UserListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class AdminController {

    private final UsersService usersService;

    // 관리자 메인 페이지
    // 유저 전체 조회 및 관리
    @GetMapping("/admin")
    public String admin(Model model, @LoginUser SessionUser user, @PageableDefault(size = 15, sort = "createdDate", direction = Sort.Direction.DESC) Pageable pageable) {

        if(user != null) {
            model.addAttribute("useName", user.getName());
            model.addAttribute("userdName", user);
        }
        Page<UserListResponseDto> allusers = usersService.findAllDesc(pageable);

        model.addAttribute("allusers", allusers);
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("hasNext", allusers.hasNext());
        model.addAttribute("hasPrev", allusers.hasPrevious());

        return "/admin/admin-user";
    }

    // 유저 이름(name) 검색
    @GetMapping("/admin/user/search")
    public String searchName(Model model, String search, @PageableDefault(size = 15, sort = "createdDate", direction = Sort.Direction.DESC) Pageable pageable) {

        Page<UserListResponseDto> userSearch = usersService.searchName(search, pageable);

        model.addAttribute("userSearch", userSearch);
        model.addAttribute("search", search);
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("hasNext", userSearch.hasNext());
        model.addAttribute("hasPrev", userSearch.hasPrevious());

        return "/admin/admin-userSearch";
    }


}
