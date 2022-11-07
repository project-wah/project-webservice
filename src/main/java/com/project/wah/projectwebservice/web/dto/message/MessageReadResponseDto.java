package com.project.wah.projectwebservice.web.dto.message;

import com.project.wah.projectwebservice.domain.message.Message;
import lombok.Getter;

// 메시지 상세 조회 DTO
@Getter
public class MessageReadResponseDto {
    private Long id;
    private String receiver;
    private String sender;
    private String title;
    private String content;
    // 송, 수신자 회원 상세 정보 조회를 하기 위해 id값 받음
    private Long receiverId;
    private Long senderId;

    public MessageReadResponseDto(Message entity) {
        this.id = entity.getId();
        this.receiver = entity.getReceiver().getNickname();
        this.sender = entity.getSender().getNickname();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.receiverId = entity.getReceiver().getId();
        this.senderId = entity.getSender().getId();
    }

}
