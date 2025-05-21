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
 * 管理者情報を操作するコントローラ.
 */
@Controller
@RequestMapping
@RequiredArgsConstructor
public class AdministratorController {
    private final AdministratorService administratorService;
    private final HttpSession session;

    /**
     * 管理者登録画面にフォワードする.
     *
     * @param form 管理者情報を保持するフォーム
     * @return 管理者登録画面
     */
    @GetMapping("/toInsert")
    public String toInsert(InsertAdministratorForm form) {
        return "administrator/insert";
    }

    /**
     * 管理者情報を登録する.
     *
     * @param form 管理者情報を保持するフォーム
     * @return ログイン画面
     */
    @PostMapping("/insert")
    public String insert(InsertAdministratorForm form) {
        Administrator administrator = new Administrator();
        // オブジェクトにフォームの中身をコピー
        BeanUtils.copyProperties(form, administrator);

        administratorService.insert(administrator);
        return "redirect:/";
    }

    /**
     * ログイン画面にフォワードする.
     *
     * @param form ログイン情報を保持するフォーム
     * @return ログイン画面
     */
    @GetMapping
    public String toLogin(LoginForm form) {
        return "administrator/login";
    }

    /**
     * ログイン処理をする.
     *
     * @param form ログイン情報
     * @param model リクエストパラメータ
     * @return 従業員一覧画面(ログイン失敗時はログイン画面)
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

    /**
     * ログアウトする.
     *
     * @return ログイン画面
     */
    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/";
    }


}
