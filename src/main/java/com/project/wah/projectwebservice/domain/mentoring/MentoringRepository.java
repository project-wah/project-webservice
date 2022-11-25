package com.project.wah.projectwebservice.domain.mentoring;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

public interface MentoringRepository extends JpaRepository<Mentoring, Long> {
    Page<Mentoring> findByTitleContaining(String keyword, Pageable pageable);
}
