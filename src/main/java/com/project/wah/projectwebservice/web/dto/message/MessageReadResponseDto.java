package com.project.wah.projectwebservice.web.dto.message;

import com.project.wah.projectwebservice.domain.message.Message;
import com.project.wah.projectwebservice.domain.user.User;
import lombok.Builder;
import lombok.Getter;

// 메시지 상세 조회 DTO
@Getter
public class MessageReadResponseDto {
    private int id;
    private String receiver;
    private String sender;
    private String title;
    private String content;
    private String status;
    private boolean readState;
    // 송, 수신자 회원 상세 정보 조회를 하기 위해 id값 받음
    private int receiverId;
    private int senderId;

    public MessageReadResponseDto(Message entity) {
        this.id = entity.getId();
        this.receiver = entity.getReceiver().getNickname();
        this.sender = entity.getSender().getNickname();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.readState = entity.isReadstate();
        this.receiverId = entity.getReceiver().getId();
        this.senderId = entity.getSender().getId();
    }

}
