package com.project.config.initDB;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * packageName    : com.project.config
 * fileName       : InitDBScriptExecutor
 * author         : Administrator
 * date           : 2025-05-10
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-05-10        Administrator       최초 생성
 */

@Component
@RequiredArgsConstructor
public class InitDBScriptExecutor {

    private final DataSource dataSource;

    private final Logger logger = LoggerFactory.getLogger(InitDBScriptExecutor.class);

//    @PostConstruct // H2에서만
    public void init() {
        try (Connection connection = dataSource.getConnection()) {
            ScriptUtils.executeSqlScript(connection, new ClassPathResource("init-db/initDB-H2.sql"));
            logger.info("✅ initDB-H2.sql executed successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("❌ Failed to execute initDB-H2.sql");
        }
    }
}