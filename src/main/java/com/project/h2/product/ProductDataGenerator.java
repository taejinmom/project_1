package com.project.h2.product;

import com.project.config.InitDBScriptExecutor;
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
    private final Environment env; // ← 추가 필요


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
    public void generateData() {
        if (!Arrays.asList(env.getActiveProfiles()).contains("dev")) {
            return;
        }
        initDBScriptExecutor.init();
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
                String productName = getRandomColor() + " " + getRandomElement(types);
                int price = 10000 + random.nextInt(50000);
                int stock = 10 + random.nextInt(90);
                String imageUrl = "https://source.unsplash.com/featured/?" + URLEncoder.encode(productName, StandardCharsets.UTF_8);

                jdbcTemplate.update("""
                            INSERT INTO T_PRODUCT (product_id, product_name, description, price, stock_quantity, category_id, thumbnail_url, created_at, updated_at)
                            VALUES (?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
                        """, productId, productName, "설명: " + productName, price, stock, categoryId, imageUrl);

                jdbcTemplate.update("""
                            INSERT INTO T_PRODUCT_IMAGE (image_id, product_id, image_url, is_thumbnail, created_at)
                            VALUES (?, ?, ?, ?, CURRENT_TIMESTAMP)
                        """, UUID.randomUUID().toString(), productId, imageUrl, true);
            }
        }
    }

    private String getRandomColor() {
        List<String> colors = List.of("블랙", "화이트", "그레이", "네이비", "베이지");
        return getRandomElement(colors);
    }

    private <T> T getRandomElement(List<T> list) {
        return list.get(random.nextInt(list.size()));
    }
}