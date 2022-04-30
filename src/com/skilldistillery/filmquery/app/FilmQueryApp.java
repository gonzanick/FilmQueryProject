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
//    app.test();
    app.launch();
  }

  private void test() {
    //db.findActorsByFilmId(1);
    System.out.println(db.findActorsByFilmId(22));
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
	  while(true) {
		  if (choice == 1) {
			  System.out.println("Please enter the film's ID number that you would like to pull up: ");
			  int movieId = input.nextInt();
			 System.out.println(db.findFilmById(movieId)); 
			  
			  
		  }
		  
	  }
     
  }

}
