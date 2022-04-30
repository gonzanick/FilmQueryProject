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
			String sql = "SELECT id, title, description, release_year, language_id, ";
			sql += " rating "
					+ " FROM film JOIN film_actor ON film.id = film_actor.film_id " + " WHERE actor_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				film = new Film();
//				film.setId(rs.getInt("id"));
				film.setTitle(rs.getString("title"));
				film.setDescrip(rs.getString("description"));
				film.setRating(rs.getString("rating"));
				film.setReleaseYear(rs.getShort("release_year"));
				film.setLangID(rs.getInt("language_id"));
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

	public Actor findActorById(int actorId) {
		Actor actor = null;
		String user = "student";
		String pass = "student";
		Connection conn;
		try {
			conn = DriverManager.getConnection(URL, user, pass);

			String sql = "SELECT id, first_name, last_name FROM actor WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actorId);
			ResultSet actorResult = stmt.executeQuery();
			while (actorResult.next()) {
				actor = new Actor();
				actor.setId(actorResult.getInt("id"));
				actor.setFirstName(actorResult.getString("first name"));
				actor.setLastName(actorResult.getString("last name"));

			}
			actorResult.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return actor;
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

}
