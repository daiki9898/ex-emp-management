package com.example.form;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * 従業員情報更新時に使用するフォーム
 */
@Getter
@Setter
@ToString
public class UpdateEmployeeForm {
    /** 従業員ID */
    private String id;
    /** 名前 */
    private String name;
    /** 画像 */
    private String image;
    /** 性別 */
    private String gender;
    /** 入社日 */
    private Date hireDate;
    /** メールアドレス */
    private String mailAddress;
    /** 郵便番号 */
    private String zipCode;
    /** 住所 */
    private String address;
    /** 電話番号 */
    private String telephone;
    /** 給料 */
    private String salary;
    /** 特性 */
    private String characteristics;
    /** 扶養人数 */
    private String dependentsCount;
}
