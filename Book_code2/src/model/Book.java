package model;

public class Book { // private指定
	private int id;
	private String title;
	private boolean isBorrowed;
	private String borrower;
	private int rating;
	private String review;

	public Book(int id, String title) {
		this.id = id;
		this.title = title;
		this.isBorrowed = false;
		this.borrower = "null"; //なし から代用
		this.rating = 0;
		this.review = "未記入";
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isBorrowed() {
		return this.isBorrowed;
	}

	public void setBorrowed(boolean isBorrowed) {
		this.isBorrowed = isBorrowed;
	}

	public String getBorrower() {
		return this.borrower;
	}

	public void setBorrower(String borrower) {
		this.borrower = borrower;
	}

	public int getRating() {
		return this.rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getReview() {
		return this.review;
	}

	public void setReview(String review) {
		this.review = review;
	}
}

//9月11日　完