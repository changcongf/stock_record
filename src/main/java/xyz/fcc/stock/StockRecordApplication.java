package xyz.fcc.stock;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("xyz.fcc.stock.record.mapper") // 👈 扫描 Mapper 接口所在包
public class StockRecordApplication {
    public static void main(String[] args) {
        SpringApplication.run(StockRecordApplication.class, args);
    }

}
