package com.jrendulic.Vrello;

import com.jrendulic.Vrello.model.*;
import com.jrendulic.Vrello.service.BoardService;
import com.jrendulic.Vrello.service.CardService;
import com.jrendulic.Vrello.service.LineService;
import com.jrendulic.Vrello.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@SpringBootApplication
public class  VrelloApplication {
	public static void main(String[] args) {
		SpringApplication.run(VrelloApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(UserService userService, BoardService boardService, LineService lineService, CardService cardService) {
		return args -> {
			userService.addRole(new Role(null, "ROLE_USER"));
			userService.addRole(new Role(null, "ROLE_ADMIN"));
			userService.addRole(new Role(null, "ROLE_MANAGER"));

			userService.addUser(new User(null, "Josip Josipovic", "josip", "1234", new HashSet<>(), new HashSet<>() ));
			userService.addUser(new User(null, "Admin Adminovic", "admin", "1234", new HashSet<>(), new HashSet<>()));

			userService.addRoleToUser("josip", "ROLE_USER");
			userService.addRoleToUser("josip", "ROLE_MANAGER");
			userService.addRoleToUser("admin", "ROLE_ADMIN");


			boardService.addBoard(new Board(null, "Reschedule meeting project", null, null, null));
			boardService.addBoard(new Board(null, "Greeting page smt project", null, null, null));

			boardService.addMemberToBoard(1L, 1L);
			boardService.addMemberToBoard(1L, 2L);
			boardService.addMemberToBoard(2L, 1L);
			boardService.addMemberToBoard(2L, 2L);

			lineService.addLine(new Line(null, "TODO", null, null));
			lineService.addLine(new Line(null, "IN PROGRESS", null, null));
			lineService.addLine(new Line(null, "CODE REVIEW", null, null));
			lineService.addLine(new Line(null, "READY FOR QA", null, null));
			lineService.addLine(new Line(null, "DONE", null, null));

			boardService.addLineToBoard("Reschedule meeting project", "TODO");
			boardService.addLineToBoard("Reschedule meeting project", "DONE");
			boardService.addLineToBoard("Greeting page smt project", "TODO");
			boardService.addLineToBoard("Greeting page smt project", "CODE REVIEW");
			boardService.addLineToBoard("Greeting page smt project", "READY FOR QA");

			cardService.addCard(new Card(null, "RMPr-12", "add schedules for all users", null, null, null));	cardService.addCardToLine(1L, 1L);
			cardService.addCard(new Card(null, "RMPr-13", "remove unnecessary schedules", null, null, null));	cardService.addCardToLine(1L, 2L);
			cardService.addCard(new Card(null, "RMPr-14", "recreate meetings", null, null, null)); cardService.addCardToLine(5L, 3L);
			cardService.addCard(new Card(null, "GPg-3", "add meetings and appointments to schedules", null, null, null)); 	cardService.addCardToLine(1L, 4L);
			cardService.addCard(new Card(null, "GPg-21", "remove old meetings", null, null, null)); cardService.addCardToLine(3L, 5L);
			cardService.addCard(new Card(null, "GPg-7", "check data", null, null, null)); 	cardService.addCardToLine(4L, 6L);

			cardService.addCardToBoard(1L, 1L);
			cardService.addCardToBoard(1L, 2L);
			cardService.addCardToBoard(1L, 3L);
			cardService.addCardToBoard(2L, 4L);
			cardService.addCardToBoard(2L, 5L);
			cardService.addCardToBoard(2L, 6L);

		};
	}
}
