package com.project.wah.projectwebservice.domain.message;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface MessageRepository extends JpaRepository<Message, Long> {

    // 받은 쪽지 리스트 조회
    @Query("SELECT m FROM Message m WHERE m.receiver.id = :receiverId ORDER BY m.id")
    Page<Message> findAllReceiverDesc(@Param("receiverId") Long receiverId, Pageable pageable);

    // 보낸 쪽지 리스트 조회
    @Query("SELECT m FROM Message m WHERE m.sender.id = :senderId ORDER BY m.id")
    Page<Message> findAllSenderDesc(@Param("senderId") Long senderId, Pageable pageable);

}
