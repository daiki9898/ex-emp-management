package com.example.controller;

import com.example.domain.Employee;
import com.example.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 従業員情報を操作するコントローラ.
 */
@Controller
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    /**
     * 従業員一覧を出力する.
     *
     * @param model リクエストパラメータ
     * @return 従業員一覧
     */
    @GetMapping("/showList")
    public String showList(Model model) {
        List<Employee> employeeList = employeeService.findAll();
        employeeList.forEach(System.out::println);
        model.addAttribute("employeeList", employeeList);
        return "employee/list.html";
    }
}
