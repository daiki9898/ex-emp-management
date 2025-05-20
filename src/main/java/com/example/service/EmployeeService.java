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

    /**
     * 従業員情報を取得する.
     *
     * @param id 従業員ID
     * @return 従業員情報
     */
    public Employee showDetail(Integer id) {
        return employeeRepository.findById(id);
    }

    /**
     * 従業員情報を更新する.
     *
     * @param employee 従業員情報
     */
    public void update(Employee employee) {
        employeeRepository.update(employee);
    }
}
