package moe.tree.educms;

import moe.tree.eduservice.EduApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@ComponentScan(basePackages = {"moe.tree"}, excludeFilters = {
	@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {EduApplication.class}),
	@ComponentScan.Filter(type = FilterType.ASPECTJ, pattern = "moe.tree.eduservice.controller.*")
})
@EnableDiscoveryClient
@MapperScan("moe.tree.educms.mapper")
public class EduCmsApplication {
	public static void main(String[] args) {
		SpringApplication.run(EduCmsApplication.class, args);
	}
}