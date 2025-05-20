package com.example.repository;

import com.example.domain.Employee;
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
 * employeesテーブルを操作するリポジトリ(Dao).
 */
@Repository
@RequiredArgsConstructor
public class EmployeeRepository {
    private static final RowMapper<Employee> EMPLOYEE_ROW_MAPPER = new BeanPropertyRowMapper<>(Employee.class);
    private final NamedParameterJdbcTemplate template;

    /**
     * 従業員一覧情報を入社日(降順)で取得する.
     *
     * @return 従業員のリスト
     */
    public List<Employee> findAll() {
        String sql = "SELECT id, name, image, gender, hire_date, mail_address, zip_code, address, " +
                "telephone, salary, characteristics, dependents_count " +
                "FROM employees ORDER BY hire_date DESC";
        return template.query(sql, EMPLOYEE_ROW_MAPPER);
    }

    /**
     * 主キーから従業員情報を取得する.
     *
     * @param id ID
     * @return 従業員情報
     */
    public Employee findById(Integer id) {
        String sql = "SELECT id, name, image, gender, hire_date, mail_address, zip_code, address, " +
                "telephone, salary, characteristics, dependents_count " +
                "FROM employees WHERE id = :id";
        SqlParameterSource param = new MapSqlParameterSource("id", id);

        List<Employee> employeeList = template.query(sql, param, EMPLOYEE_ROW_MAPPER);
        if (employeeList.isEmpty()) {
            return null;
        }
        return employeeList.getFirst();
    }

    /**
     * 従業員情報を変更する.
     *
     * @param employee 従業員情報
     */
    public void update(Employee employee) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(employee);
        String sql = "UPDATE employees SET name = :name, image = :image, gender = :gender, hire_date = :hireDate, " +
                "mail_address = :mailAddress, zip_code = :zipCode, address = :address, telephone = :telephone, salary = :salary, " +
                "characteristics = :characteristics, dependents_count = :dependentsCount WHERE id = :id";

        template.update(sql, param);
    }
}
