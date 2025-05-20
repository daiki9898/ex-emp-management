package com.example.controller;

import com.example.domain.Employee;
import com.example.form.UpdateEmployeeForm;
import com.example.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
     * 従業員一覧を表示する.
     *
     * @param model リクエストパラメータ
     * @return 従業員一覧
     */
    @GetMapping("/showList")
    public String showList(Model model) {
        List<Employee> employeeList = employeeService.findAll();
        model.addAttribute("employeeList", employeeList);
        return "employee/list.html";
    }

    /**
     * 従業員詳細情報を表示する.
     *
     * @param id 従業員ID
     * @param model モデル
     * @param form フォーム
     * @return 従業員詳細画面
     */
    @GetMapping("/showDetail")
    public String showDetail(String id, Model model, UpdateEmployeeForm form) {
        Employee employee = employeeService.showDetail(Integer.parseInt(id));
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(employee, form);
//        model.addAttribute("employee", employee);
        return "employee/detail";
    }

    /**
     * 従業員の扶養人数を更新する.
     *
     * @param form フォーム
     * @return 従業員一覧画面
     */
    @PostMapping("/update")
    public String update(UpdateEmployeeForm form) {
        Employee employee = employeeService.showDetail(Integer.parseInt(form.getId()));
        employee.setDependentsCount(Integer.parseInt(form.getDependentsCount()));
        employeeService.update(employee);

        return "redirect:/employee/showList";
    }
}
