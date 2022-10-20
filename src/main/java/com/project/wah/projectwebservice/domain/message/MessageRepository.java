package com.project.wah.projectwebservice.domain.message;

import com.project.wah.projectwebservice.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {

    // 받은 쪽지 리스트 조회
    @Query("SELECT m FROM Message m WHERE m.receiver.id = :receiverId ORDER BY m.id")
    Page<Message> findAllReceiverDesc(@Param("receiverId") int receiverId, Pageable pageable);

    // 보낸 쪽지 리스트 조회
    @Query("SELECT m FROM Message m WHERE m.sender.id = :senderId ORDER BY m.id")
    Page<Message> findAllSenderDesc(@Param("senderId") int senderId, Pageable pageable);

//    // 받은 쪽지 검색
//    Page<Message> findReceiverByTitleContaining(@Param("title") String title, Pageable pageable);
//
//    // 보낸 쪽지 검색
//    @Query("SELECT m FROM Message m WHERE m.sender.id = :senderId ORDER BY m.id")
//    Page<Message> findSenderByTitleContaining(@Param("senderId") int senderId, Pageable pageable);
}
