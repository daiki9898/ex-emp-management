package com.example.service;

import com.example.domain.Employee;
import com.example.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 従業員情報を操作するサービス.
 */
@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    /**
     * 従業員情報一覧を全件検索する.
     *
     * @return 従業員情報一覧
     */
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }
}
