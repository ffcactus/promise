package com.promise.platform.auth;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.promise.platform.auth.model.User;
import com.promise.platform.auth.repository.UserRepository;


@SpringBootApplication
public class AuthApplication implements CommandLineRunner {
//	@Autowired
//	private UserRepository userRepository;

	@Value("${self.db.recreate}")
	private boolean recreate;

	public static void main(String args[]) {
		SpringApplication.run(AuthApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*
		if (recreate) {
			userRepository.deleteAll();

			List<String> orgnazationA = Collections.singletonList("orgnazationA");
			List<String> orgnazationB = Collections.singletonList("orgnazationB");

			List<String> adminAuthorities = Collections.singletonList("ROLE_ADMIN");
			List<String> managerAuthorities = Collections.singletonList("ROLE_MANAGER");
			List<String> readerAuthorities = Collections.singletonList("ROLE_VIEWER");

			// admin in HP.
			userRepository.save(new User("id-admin1-hp-scope1", "admin1@hp.com", "password", "admin1@hp.com", "hp",
					orgnazationA, adminAuthorities));
			userRepository.save(new User("id-admin2-hp-scope2", "admin2@hp.com", "password", "admin2@hp.com", "hp",
					orgnazationB, adminAuthorities));

			// manager in HP.
			userRepository.save(new User("id-manager1-hp-scope1", "manager1@hp.com", "password", "manager1@hp.com",
					"hp", orgnazationA, managerAuthorities));
			userRepository.save(new User("id-manager2-hp-scope1", "manager2@hp.com", "password", "manager2@hp.com",
					"hp", orgnazationB, managerAuthorities));
			// reader in HP.
			userRepository.save(new User("id-reader1-hp-scope1", "reader1@hp.com", "password", "reader1@hp.com", "hp",
					orgnazationA, readerAuthorities));
			userRepository.save(new User("id-reader1-hp-scope2", "reader2@hp.com", "password", "reader2@hp.com", "hp",
					orgnazationB, readerAuthorities));

			// admin in HW.
			userRepository.save(new User("id-admin1-hw-scope1", "admin1@hw.com", "password", "admin1@hw.com", "hw",
					orgnazationA, adminAuthorities));
			userRepository.save(new User("id-admin2-hw-scope2", "admin2@hw.com", "password", "admin2@hw.com", "hw",
					orgnazationB, adminAuthorities));
			// manager in HW.
			userRepository.save(new User("id-manager1-hw-scope1", "manager1@hw.com", "password", "manager1@hw.com",
					"hw", orgnazationA, managerAuthorities));
			userRepository.save(new User("id-manager2-hw-scope1", "manager2@hw.com", "password", "manager2@hw.com",
					"hw", orgnazationB, managerAuthorities));
			// reader in HW.
			userRepository.save(new User("id-reader1-hw-scope1", "reader1@hw.com", "password", "reader1@hw.com", "hw",
					orgnazationA, readerAuthorities));
			userRepository.save(new User("id-reader1-hw-scope2", "reader2@hw.com", "password", "reader2@hw.com", "hw",
					orgnazationB, readerAuthorities));			
		}
		*/
	}
}
