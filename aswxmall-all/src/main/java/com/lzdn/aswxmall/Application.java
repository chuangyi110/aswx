package com.lzdn.aswxmall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = {"com.lzdn.aswxmall"})
@MapperScan("com.lzdn.aswxmall.db.dao")
@EnableTransactionManagement
@EnableScheduling
public class Application {
    /**添加一个备注**/
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

}