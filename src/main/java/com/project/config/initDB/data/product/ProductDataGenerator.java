package com.project.config.initDB.data.product;

import com.project.config.initDB.InitDBScriptExecutor;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ProductDataGenerator {

    private final JdbcTemplate jdbcTemplate;
    private final InitDBScriptExecutor initDBScriptExecutor;
    private final Random random = new Random();
    private final DataSource dataSource;
    private final Environment env;

    @PostConstruct
    public void printDatabaseInfo() throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            DatabaseMetaData metaData = conn.getMetaData();
            System.out.println("DB Product Name: " + metaData.getDatabaseProductName());
            System.out.println("DB Version: " + metaData.getDatabaseProductVersion());
            System.out.println("Driver Name: " + metaData.getDriverName());
        }
    }

    @PostConstruct
    public void generateData() { // DB data init methdo. h2일때만 실행 함.
        if (!Arrays.asList(env.getActiveProfiles()).contains("prod")) {
            initDBScriptExecutor.init();
        }

        Map<String, String> categoryIdMap = insertCategories();
        insertProductsWithImages(categoryIdMap);
    }

    private Map<String, String> insertCategories() {
        Map<String, String> map = new HashMap<>();

        for (String category : CategoryToProductNames.CATEGORY_MAP.keySet()) {
            String categoryId = UUID.randomUUID().toString();
            jdbcTemplate.update("""
            INSERT INTO T_CATEGORY (category_id, category_name, created_at, updated_at)
            VALUES (?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
        """, categoryId, category);
            map.put(category, categoryId);
        }

        return map;
    }

    private void insertProductsWithImages(Map<String, String> categoryIdMap) {
        for (Map.Entry<String, List<String>> entry : CategoryToProductNames.CATEGORY_MAP.entrySet()) {
            String category = entry.getKey();
            String categoryId = categoryIdMap.get(category);
            List<String> types = entry.getValue();

            for (int i = 0; i < 100; i++) {
                String productId = UUID.randomUUID().toString();

                String color = getRandomColor();
                String style = getRandomStyle();
                String type = getRandomElement(types);

                String productName = style + " " + color + " " + type;
                int price = 10000 + random.nextInt(50000);
                int stock = 10 + random.nextInt(90);

                String imageKeyword = String.join(",", color, type, category);
                String imageUrl = "https://source.unsplash.com/featured/?" + URLEncoder.encode(imageKeyword, StandardCharsets.UTF_8);

                String description = getRandomDescription(productName);

                jdbcTemplate.update("""
                    INSERT INTO T_PRODUCT (product_id, product_name, description, price, stock_quantity, category_id, thumbnail_url, created_at, updated_at)
                    VALUES (?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
                """, productId, productName, description, price, stock, categoryId, imageUrl);

                jdbcTemplate.update("""
                    INSERT INTO T_PRODUCT_IMAGE (image_id, product_id, image_url, is_thumbnail, created_at)
                    VALUES (?, ?, ?, ?, CURRENT_TIMESTAMP)
                """, UUID.randomUUID().toString(), productId, imageUrl, true);
            }
        }
    }

    private String getRandomColor() {
        List<String> colors = List.of("블랙", "화이트", "그레이", "네이비", "베이지", "카키", "브라운", "핑크", "소라", "차콜");
        return getRandomElement(colors);
    }

    private String getRandomStyle() {
        List<String> styles = List.of("오버핏", "슬림핏", "루즈핏", "기본", "캐주얼", "심플", "클래식", "유니크");
        return getRandomElement(styles);
    }

    private String getRandomDescription(String productName) {
        List<String> phrases = List.of(
                "트렌디한 디자인의 " + productName + "입니다.",
                productName + "는 어떤 스타일에도 잘 어울립니다.",
                "데일리룩에 딱 맞는 아이템, " + productName,
                productName + "로 멋스러움을 더해보세요.",
                "편안함과 스타일을 동시에! " + productName
        );
        return getRandomElement(phrases);
    }

    private <T> T getRandomElement(List<T> list) {
        return list.get(random.nextInt(list.size()));
    }
}