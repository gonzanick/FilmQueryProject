package com.skilldistillery.filmquery.entities;

import java.util.List;
import java.util.Objects;

import com.skilldistillery.filmquery.database.DatabaseAccessorObject;

public class Film {
	private int id;
	private String title;
	private String descrip;
	private Integer releaseYear;
	private String lang;
	private int rentDur;
	private double rentRate;
	private Integer length;
	private double replaceCost;
	private String rating;
	private String specialFeat;
	private List<Actor> actors;

	public Film() {

	}

	public Film(int id, String title, String descrip, short releaseYear, int langID, int rentDur, double rentRate,
			int length, double replaceCost, String rating, String specialFeat) {
		DatabaseAccessorObject dao = new DatabaseAccessorObject();
		List<Actor> actor = dao.findActorsByFilmId(id);
		this.actors = actor;

	}
	
	public List<Actor> getActors() {
		return actors;
	}

	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}

//	public void setActor(Actor actor) {
//		this.actors.add(actor);
//	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescrip() {
		return descrip;
	}

	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}

	public int getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public int getRentDur() {
		return rentDur;
	}

	public void setRentDur(int rentDur) {
		this.rentDur = rentDur;
	}

	public double getRentRate() {
		return rentRate;
	}

	public void setRentRate(double rentRate) {
		this.rentRate = rentRate;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public double getReplaceCost() {
		return replaceCost;
	}

	public void setReplaceCost(double replaceCost) {
		this.replaceCost = replaceCost;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getSpecialFeat() {
		return specialFeat;
	}

	public void setSpecialFeat(String specialFeat) {
		this.specialFeat = specialFeat;
	}

	@Override
	public String toString() {
		return "Film title: " + title + ", rated " + rating + ", realease year " + releaseYear + "\n " + "Description: "
				+ descrip + ". " + "Language: " + lang + "\n" + "Cast: ";
	}

	@Override
	public int hashCode() {
		return Objects.hash(descrip, id, lang, length, rating, releaseYear, rentDur, rentRate, replaceCost,
				specialFeat, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Film other = (Film) obj;
		return Objects.equals(descrip, other.descrip) && id == other.id && lang == other.lang
				&& length == other.length && Objects.equals(rating, other.rating) && releaseYear == other.releaseYear
				&& rentDur == other.rentDur
				&& Double.doubleToLongBits(rentRate) == Double.doubleToLongBits(other.rentRate)
				&& Double.doubleToLongBits(replaceCost) == Double.doubleToLongBits(other.replaceCost)
				&& Objects.equals(specialFeat, other.specialFeat) && Objects.equals(title, other.title);
	}

}
