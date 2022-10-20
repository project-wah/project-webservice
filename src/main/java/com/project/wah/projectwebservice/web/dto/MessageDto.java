package com.project.wah.projectwebservice.web.dto;

import com.project.wah.projectwebservice.domain.message.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto {
    private String title;
    private String content;
    private String senderName;
    private String receiverName;

    public static MessageDto messageDto(Message message) {

        return new MessageDto(
                message.getTitle(),
                message.getContent(),
                message.getSender().getUsername(),
                message.getReceiver().getUsername()
        );
    }

}
