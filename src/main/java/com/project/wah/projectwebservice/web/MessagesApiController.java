package com.project.wah.projectwebservice.web;

import com.project.wah.projectwebservice.config.auth.LoginUser;
import com.project.wah.projectwebservice.config.auth.dto.SessionUser;
import com.project.wah.projectwebservice.domain.message.Message;
import com.project.wah.projectwebservice.service.MessageService;
import com.project.wah.projectwebservice.web.dto.message.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class MessagesApiController {

    private final MessageService messageService;

    // 메시지 생성
    @PostMapping("/message")
    public Message createMessage(@RequestBody MessageCreateRequestDto messageCreateRequestDto,
                                 @LoginUser SessionUser sessionUser) {
        return  messageService.createMessage(messageCreateRequestDto, sessionUser);
    }

    // 메시지 상세 조회
    @GetMapping("/message/read/{messageId}")
    public MessageReadResponseDto findById (@PathVariable Long messageId) {
        return messageService.findById(messageId);
    }

    // 메시지 삭제
    @DeleteMapping("/message/delete/{messageId}")
    public Long delete (@PathVariable Long messageId) {
        messageService.deleteMessage(messageId);
        return messageId;
    }

}


