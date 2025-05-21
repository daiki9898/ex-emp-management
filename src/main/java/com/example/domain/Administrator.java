package com.example.domain;

import lombok.*;

/**
 * 管理者情報を表すドメイン.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Administrator {
    /** ID(主キー) */
    private Integer id;
    /** 名前 */
    private String name;
    /** メールアドレス */
    private String mailAddress;
    /** パスワード */
    private String password;
}
