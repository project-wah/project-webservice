package com.project.wah.projectwebservice.web;

import com.project.wah.projectwebservice.config.auth.LoginUser;
import com.project.wah.projectwebservice.config.auth.dto.SessionUser;

import com.project.wah.projectwebservice.domain.message.Message;
import com.project.wah.projectwebservice.service.MessageService;
import com.project.wah.projectwebservice.service.UsersService;
import com.project.wah.projectwebservice.web.dto.UsersResponseDto;
import com.project.wah.projectwebservice.web.dto.message.MessageListReadResponseDto;
import com.project.wah.projectwebservice.web.dto.message.MessageReadResponseDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import javax.servlet.http.HttpSession;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final HttpSession httpSession;
    private final UsersService usersService;
    private final MessageService messageService;


    //대문 페이지 생성
    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {

        if(user != null) {
            model.addAttribute("useName", user.getName());
            model.addAttribute("userdName", user);
        }
        return "index";
    }

    //회원 정보 수정
    @GetMapping("/users/update")
    public String usersUpdate(Model model, @LoginUser SessionUser user){

        if(user != null) {
            model.addAttribute("useName", user.getName());
            // UsersResponseDto dto = usersService.findById(id);
            model.addAttribute("userdName", user);
        }
        return "user-update";
    }

    //유저 상세 보기
    @GetMapping("/users/detail/read/{id}")
    public String userDetailRead(@PathVariable int id, Model model, @LoginUser SessionUser user) {
        if(user != null) {
            model.addAttribute("useName", user.getName());
            model.addAttribute("userdName", user);
        }

        UsersResponseDto dto = usersService.findById(id);

        model.addAttribute("useDetail", dto);

        return "user-detailread";

    }

    @GetMapping("/message/locker")
    public String messagelocker(Model model, @LoginUser SessionUser user) {
        if(user != null) {
            model.addAttribute("useName", user.getName());
            model.addAttribute("userdName", user);
        }

        return "message-locker";

    }

    @GetMapping("/message/create")
    public String messageCreate(Model model, @LoginUser SessionUser user) {
        if(user != null) {
            model.addAttribute("useName", user.getName());
            model.addAttribute("userdName", user);
        }

        return "message-create";

    }

    @GetMapping("/message/receiver/read")
    public String receiverReadMessage(Model model, @LoginUser SessionUser user, @PageableDefault(size = 15, sort = "createdate", direction = Sort.Direction.DESC) Pageable pageable) {
        if(user != null) {
            model.addAttribute("useName", user.getName());
            model.addAttribute("userdName", user);
        }

        Page<MessageListReadResponseDto> readList = messageService.findAllReceiverDesc(user, pageable);

        model.addAttribute("readList", readList);
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("hasNext", readList.hasNext());
        model.addAttribute("hasPrev", readList.hasPrevious());

        return "message-receiverread";
    }


    @GetMapping("/message/sender/read")
    public String senderReadMessage(Model model, @LoginUser SessionUser user, @PageableDefault(size = 15, sort = "createdate", direction = Sort.Direction.DESC) Pageable pageable) {
        if(user != null) {
            model.addAttribute("useName", user.getName());
            model.addAttribute("userdName", user);
        }

        Page<MessageListReadResponseDto> readList = messageService.findAllSenderDesc(user, pageable);

        model.addAttribute("readList", readList);
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("hasNext", readList.hasNext());
        model.addAttribute("hasPrev", readList.hasPrevious());

        return "message-senderread";
    }

    @GetMapping("/message/detail/read/{id}")
    public String messageDetailRead(@PathVariable int id, Model model, @LoginUser SessionUser user) {
        if(user != null) {
            model.addAttribute("useName", user.getName());
            model.addAttribute("userdName", user);
        }

        MessageReadResponseDto dto = messageService.findById(id);

        model.addAttribute("mess", dto);

        return "message-detailread";

    }

}
