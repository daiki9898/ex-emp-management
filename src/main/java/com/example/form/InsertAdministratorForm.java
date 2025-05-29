package com.example.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 管理者情報登録時に使用するフォーム
 */
@Getter
@Setter
@ToString
public class InsertAdministratorForm {

    /** 名前 */
    @NotBlank(message = "名前を入力してください")
    private String name;
    /** メールアドレス */
    @NotBlank(message = "メールアドレスを入力してください")
    @Email(message = "@の形式で入力してください")
    private String mailAddress;
    /** パスワード */
    @NotBlank(message = "パスワードを入力してください")
    private String password;
}
