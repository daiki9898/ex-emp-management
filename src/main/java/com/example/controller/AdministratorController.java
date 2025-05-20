package com.example.controller;

import com.example.domain.Administrator;
import com.example.form.InsertAdministratorForm;
import com.example.service.AdministratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 管理者登録画面を表示する処理を記述する
 */
@Controller
@RequestMapping
@RequiredArgsConstructor
public class AdministratorController {
    private final AdministratorService administratorService;

    /**
     * 管理者登録画面にフォワードする.
     *
     * @param form 管理者情報を格納するフォーム
     * @return フォワード先のパス
     */
    @GetMapping("/toInsert")
    public String toInsert(InsertAdministratorForm form) {
        return "administrator/insert";
    }

    /**
     * 管理者情報を登録する.
     *
     * @param form 管理者情報を格納するフォーム
     * @return リダイレクト先のパス
     */
    @PostMapping("/insert")
    public String insert(InsertAdministratorForm form) {
        // オブジェクトにフォームの中身をコピー
        Administrator administrator = new Administrator();
        BeanUtils.copyProperties(form, administrator);

        // DBへの挿入
        administratorService.insert(administrator);
        return "redirect:/";
    }


}
