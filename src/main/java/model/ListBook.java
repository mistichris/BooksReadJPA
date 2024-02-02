package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Misti Christianson - mchristianson
 * CIS175 - Spring 2024
 * Jan 31, 2024
 */
@Entity	//creates a new table
@Table(name="books")		//names the table
public class ListBook {
	@Id							//Marks as primary key field
	@GeneratedValue					//Auto generates the primary key in the database
	@Column(name="ID")			//Column Heading Name
	private int id;					//field variable
	@Column(name="AUTHOR")		//Column Heading Name
	private String author;			//field variable
	@Column(name="BOOK")		//Column Heading Name
	private String book;			//field variable
//	@Column(name="GENRE")
//	private String genre;
//	@Column(name="RATING")
//	private int rating;
	
	public ListBook() {			//empty constructor
		super();
	}
	
	public ListBook(String author, String book) {			//accepts arguments constructor
		super();
		this.author = author;
		this.book = book;
//		this.genre = genre;
//		this.rating = rating;
	}
	
	public String returnBookDetails() {					//returns string of book details
		return this.book + ": By " + this.author;
//				+ ", Genre: " + genre + ", Rating: " + rating;
	}

	
	//Getters and Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBook() {
		return book;
	}

	public void setBook(String book) {
		this.book = book;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
//		public String getGenre() {
//		return genre;
//	}
//
//	public void setGenre(String genre) {
//		this.genre = genre;
//	}
//
//	public int getRating() {
//		return rating;
//	}
//
//	public void setRating(int rating) {
//		this.rating = rating;
//	}

	@Override
	public String toString() {
		return "Listauthor [id=" + id + ", author=" + author + ", book=" + book +"]";
				
//				+ ", genre=" + genre + ", rating="
//				+ rating + "]";
	}

	
	
	
	
}
