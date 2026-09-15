package BOOK;

public class Book {
	private int id;
	private String title;
	private boolean isborrowed;
	private String borrower;
	private int rating;
	private String review;

	public Book(int id, String title) {
		this.id = id;
		this.title = title;
		this.isborrowed = false;
		this.borrower = "なし"; //nullでもいいけど
		this.rating = 0;
		this.review = "未記入";
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public boolean isBorrowed() {
		return isborrowed;
	}

	public void setBorrowed(boolean isborrowed) {
		this.isborrowed = isborrowed;
	}

	public String getBorrower() {
		return borrower;
	}

	public void setBorrower(String borrower) {
		this.borrower = borrower;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

}
