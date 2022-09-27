package moe.tree.eduservice.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("moe.tree.eduservice.mapper")
public class EduConfig {
}
