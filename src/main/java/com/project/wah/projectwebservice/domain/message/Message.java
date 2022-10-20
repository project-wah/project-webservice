package com.project.wah.projectwebservice.domain.message;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.project.wah.projectwebservice.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
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
    private int id;

    // 쪽지 송신하는 사용자
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id")
    @JsonManagedReference   // 순환참조 방지
    // @OnDelete(action = OnDeleteAction.NO_ACTION)    // 작성자 혹은 수신자가 계정을 삭제하면 같이 지워짐
    private User sender;

    // 쪽지 수신하는 사용자
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id")
    @JsonManagedReference   // 순환참조 방지
    // @OnDelete(action = OnDeleteAction.NO_ACTION)    // 작성자 혹은 수신자가 계정을 삭제하면 같이 지워짐
    private User receiver;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private boolean readstate;

    @Column(nullable = false)
    private LocalDateTime createdate;

    public void sendUser(User user) { this.sender = user; }
    public void changeReadState() { this.readstate = true; }



}
