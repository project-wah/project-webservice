package com.project.wah.projectwebservice.domain.user;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.project.wah.projectwebservice.domain.BaseTimeEntity;
import com.project.wah.projectwebservice.domain.message.Message;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;



import javax.persistence.*;
import java.util.*;


@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    // 유저 추가 정보(사용자 실명, 닉네임, 자기소개, 깃허브 이메일, 블로그 주소, 휴대폰 번호)
    @Column
    private String username;

    @Column(unique = true)
    private String nickname;

    @Column
    private String aboutme;

    @Column
    private String githubemail;

    @Column
    private String blogaddress;

    @Column
    private int phonenumber;

    // 쪽지 양방향, 회원이 삭제되면 쪽지(발송한 쪽지)도 같이 삭제
    @OneToMany(mappedBy = "sender", cascade = CascadeType.REMOVE)
    @JsonBackReference  // 순환참조 방지
    private List<Message> sendMessage = new ArrayList<>();

    // 회원이 삭제되면, 쪽지(수신한 쪽지)도 같이 삭제
    @OneToMany(mappedBy = "receiver", cascade = CascadeType.REMOVE)
    @JsonBackReference  // 순환참조 방지
    private List<Message> readMessage = new ArrayList<>();

    public void sendMessage(Message message) {
        this.sendMessage.add(message);
        if (message.getSender() != this) {
            message.sendUser(this);
        }
    }

    @Builder
    public User(Long id, String name, String email, String picture, Role role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;

    }

    public User update(String name, String picture) {
        this.name = name;
        this.picture = picture;

        return this;
    }

    public void detailUpdate(String username, String nickname, String aboutme, String githubemail, String blogaddress, int phonenumber) {
        this.username = username;
        this.nickname = nickname;
        this.aboutme = aboutme;
        this.githubemail = githubemail;
        this.blogaddress = blogaddress;
        this.phonenumber = phonenumber;
    }

    // 권한(Role)을 손님(ROLE_GUEST) -> 유저(ROLE_USER)로 수정
    public void roleUserUpdate() {
        this.role = Role.USER;
    }

    public void roleUpdate(Role role) {
        this.role = role;
    }

    public String getRoleKey() {

        return this.role.getKey();
    }

}
