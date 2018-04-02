package com.csipon.erp;

import com.csipon.erp.data.RoleRepository;
import com.csipon.erp.data.UserRepository;
import com.csipon.erp.models.Role;
import com.csipon.erp.models.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ErpApplicationTests {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;


	@Test
	public void contextLoads() {

		Role admin = Role.builder()
				.role("ROLE_ADMIN")
				.description("The main person in system")
				.build();
		roleRepository.save(admin);
		User user = User.builder()
				.role(admin)
				.login("pasha")
				.password(passwordEncoder.encode("qwer1234"))
				.firstName("Pasha")
				.middleName("Pasha")
				.lastName("Pasha")
				.build();
		userRepository.save(user);

		User pasha = userRepository.findUserByLogin("pasha");
		System.out.println(pasha.toString());
	}

}
