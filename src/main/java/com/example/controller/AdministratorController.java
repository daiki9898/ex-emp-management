package com.example.controller;

import com.example.domain.Administrator;
import com.example.form.InsertAdministratorForm;
import com.example.form.LoginForm;
import com.example.service.AdministratorService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    private final HttpSession session;

    /**
     * ログイン画面にフォワードする.
     *
     * @param form ログイン情報を保持するフォーム
     * @return フォワード先のパス
     */
    @GetMapping
    public String toLogin(LoginForm form) {
        return "administrator/login";
    }

    /**
     * 管理者登録画面にフォワードする.
     *
     * @param form 管理者情報を保持するフォーム
     * @return フォワード先のパス
     */
    @GetMapping("/toInsert")
    public String toInsert(InsertAdministratorForm form) {
        return "administrator/insert";
    }

    /**
     * 管理者情報を登録する.
     *
     * @param form 管理者情報を保持するフォーム
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

    /**
     * ログイン処理をする.
     *
     * @param form ログイン情報
     * @param model リクエストスコープ
     * @return 遷移先のパス
     */
    @PostMapping("/login")
    public String login(LoginForm form, Model model) {
        Administrator administrator = administratorService.login(form.getMailAddress(), form.getPassword());
        // ログインに失敗した場合
        if (administrator == null) {
            model.addAttribute("errorMessage", "メールアドレスまたはパスワードが不正です。");
            return toLogin(form);
        }

        // ログインに成功した場合
        session.setAttribute("administratorName", administrator.getName());
        return "redirect:/employee/showList";
    }


}
