package com.project.wah.projectwebservice.web.dto.message;

import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;


@Getter
@NoArgsConstructor
public class MessageCreateRequestDto {
    private String receiver;
    private String title;
    private String content;
    private LocalDateTime createDate = LocalDateTime.now();

}
