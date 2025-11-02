package com.max.carpincho;

import com.max.carpincho.persistence.entity.Category;
import com.max.carpincho.persistence.entity.Product;
import com.max.carpincho.persistence.repository.ProductRepository;
import com.max.carpincho.security.persistence.entity.PermissionEntity;
import com.max.carpincho.security.persistence.entity.RoleEntity;
import com.max.carpincho.security.persistence.entity.RoleEnum;
import com.max.carpincho.security.persistence.entity.UserEntity;
import com.max.carpincho.security.persistence.repository.UserEntityRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Set;


@SpringBootApplication
public class CarpinchoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarpinchoApplication.class, args);
	}

//	@Bean
//	CommandLineRunner init() {
//		return args -> {};
//	}
}
