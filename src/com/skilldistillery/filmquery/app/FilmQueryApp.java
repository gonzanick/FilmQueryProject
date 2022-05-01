package com.skilldistillery.filmquery.app;

import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {
	Scanner input = new Scanner(System.in);

	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) {
		FilmQueryApp app = new FilmQueryApp();
//		app.test();
		app.launch();
	}

	private void test() {
		// db.findActorsByFilmId(1);
		System.out.println(db.findActorsByFilmId(1));
	}

	private void launch() {

		startUserInterface(input);

		input.close();
	}

	private void startUserInterface(Scanner input) {
		System.out.println("       Menu           ");
		System.out.println("1. Look up film by ID. ");
		System.out.println("2. Look up film by a search keyword.");
		System.out.println("3. Exit");
		int choice = input.nextInt();
		input.nextLine();
		while (true) {
			if (choice == 1) {
				System.out.println("Please enter the film's ID number that you would like to pull up: ");
				int movieId = input.nextInt();
				if (db.findFilmById(movieId) == null) {
					System.out.println("No film on record. Please try again.");
					continue;
				}
				
				System.out.println(db.findFilmById(movieId));
				System.out.println(db.findActorsByFilmId(movieId));

			} else if (choice == 2) {
				System.out.println("Please enter in a keyword to look up a film: ");
				String keyW = input.next();
				if (db.searchByKeyword(keyW) == null) {
					System.out.println("No film found. Please try again.");
					continue;
				}
				System.out.println(db.searchByKeyword(keyW));

			} else if (choice == 3) {
				System.out.println("You have exited the program.");
				break;
			
			} 

		}

	}

}
