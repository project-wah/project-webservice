package com.project.wah.projectwebservice.web;

import com.project.wah.projectwebservice.config.auth.LoginUser;
import com.project.wah.projectwebservice.config.auth.dto.SessionUser;
import com.project.wah.projectwebservice.domain.message.Message;
import com.project.wah.projectwebservice.domain.user.User;
import com.project.wah.projectwebservice.domain.user.UserRepository;
import com.project.wah.projectwebservice.response.Response;
import com.project.wah.projectwebservice.service.MessageService;
import com.project.wah.projectwebservice.web.dto.MessageDto;
import com.project.wah.projectwebservice.web.dto.message.*;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class MessageController {

    private final MessageService messageService;

    // 쪽지 생성
    @PostMapping("/message")
    public Message createMessage(@RequestBody MessageCreateRequestDto messageCreateRequestDto,
                                 @LoginUser SessionUser sessionUser) {
        return  messageService.createMessage(messageCreateRequestDto, sessionUser);
    }

    // 메시지 상세 조회
    @GetMapping("/message/read/{messageId}")
    public MessageReadResponseDto findById (@PathVariable int messageId) {
        return messageService.findById(messageId);
    }


//    @DeleteMapping("/message/{messageId}")
//    public ResponseEntity<MessageDeleteResponseDto> find(@PathVariable int messageId,
//                                                         @LoginUser SessionUser sessionUser) {
//        return messageService.deleteMessage(messageId, sessionUser);
//    }
}


