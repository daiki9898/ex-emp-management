package com.example.util;

import com.example.domain.Employee;

import java.util.List;

public class PaginationUtil {
    // 1ページに表示する最大人数
    private static final int MAX_NUMBER = 10;

    public static int getTotalPageNumber(List<Employee> list) {
        int size = list.size();
        int totalPageNumber = size / 10;
        if (size % 10 != 0) {
            totalPageNumber++;
        }
        return totalPageNumber;
    }
}
