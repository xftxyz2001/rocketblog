package com.xftxyz.rocketblog.service.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.jdbc.ScriptRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import com.xftxyz.rocketblog.exception.file.FileException;
import com.xftxyz.rocketblog.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    DataSource dataSource;

    @Value("xftxyz.sqlscript")
    String sqlScript;

    // 日志文件路径
    @Value("xftxyz.logfilepath")
    String logFilePath;

    @Override
    public void resetDatabase() {
        try (Connection connection = dataSource.getConnection()) {
            // 读取数据库重置脚本
            Resource resource = new ClassPathResource(sqlScript);
            String script = StreamUtils.copyToString(resource.getInputStream(), Charset.forName("UTF-8"));
            // 执行数据库脚本
            ScriptRunner runner = new ScriptRunner(connection);
            runner.runScript(new StringReader(script));

        } catch (IOException e) {
            throw new FileException("数据库重置脚本读取失败");
        } catch (SQLException e) {
            throw new FileException("数据库重置失败");
        }
    }

    @Override
    public void deleteLogFiles() {
        // 清空日志文件
        File logFile = new File(logFilePath);
        if (logFile.exists()) {
            try (FileWriter writer = new FileWriter(logFile)) {
                writer.write("");
            } catch (IOException e) {
                throw new FileException("日志文件清空失败");
            }
        }
    }

}
