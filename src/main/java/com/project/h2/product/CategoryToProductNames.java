package com.project.h2.product;

import java.util.List;
import java.util.Map;

public class CategoryToProductNames {
    public static final Map<String, List<String>> CATEGORY_MAP = Map.of(
            "상의", List.of("반팔티", "후드티", "셔츠"),
            "하의", List.of("청바지", "슬랙스", "반바지"),
            "아우터", List.of("자켓", "코트", "패딩"),
            "신발", List.of("운동화", "샌들", "부츠")
    );
}