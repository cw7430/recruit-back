package com.recruit.module.auth.dto.request;

import com.recruit.module.recurit.repository.RecruitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final RecruitRepository recruitRepository;

}
