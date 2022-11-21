package com.project.wah.projectwebservice.service;

import com.project.wah.projectwebservice.config.auth.LoginUser;
import com.project.wah.projectwebservice.config.auth.dto.SessionUser;
import com.project.wah.projectwebservice.domain.message.Message;
import com.project.wah.projectwebservice.domain.message.MessageRepository;
import com.project.wah.projectwebservice.domain.user.User;
import com.project.wah.projectwebservice.domain.user.UserRepository;
import com.project.wah.projectwebservice.web.dto.message.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MessageService {
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    // 쪽지 보내기
    @Transactional
    public Message createMessage(MessageCreateRequestDto requestDto, SessionUser sessionUser) {

        Optional<User> userOptional = userRepository.findById(sessionUser.getId());
        User sendUser = userOptional.get();

        Optional<User> receivedUserOptional = userRepository.findByNickname(requestDto.getReceiver());

        User receivedUser = receivedUserOptional.get();

        // 메시지 보내는 코드
        Message message = Message.builder()
                .receiver(receivedUser)
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .createdate(requestDto.getCreateDate())
                .build();

        sendUser.sendMessage(message);
        return messageRepository.save(message);

    }

    // 쪽지 상세 읽기
    public MessageReadResponseDto findById(int id) {
        Message message = messageRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 메시지가 없습니다. id= " + id));

        return new MessageReadResponseDto(message);
    }

    // 받은 쪽지 리스트 조회
    @Transactional
    public Page<MessageListReadResponseDto> findAllReceiverDesc(@LoginUser SessionUser sessionUser, Pageable pageable) {

        Page<Message> messageList = messageRepository.findAllReceiverDesc(sessionUser.getId(), pageable);

        Page<MessageListReadResponseDto> messagePagingList = messageList.map(message -> new MessageListReadResponseDto(message));

        return messagePagingList;
    }

    // 보낸 쪽지 리스트 조회
    public Page<MessageListReadResponseDto> findAllSenderDesc(@LoginUser SessionUser sessionUser, Pageable pageable) {

        Page<Message> messageList = messageRepository.findAllSenderDesc(sessionUser.getId(), pageable);

        Page<MessageListReadResponseDto> messagePagingList = messageList.map(message -> new MessageListReadResponseDto(message));

        return messagePagingList;
    }

}
