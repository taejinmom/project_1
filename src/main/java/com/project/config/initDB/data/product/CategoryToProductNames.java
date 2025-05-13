package com.project.config.initDB.data.product;

import java.util.List;
import java.util.Map;

public class CategoryToProductNames {
    public static final Map<String, List<String>> CATEGORY_MAP = Map.ofEntries(
            Map.entry("상의", List.of("반팔티", "긴팔티", "맨투맨", "후드티", "셔츠", "니트")),
            Map.entry("하의", List.of("청바지", "슬랙스", "반바지", "면바지", "트레이닝팬츠")),
            Map.entry("아우터", List.of("자켓", "코트", "패딩", "블레이저", "바람막이", "무스탕")),
            Map.entry("신발", List.of("운동화", "샌들", "부츠", "로퍼", "슬리퍼")),
            Map.entry("가방", List.of("백팩", "크로스백", "숄더백", "토트백", "클러치")),
            Map.entry("모자", List.of("야구모자", "비니", "버킷햇", "페도라")),
            Map.entry("액세서리", List.of("시계", "팔찌", "목걸이", "반지", "벨트")),
            Map.entry("스포츠웨어", List.of("트레이닝복", "레깅스", "스포츠브라", "조깅팬츠")),
            Map.entry("언더웨어", List.of("브라", "팬티", "러닝셔츠", "드로즈")),
            Map.entry("홈웨어", List.of("파자마", "잠옷세트", "실내복", "가운"))
    );
}