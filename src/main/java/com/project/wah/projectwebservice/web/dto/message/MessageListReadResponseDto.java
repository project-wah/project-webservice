package com.project.wah.projectwebservice.web.dto.message;

import com.project.wah.projectwebservice.domain.message.Message;
import com.project.wah.projectwebservice.domain.user.User;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

// 메시지 전체 조회 DTO
@Getter
public class MessageListReadResponseDto {
    private Long id;
    private String title;
    private String content;
    private String receiver;
    private String sender;
    private LocalDate createDate;   // 조회를 할 경우, 나노 초를 보여주지 않기 위해 LocalDate를 사용
    // 송, 수신자 회원 상세 정보 조회를 하기 위해 id값 받음
    private Long receiverId;
    private Long senderId;

    public MessageListReadResponseDto(Message entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.receiver = entity.getReceiver().getNickname();
        this.sender = entity.getSender().getNickname();
        this.createDate = entity.getCreatedate().toLocalDate();
        this.receiverId = entity.getReceiver().getId();
        this.senderId = entity.getSender().getId();
    }


}
