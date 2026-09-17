package view;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import model.Book;

public class ConsoleView {
	private Scanner scanner;

	public ConsoleView() {
		this.scanner = new Scanner(System.in);
	}

	public void printMenu() {
		System.out.println("");
		System.out.println("=== 技術書シェア＆レビュー管理 ===");
		System.out.println("1. 本を登録する (Create)");
		System.out.println("2. 本の一覧を見る (Read)");
		System.out.println("3. 貸出/返却の切り替え (Update)");
		System.out.println("4. 本の評価とレビューを書く (Update)");
		System.out.println("5. 本を削除する (Delete)");
		System.out.println("6. 終了");
		System.out.print("操作したい番号を入力してください: ");
	}

	public int inputNumber() {
		int number = -1;
		while (true) {
			try {
				number = this.scanner.nextInt();
				// 【伊織さん談】nextInt()の後に改行コードが残るせいで次のnextLine()が空振りする現象を防ぐんだね
				this.scanner.nextLine();
				break;
			} catch (InputMismatchException e) {
				// 文字列が打ち込まれたときの対策
				System.out.println("[入力エラー] 半角数字で入力してください。");
				this.scanner.nextLine(); // バッファに残ったゴミ文字列を捨てる
				System.out.print("再度入力してください: ");
			}
		}
		return number;
	}

	public String inputString(String message) {
		System.out.print(message);
		String text = this.scanner.nextLine();
		return text;
	}

	public void displayAllBooks(ArrayList<Book> list) {
		if (list.size() == 0) { // isEmpty()でもいいけどサイズ0で比較
			System.out.println("登録されている本は1冊もありません。");
			return;
		}

		System.out.println("\n--- 登録されている技術書一覧 ---");
		for (int i = 0; i < list.size(); i++) {
			Book b = list.get(i);
			String status = "";
			if (b.isBorrowed() == true) {
				status = "貸出中 (借用者: " + b.getBorrower() + ")";
			} else {
				status = "貸出可能";
			}

			System.out.println("ID: " + b.getId() + " | 書名: " + b.getTitle() + " | 貸出状態: " + status);
			// 評価が0（未設定）なら出さない
			if (b.getRating() > 0) {
				System.out.println("  -> 評価: " + b.getRating() + "点 / レビュー: " + b.getReview());
			}
		}
	}

	public void showMessage(String message) {
		System.out.println(message);
	}
}
