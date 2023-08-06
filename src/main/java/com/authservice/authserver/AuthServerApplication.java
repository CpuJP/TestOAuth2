package com.authservice.authserver;

import com.authservice.authserver.entity.Role;
import com.authservice.authserver.enums.RoleName;
import com.authservice.authserver.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuthServerApplication /*implementsCommandLineRunner*/{
   /* @Autowired
    RoleRepository roleRepository;*/

    public static void main(String[] args) {
        SpringApplication.run(AuthServerApplication.class, args);
    }

   /* @Override
    public void run(String... args) throws Exception {
        Role adminRole = Role.builder()
                .roleName(RoleName.ROLE_ADMIN)
                .build();
        Role userRole = Role.builder()
                .roleName(RoleName.ROLE_USER)
                .build();
        roleRepository.save(adminRole);
        roleRepository.save(userRole);    }*/
}
