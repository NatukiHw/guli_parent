package moe.tree.ucenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"moe.tree"})
@EnableDiscoveryClient
@MapperScan("moe.tree.ucenter.mapper")
public class UcenterApplication {
	public static void main(String[] args) {
		SpringApplication.run(UcenterApplication.class, args);
	}
}
