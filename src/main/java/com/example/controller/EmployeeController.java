package com.example.controller;

import com.example.domain.Employee;
import com.example.form.UpdateEmployeeForm;
import com.example.service.EmployeeService;
import com.example.util.PaginationUtil;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 従業員情報を操作するコントローラ.
 */
@Controller
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;
    private final HttpSession session;

    /**
     * 従業員一覧を表示する.
     *
     * @param model リクエストパラメータ
     * @return 従業員一覧
     */
    @GetMapping("/showList")
    public String showList(Model model, @RequestParam(defaultValue = "1") int pageNumber) {
        // ログインしていない場合
        if (session.getAttribute("administratorName") == null) {
            return "redirect:/";
        }
        // 総ページ数がない場合
        if (session.getAttribute("totalPageNumber") == null) {
            session.setAttribute("totalPageNumber", PaginationUtil.getTotalPageNumber(employeeService.findAll()));
        }
        model.addAttribute("employeeList", employeeService.findByPage(pageNumber));
        model.addAttribute("pageNumber", pageNumber);
        return "employee/list";
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
        if (session.getAttribute("administratorName") == null) {
            return "redirect:/";
        }
        Employee employee = employeeService.showDetail(Integer.parseInt(id));
        ModelMapper modelMapper = new ModelMapper();
        // 取得した従業員情報を、フォームにコピー
        modelMapper.map(employee, form);

        // コピーできない画像パスは、直接リクエストスコープにセットする.
        model.addAttribute("imagePath", employee.getImage());
        return "employee/detail";
    }

    /**
     * 従業員の扶養人数を更新する.
     *
     * @param form フォーム
     * @return 従業員一覧画面
     */
    @PostMapping("/update")
    public String update(@Validated UpdateEmployeeForm form, BindingResult bindingResult, Model model) {
        if (session.getAttribute("administratorName") == null) {
            return "redirect:/";
        }
        if (bindingResult.hasErrors()) {
            Integer id = Integer.parseInt(form.getId());
            Employee employee = employeeService.showDetail(id);
            // コピーできない画像パスは、直接リクエストスコープにセットする.
            model.addAttribute("imagePath", employee.getImage());
            return "employee/detail";
        }
        Employee employee = employeeService.showDetail(Integer.parseInt(form.getId()));
        ModelMapper modelMapper = new ModelMapper();
        // 取得した従業員情報にフォームの内容をコピー
        modelMapper.map(form, employee);
        employeeService.update(employee);

        return "redirect:/employee/showList";
    }
}
