package com.SaeedAndEmre.CS393Project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Cs393ProjectApplication {

	/*TODO!!!! --> Change all inputs and outputs from dtos to entities in Service layer,
	 * turns out we only need to use Dtos in controller
	 */
	public static void main(String[] args) {
		SpringApplication.run(Cs393ProjectApplication.class, args);
	}

}
