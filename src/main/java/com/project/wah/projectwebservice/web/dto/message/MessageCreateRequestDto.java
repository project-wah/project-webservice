package com.project.wah.projectwebservice.web.dto.message;


import com.project.wah.projectwebservice.domain.message.Message;
import com.project.wah.projectwebservice.domain.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@NoArgsConstructor
public class MessageCreateRequestDto {
    private String receiver;
    private String title;
    private String content;
    private LocalDateTime createDate = LocalDateTime.now();

//    @Builder
//    public MessageCreateRequestDto(User sender, User receiver, String title, String content) {
//        this.sender = sender;
//        this.receiver = receiver;
//        this.title = title;
//        this.content = content;
//    }
//
//    public Message toEntity() {
//        return Message.builder()
//                .sender(sender)
//                .receiver(receiver)
//                .title(title)
//                .content(content)
//                .createdate(createDate)
//                .build();
}
