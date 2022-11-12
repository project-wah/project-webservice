package com.project.wah.projectwebservice.service;

import com.project.wah.projectwebservice.domain.mentoring.MentorIntro;
import com.project.wah.projectwebservice.domain.mentoring.MentorIntroRepository;
import com.project.wah.projectwebservice.web.dto.mentoring.MentorIntroListResponseDto;
import com.project.wah.projectwebservice.web.dto.mentoring.MentorIntroResponseDto;
import com.project.wah.projectwebservice.web.dto.mentoring.MentorIntroSaveRequestDto;
import com.project.wah.projectwebservice.web.dto.mentoring.MentorIntroUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MentorIntroService {

    private final MentorIntroRepository mentorIntroRepository;

    @Transactional
    public Long save(MentorIntroSaveRequestDto requestDto) {
        return mentorIntroRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, MentorIntroUpdateRequestDto requestDto) {
        MentorIntro mentorIntro = mentorIntroRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 멘토링 소개글은 없습니다. id=" + id));
        mentorIntro.update(
                requestDto.getTitle(),
                requestDto.getJob(),
                requestDto.getCareer(),
                requestDto.getOffice(),
                requestDto.getContent(),
                requestDto.getHour(),
                requestDto.getMinutes(),
                requestDto.getPrice(),
                requestDto.getPerson()
        );
        return id;
    }

    public MentorIntroResponseDto findById(Long id) {
        MentorIntro entity = mentorIntroRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 멘토링 소개글은 없습니다. id=" + id));
        return new MentorIntroResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<MentorIntroListResponseDto> findAllDesc() {
        return mentorIntroRepository.findAllDesc().stream().map(MentorIntroListResponseDto::new).collect(Collectors.toList());
    }
}
