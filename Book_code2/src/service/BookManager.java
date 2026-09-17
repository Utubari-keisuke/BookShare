package service;

import java.util.ArrayList;

import model.Book;

public class BookManager { // リスト
	private ArrayList<Book> bookList;
	private int nextId;

	public BookManager() {
		this.bookList = new ArrayList<Book>();
		this.nextId = 1;
	}

	// 1
	public Book addBook(String title) {
		Book newBook = new Book(this.nextId, title);
		this.bookList.add(newBook);
		this.nextId = this.nextId + 1;
		return newBook;
	} //9月12日　完

	// 2
	public ArrayList<Book> getBookList() {
		return this.bookList;
	}

	// 3
	public Book findBookById(int id) {
		Book target = null;
		for (int i = 0; i < this.bookList.size(); i++) {
			Book b = this.bookList.get(i);
			if (b.getId() == id) {
				target = b;
				break;
			}
		}
		return target;
	}

	// 5
	public boolean deleteBookById(int id) {
		int targetIndex = -1;
		for (int i = 0; i < this.bookList.size(); i++) {
			if (this.bookList.get(i).getId() == id) {
				targetIndex = i;
				break;
			}
		}

		if (targetIndex != -1) {
			this.bookList.remove(targetIndex);
			return true;
		} else {
			return false;
		}
	}
}
//9月16日　完