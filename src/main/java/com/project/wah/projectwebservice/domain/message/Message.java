package com.project.wah.projectwebservice.domain.message;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.project.wah.projectwebservice.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MESSAGE_ID", nullable = false)
    private Long id;

    // 메시지 송신하는 사용자
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id", nullable = false)
    @JsonManagedReference   // 순환참조 방지
    // @OnDelete(action = OnDeleteAction.NO_ACTION)    // 작성자 혹은 수신자가 계정을 삭제하면 같이 지워짐
    private User sender;

    // 메시지 수신하는 사용자
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id", nullable = false)
    @JsonManagedReference   // 순환참조 방지
    // @OnDelete(action = OnDeleteAction.NO_ACTION)    // 작성자 혹은 수신자가 계정을 삭제하면 같이 지워짐
    private User receiver;

    // 메시지 제목
    @Column(nullable = false)
    private String title;

    // 메시지 내용, 메시지 내용은 최대 500자로 설정
    @Column(columnDefinition = "TEXT",length = 500, nullable = false)
    private String content;

    // 메시지를 생성한 날짜
    @Column(nullable = false)
    private LocalDateTime createdate;

    public void sendUser(User user) { this.sender = user; }




}
