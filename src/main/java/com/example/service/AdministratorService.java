package com.example.service;

import com.example.repository.AdministratorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 管理者情報を操作するサービス
 */
@Service
@Transactional
@RequiredArgsConstructor
public class AdministratorService {
    private final AdministratorRepository administratorRepository;


}
