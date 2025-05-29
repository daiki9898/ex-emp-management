package com.example.form;

import com.example.validation.Numeric;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * 従業員情報更新時に使用するフォーム
 */
@Getter
@Setter
@ToString
public class UpdateEmployeeForm {
    /** 従業員ID */
    @NotBlank(message = "idは空ではいけません")
    private String id;

    /** 名前 */
    @NotBlank(message = "名前を入力してください")
    private String name;

    /** 性別 */
    @NotBlank(message = "性別を入力してください")
    private String gender;

    /** 入社日 */
    @NotNull(message = "日付を選択してください")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate hireDate;

    /** メールアドレス */
    @NotBlank(message = "メールアドレスを入力してください")
    @Email(message = "@の形式で入力してください")
    private String mailAddress;

    /** 郵便番号 */
    @Pattern(regexp = "^[0-9]{3}-[0-9]{4}$", message = "xxx-xxxxの形式で入力してください")
    private String zipCode;

    /** 住所 */
    @NotBlank(message = "住所を入力してください")
    private String address;

    /** 電話番号 */
    @Pattern(regexp = "^\\d{2,4}-\\d{2,4}-\\d{4}$", message = "xx-xxxx-xxxxの形式で入力してください")
    private String telephone;

    /** 給料 */
    @Numeric
    private String salary;

    /** 特性 */
    @NotBlank(message = "特性を入力してください")
    private String characteristics;

    /** 扶養人数 */
    @Numeric
    private String dependentsCount;
}
