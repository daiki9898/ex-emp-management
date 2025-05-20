package com.example.controller;

import com.example.form.InsertAdministratorForm;
import com.example.service.AdministratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
     * 管理者登録画面にフォワードする
     * @param form 管理者情報を格納するフォーム
     * @return フォワード先のパス
     */
    @GetMapping("/toInsert")
    public String toInsert(InsertAdministratorForm form) {
        return "administrator/insert";
    }

}
