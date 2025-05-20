package com.example.repository;

import com.example.domain.Administrator;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * administratorsテーブルを操作するリポジトリ(Dao)
 */
@Repository
@RequiredArgsConstructor
public class AdministratorRepository {
    private static final RowMapper<Administrator> ADMINISTRATOR_ROW_MAPPER = new BeanPropertyRowMapper<>(Administrator.class);
    private final NamedParameterJdbcTemplate template;

    /**
     * 管理者情報を表示する.
     *
     * @param administrator 管理者情報
     */
    public void insert(Administrator administrator) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(administrator);
        String sql = "INSERT INTO administrators (name, mail_address, password) VALUES " +
                "(:name, :mailAddress, :password)";

        template.update(sql, param);
    }

    /**
     * メールアドレスとパスワードから管理者情報を取得する
     *
     * @param mailAddress メールアドレス
     * @param password パスワード
     * @return 管理者情報
     */
    public Administrator findByEmailAndPassword(String mailAddress, String password) {
        String sql = "SELECT id, name, mail_address, password FROM administrators " +
                "WHERE mail_address = :mailAddress AND password = :password";
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("mailAddress", mailAddress)
                .addValue("password", password);

        List<Administrator> administratorList = template.query(sql, param, ADMINISTRATOR_ROW_MAPPER);
        if (administratorList.isEmpty()) {
            return null;
        }
        return administratorList.getFirst();
    }

}

