package com.qa.blockbuster.domain;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity

public class Blockbuster {

	public Blockbuster() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String movieName;
	private String movieRelDate;
	private int movieRating;

	public Blockbuster(String movieName, String movieRelDate, int movieRating) {
		super();
		this.movieName = movieName;
		this.movieRelDate = movieRelDate;
		this.movieRating = movieRating;
	}

	public Blockbuster(Long id, String movieName, String movieRelDate, int movieRating) {
		super();
		this.id = id;
		this.movieName = movieName;
		this.movieRelDate = movieRelDate;
		this.movieRating = movieRating;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getMovieRelDate() {
		return movieRelDate;
	}

	public void setMovieRelDate(String movieRelDate) {
		this.movieRelDate = movieRelDate;
	}

	public int getMovieRating() {
		return movieRating;
	}

	public void setMovieRating(int movieRating) {
		this.movieRating = movieRating;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, movieName, movieRating, movieRelDate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Blockbuster other = (Blockbuster) obj;
		return id == other.id && Objects.equals(movieName, other.movieName) && movieRating == other.movieRating
				&& Objects.equals(movieRelDate, other.movieRelDate);
	}

	@Override
	public String toString() {
		return "Blockbuster [id=" + id + ", movieName=" + movieName + ", movieRelDate=" + movieRelDate
				+ ", movieRating=" + movieRating + "]";
	}

}
