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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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


//    // 쪽지 삭제
//    @Transactional
//    public ResponseEntity<MessageDeleteResponseDto> deleteMessage(int messageId, @LoginUser SessionUser sessionUser) {
//
//        Optional<User> userOptional = userRepository.findById(sessionUser.getId());
//        User user = userOptional.get();
//
//        boolean isValid = user.deleteMessage(messageId);
//
//        if (!isValid) {
//            log.error("nickname={}, messageId={}, error={}", user.getNickname(), messageId, "해당 쪽지를 찾을 수 없음");
//            return new ResponseEntity<>(
//                    MessageDeleteResponseDto.builder().status(StatusMessage.BAD_REQUEST).build(),
//                    HttpStatus.valueOf(StatusCode.BAD_REQUEST)
//            );
//        }
//        messageRepository.deleteById(messageId);
//
//        return new ResponseEntity<>(
//                MessageDeleteResponseDto.builder().status(StatusMessage.SUCCESS).build(),
//                HttpStatus.valueOf(StatusCode.SUCCESS)
//        );
//    }
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

//    // 받은 쪽지 검색 후 리스트 조회
//    @Transactional
//    public Page<MessageListReadResponseDto> findReceiverMessageSearch(String title, Pageable pageable, @LoginUser SessionUser sessionUser) {
//
//        Page<Message> messageList = messageRepository.findReceiverByTitleContaining(title, pageable);
//
//        Page<MessageListReadResponseDto> messagePagingList = messageList.map(message -> new MessageListReadResponseDto(message));
//
//        return messagePagingList;
//    }
//
//    // 보낸 쪽지 검색 후 리스트 조회
//    @Transactional
//    public Page<MessageListReadResponseDto> findSenderMessagesearch(@LoginUser SessionUser sessionUser, Pageable pageable) {
//
//        Page<Message> messageList = messageRepository.findSenderByTitleContaining(sessionUser.getId(), pageable);
//
//        Page<MessageListReadResponseDto> messagePagingList = messageList.map(message -> new MessageListReadResponseDto(message));
//
//        return messagePagingList;
//    }



}


//    Optional<User> userOptional = userRepository.findById(sessionUser.getId());
//    User sendUser = userOptional.get();
//
//    Optional<User> receivedUserOptional = userRepository.findByNickname(requestDto.getReceiver());
//        if(receivedUserOptional.isEmpty()) {
//                log.error("nickname={}, error={}", requestDto.getReceiver(), "쪽지 수신자가 존재하지 않음");
//                return new ResponseEntity<>(
//        MessageCreateResponseDto.builder().status(StatusMessage.UNKNOWN_RECEIVER).build(),
//        HttpStatus.valueOf(StatusCode.BAD_REQUEST)
//        );
//        };
//
//        User receivedUser = receivedUserOptional.get();
//
//        // 쪽지 내용 255자 이내로 제한
//        if (requestDto.getContent().length() > 255) {
//        log.error("nickname={}, error={}", sendUser.getNickname(), "쪽지 내용 255자 초과");
//        return new ResponseEntity<>(
//        MessageCreateResponseDto.builder().status(StatusMessage.BAD_REQUEST).build(),
//        HttpStatus.valueOf(StatusCode.BAD_REQUEST)
//        );
//        }
//
//        // 메시지 보내는 코드
//        Message message = Message.builder()
//        .receiver(receivedUser)
//        .title(requestDto.getTitle())
//        .content(requestDto.getContent())
//        .createdate(requestDto.getCreateDate())
//        .build();
//
//        sendUser.sendMessage(message);
//        messageRepository.save(message);
//
//        return new ResponseEntity<>(
//        MessageCreateResponseDto.builder().status(StatusMessage.SUCCESS).build(),
//        HttpStatus.valueOf(StatusCode.SUCCESS)
//        );