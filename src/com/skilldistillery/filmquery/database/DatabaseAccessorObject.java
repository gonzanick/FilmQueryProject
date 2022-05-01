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

	public Film findFilmById(int filmId) {
		Film film = null;
		try {
			String user = "student";
			String pass = "student";
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sql = "SELECT title,first_name, last_name, description, release_year, language_id,language.name, rating, film.id "
					+ "FROM film JOIN film_actor ON film.id = film_actor.film_id "
					+ "JOIN language ON film.language_id = language.id "
					+ "JOIN actor ON actor.id = film_actor.actor_id WHERE film_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				film = new Film();
				film.setTitle(rs.getString("title"));
				film.setDescrip(rs.getString("description"));
				film.setRating(rs.getString("rating"));
				film.setReleaseYear(rs.getShort("release_year"));
				film.setLang(rs.getString("language.name"));
				film.setActors(getActors(filmId));
//				film.setId(rs.getInt("id"));
//				int rentDur = rs.getInt(6);
//				double rentRate = rs.getDouble(7);
//				Integer length = rs.getInt(8);
//				double replaceCost = rs.getDouble(9);

			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return film;
	}

	public List<Actor> getActors(int filmId) {
		List<Actor> actors = new ArrayList<>();
		Actor actor = null;
		String user = "student";
		String pass = "student";
		Connection conn;
		try {
			conn = DriverManager.getConnection(URL, user, pass);

			String sql = "SELECT first_name, last_name "
					+ "FROM actor JOIN film_actor "
					+ "ON actor.id = film_actor.actor_id  WHERE film_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet actorResult = stmt.executeQuery();
			while (actorResult.next()) {
				actor = new Actor();
				actor.setFirstName(actorResult.getString("first_name"));
				actor.setLastName(actorResult.getString("last_name"));
				actors.add(actor);

			}
			actorResult.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return actors;
	}

	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> actors = new ArrayList<>();
		Actor actor = null;
		try {
			String user = "student";
			String pass = "student";
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sql = "SELECT first_name, last_name FROM actor JOIN film_actor ON actor.id = film_actor.actor_id"
					+ " WHERE film_id = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				actor = new Actor();
				actor.setFirstName(rs.getString("first_name"));
				actor.setLastName(rs.getString("last_name"));
				actors.add(actor);

			}
			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return actors;

	}
//	public List<Actor> findActorsByFilmId(String keyWord) {
//		List<Actor> actors = new ArrayList<>();
//		Actor actor = null;
//		try {
//			String user = "student";
//			String pass = "student";
//			Connection conn = DriverManager.getConnection(URL, user, pass);
//			String sql = "SELECT first_name, last_name FROM actor JOIN film_actor ON actor.id = film_actor.actor_id"
//					+ " WHERE film_id = ?";
//			
//			PreparedStatement stmt = conn.prepareStatement(sql);
//			stmt.setString(1, keyWord);
//			ResultSet rs = stmt.executeQuery();
//			while (rs.next()) {
//				actor = new Actor();
//				actor.setFirstName(rs.getString("first_name"));
//				actor.setLastName(rs.getString("last_name"));
//				actors.add(actor);
//				
//			}
//			rs.close();
//			stmt.close();
//			conn.close();
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		return actors;
//		
//	}

	public List<Film> searchByKeyword(String keyWord) {
		List<Film> listOFilms = new ArrayList<>();
		Film film = null;
		try {
			String user = "student";
			String pass = "student";
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sql = "SELECT film.id, title, description, release_year, language_id,language.name, rating "
					+ "FROM film JOIN language ON film.language_id = language.id "
					+ "WHERE title LIKE ? OR description LIKE ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%" + keyWord + "%");
			stmt.setString(2, "%" + keyWord + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				film = new Film();
				film.setTitle(rs.getString("title"));
				film.setDescrip(rs.getString("description"));
				film.setRating(rs.getString("rating"));
				film.setReleaseYear(rs.getShort("release_year"));
				film.setLang(rs.getString("language.name"));
				film.setActors(getActors(rs.getInt("film.id")));
				listOFilms.add(film);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listOFilms;
	}

}
