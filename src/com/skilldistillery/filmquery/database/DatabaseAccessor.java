package com.skilldistillery.filmquery.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public interface DatabaseAccessor {
	public Film findFilmById(int filmId);

	public List<Actor> getActors(int actorId) throws SQLException;

	public List<Actor> findActorsByFilmId(int filmId);

	public List<Film> searchByKeyword(String keyWord);
	
//	public List<Actor> findActorsByFilmId(String keyWord);
}
