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

	@Bean
	CommandLineRunner init(UserEntityRepository userRepository, ProductRepository productRepository) {
		return args -> {
			// Create PERMISSIONS
			PermissionEntity createPermission = PermissionEntity.builder()
					.name("CREATE").build();

			PermissionEntity readPermission = PermissionEntity.builder()
					.name("READ").build();

			PermissionEntity updatePermission = PermissionEntity.builder()
					.name("UPDATE").build();

			PermissionEntity deletePermission = PermissionEntity.builder()
					.name("DELETE").build();

			PermissionEntity refactorPermission = PermissionEntity.builder()
					.name("REFACTOR").build();

			//Create ROLES
			RoleEntity roleAdmin = RoleEntity.builder()
					.roleEnum(RoleEnum.ADMIN)
					.permissionList(Set.of(createPermission, readPermission, updatePermission, deletePermission))
					.build();

			RoleEntity roleUser = RoleEntity.builder()
					.roleEnum(RoleEnum.USER)
					.permissionList(Set.of(createPermission, readPermission))
					.build();

			RoleEntity roleInvited = RoleEntity.builder()
					.roleEnum(RoleEnum.INVITED)
					.permissionList(Set.of(readPermission))
					.build();

			RoleEntity roleDeveloper = RoleEntity.builder()
					.roleEnum(RoleEnum.DEVELOPER)
					.permissionList(Set.of(
							createPermission, readPermission, updatePermission, deletePermission, refactorPermission))
					.build();

			//Create USERS
			UserEntity userSantiago = UserEntity.builder()
					.username("Santiago")
					.password("$2a$10$VthJCDyMhW/HGFrW9G7tGOw0wYAhEdVlJ8XwLWh0Xgilz/KSYxkGC")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleAdmin))
					.build();

			UserEntity userDaniel = UserEntity.builder()
					.username("Daniel")
					.password("$2a$10$VthJCDyMhW/HGFrW9G7tGOw0wYAhEdVlJ8XwLWh0Xgilz/KSYxkGC")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleUser))
					.build();

			UserEntity userAndrea = UserEntity.builder()
					.username("Andrea")
					.password("$2a$10$VthJCDyMhW/HGFrW9G7tGOw0wYAhEdVlJ8XwLWh0Xgilz/KSYxkGC")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleInvited))
					.build();

			UserEntity userAnyi = UserEntity.builder()
					.username("Anyi")
					.password("$2a$10$VthJCDyMhW/HGFrW9G7tGOw0wYAhEdVlJ8XwLWh0Xgilz/KSYxkGC")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleDeveloper))
					.build();

			userRepository.saveAll(List.of(userSantiago, userDaniel, userAndrea, userAnyi));

			//Create CATEGORIES
			Category clothes = Category.builder()
					.nameCategory("clothes")
					.build();
			Category rare = Category.builder()
					.nameCategory("rare")
					.build();
			Category special = Category.builder()
					.nameCategory("special")
					.build();
			Category premium = Category.builder()
					.nameCategory("premium")
					.build();

			//Create PRODUCTS
			Product tShirt = Product.builder()
					.title("T Shirt")
					.srcImg("tshirt.jpg")
					.description("Cotton Shirt")
					.price(12500)
					.categories(List.of(clothes, rare))
					.build();
			Product tShirtU = Product.builder()
					.title("T Shirt University")
					.srcImg("tshirtu.jpg")
					.description("Cotton Shirt Sublimated")
					.price(16000)
					.categories(List.of(clothes, premium, special))
					.build();
			Product tShirtB = Product.builder()
					.title("T Shirt Blue")
					.srcImg("tshirtb.jpg")
					.description("Blue Cotton Shirt")
					.price(13500)
					.categories(List.of(clothes, rare, special))
					.build();
			Product coat = Product.builder()
					.title("Coat")
					.srcImg("coat.jpg")
					.description("Coat")
					.price(15000)
					.categories(List.of(clothes, rare))
					.build();
			Product coatC = Product.builder()
					.title("Cotton Coat")
					.srcImg("coatC.jpg")
					.description("Cotton Coat")
					.price(18000)
					.categories(List.of(special, clothes))
					.build();
			Product shoe = Product.builder()
					.title("Shoe sport")
					.srcImg("shoe.jpg")
					.description("Football Shoe")
					.price(25000)
					.categories(List.of(clothes, rare, special, premium))
					.build();

			productRepository.saveAll(List.of(tShirt, tShirtU, tShirtB, coat, coatC, shoe));

		};
	}
}
