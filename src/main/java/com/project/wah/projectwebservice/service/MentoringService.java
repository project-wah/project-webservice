package com.project.wah.projectwebservice.service;

import com.project.wah.projectwebservice.domain.mentoring.Mentoring;
import com.project.wah.projectwebservice.domain.mentoring.MentoringRepository;
import com.project.wah.projectwebservice.domain.user.User;
import com.project.wah.projectwebservice.domain.user.UserRepository;
import com.project.wah.projectwebservice.web.dto.mentoring.MentoringDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class MentoringService {

    private final MentoringRepository mentoringRepository;
    private final UserRepository userRepository;

    //CREATE
    @Transactional
    public Long save(MentoringDto.Request dto, String name) {
        User user = userRepository.findByName(name);
        dto.setUser(user);
        log.info("MentoringService save() 실행");
        Mentoring mentoring = dto.toEntity();
        mentoringRepository.save(mentoring);

        return mentoring.getId();
    }

    //READ : 게시글 리스트 조회
    @Transactional(readOnly = true)
    public MentoringDto.Response findById(Long id){
        Mentoring mentoring = mentoringRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당되는 멘토링 소개글이 존재하지 않습니다."));

        return new MentoringDto.Response(mentoring);
    }

    //UPDATE
    @Transactional
    public void update(Long id, MentoringDto.Request dto) {
        Mentoring mentoring = mentoringRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 멘토링 소개글은 없습니다. id=" + id));

        mentoring.update(
                dto.getTitle(),
                dto.getContent(),
                dto.getJob(),
                dto.getCareer(),
                dto.getOffice()
        );
    }

    //DELETE
    @Transactional
    public void delete(Long id) {
        Mentoring mentoring = mentoringRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당되는 멘토링 소개글이 존재하지 않습니다. id=" + id));

        mentoringRepository.delete(mentoring);
    }

    //페이징
    @Transactional(readOnly = true)
    public Page<Mentoring> pageList(Pageable pageable) {
        return mentoringRepository.findAll(pageable);
    }

    //검색
    @Transactional(readOnly = true)
    public Page<Mentoring> search(String keyword, Pageable pageable) {
        Page<Mentoring> mentoringList = mentoringRepository.findByTitleContaining(keyword, pageable);
        return mentoringList;
    }
}