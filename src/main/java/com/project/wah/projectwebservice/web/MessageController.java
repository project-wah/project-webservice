package com.project.wah.projectwebservice.web;

import com.project.wah.projectwebservice.config.auth.LoginUser;
import com.project.wah.projectwebservice.config.auth.dto.SessionUser;
import com.project.wah.projectwebservice.service.MessageService;
import com.project.wah.projectwebservice.web.dto.message.MessageListReadResponseDto;
import com.project.wah.projectwebservice.web.dto.message.MessageReadResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class MessageController {

    private final MessageService messageService;

    // 메시지 메인 페이지
    @GetMapping("/messages")
    public String messageLocker(Model model, @LoginUser SessionUser user) {
        if(user != null) {
            model.addAttribute("useName", user.getName());
            model.addAttribute("userdName", user);
        }

        return "message-locker";

    }

    // 메시지 생성
    @GetMapping("/messages/create")
    public String messageCreate(Model model, @LoginUser SessionUser user) {
        if(user != null) {
            model.addAttribute("useName", user.getName());
            model.addAttribute("userdName", user);
        }

        return "message-create";

    }

    // 받은 메시지 전체 조회
    @GetMapping("/messages/receive/list")
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

    // 보낸 메시지 전체 조회
    @GetMapping("/messages/send/list")
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

    // 메시지 상세 조회
    @GetMapping("/messages/detail/{id}")
    public String messageDetailRead(@PathVariable Long id, Model model, @LoginUser SessionUser user) {
        if(user != null) {
            model.addAttribute("useName", user.getName());
            model.addAttribute("userdName", user);
        }

        MessageReadResponseDto dto = messageService.findById(id);

        model.addAttribute("mess", dto);

        return "message-detailread";

    }

}
