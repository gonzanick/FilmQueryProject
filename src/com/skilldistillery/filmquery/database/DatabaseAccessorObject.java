package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {
	
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=US/Mountain";

	public DatabaseAccessorObject() throws ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
	}

  
  public Actor findActorById(int actorId) {
	  Actor actor = null;
	  String user = "student";
	  String pass = "student";
    Connection conn;
	try {
		conn = DriverManager.getConnection(URL, user, pass);
	  
	  String sql = "SELECT id, first_name, last_name FROM actor WHERE id = ?";
	  PreparedStatement stmt = conn.prepareStatement(sql);
	  stmt.setInt(1,actorId);
	  ResultSet actorResult = stmt.executeQuery();
	  if (actorResult.next()) {
	    actor = new Actor(); // Create the object
	    // Here is our mapping of query columns to our object fields:
	    actor.setId(actorResult.getInt(1));
	    actor.setFirstName(actorResult.getString(2));
	    actor.setLastName(actorResult.getString(3));
	    actor.setFilm(findFilmsByActorId(actorId)); // An Actor has Films
	  }
	    stmt.close();
	    conn.close();
	  
	} catch (SQLException e) {
		e.printStackTrace();
	}
	  //...
	  return actor;
	}
  
  
  
  
  public Film findFilmById(int actorId) {
	  List<Film> films = new ArrayList<>();
	  try {
		  String user = "student";
		  String pass = "student";
	    Connection conn = DriverManager.getConnection(URL, user, pass);
	    String sql = "SELECT id, title, description, release_year, language_id, rental_duration, ";
	                sql += " rental_rate, length, replacement_cost, rating, special_features "
	               +  " FROM film JOIN film_actor ON film.id = film_actor.film_id "
	               + " WHERE actor_id = ?";
	    PreparedStatement stmt = conn.prepareStatement(sql);
	    stmt.setInt(1, actorId);
	    ResultSet rs = stmt.executeQuery();
	    while (rs.next()) {
	      int id = rs.getInt(1);
	      String title = rs.getString("title");
	      String descrip = rs.getString("description");
	      short releaseYear = rs.getShort(4);
	      int langId = rs.getInt(5);
	      int rentDur = rs.getInt(6);
	      double rentRate = rs.getDouble(7);
	      int length = rs.getInt(8);
	      double replaceCost = rs.getDouble(9);
	      String rating = rs.getString("rating");
	      String specialFeat = rs.getString("special_features");
	      Film film = new Film(id, title, descrip, releaseYear, langId,
	                           rentDur, rentRate, length, replaceCost, rating, specialFeat);
	      films.add(film);
	    }
	    rs.close();
	    stmt.close();
	    conn.close();
	  } catch (SQLException e) {
	    e.printStackTrace();
	  }
	  return films;
	}


@Override
public List<Actor> findActorsByFilmId(int filmId) {
	// TODO Auto-generated method stub
	return null;
}



}
