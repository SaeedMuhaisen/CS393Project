package com.SaeedAndEmre.CS393Project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Cs393ProjectApplication {
	//TODO: can we use carrepo from inside reservationservices or must be reservationrep
	//TODO: for 2 item DTOs, can we use RequestParam x2? or 1 RequestBody and 1 pathvariable? (check addservice and equipments in reservations

	public static void main(String[] args) {
		SpringApplication.run(Cs393ProjectApplication.class, args);
	}

}
