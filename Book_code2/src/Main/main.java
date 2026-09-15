package Main;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import BOOK.Book;

public class main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		ArrayList<Book> books = new ArrayList<>();
		int nextId = 1;

		while (true) {
			System.out.println("\n=== 技術書シェア＆レビュー管理 ===");
			System.out.println("1. 本を登録する");
			System.out.println("2. 本の一覧を見る");
			System.out.println("3. 貸出/返却の切り替え");
			System.out.println("4. 本の評価とレビューを書く");
			System.out.println("5. 本を削除する");
			System.out.println("6. 終了");
			System.out.print("番号を選択してください: ");

			int menu = -1;
			try {
				menu = scanner.nextInt();
				scanner.nextLine(); // 改行の読み飛ばし
			} catch (InputMismatchException e) {
				System.out.println("半角数字を入力してください。");
				scanner.nextLine();
				continue;
			}

			if (menu == 0) {
				System.out.println("アプリを終了します。");
				break;
			}

			else if (menu == 1) {
				System.out.print("本のタイトルを入力してください: ");
				String title = scanner.nextLine();

				if (title.isEmpty()) {
					System.out.println("タイトルが入力されていません。");
					continue;
				}

				Book newBook = new Book(nextId, title);
				books.add(newBook);
				System.out.println("『" + title + " 』を登録しました。(ID: " + nextId + ")");

				nextId++;
			}

			else if (menu == 2) {
				if (books.isEmpty()) {
					System.out.println("登録されている本はありません。");
					continue;
				}

				System.out.println("\n--- 本の一覧 ---");
				for (int i = 0; i < books.size(); i++) {
					Book b = books.get(i);
					String status = b.isBorrowed() ? "貸出中(" + b.getBorrower() + ")" : "貸出可能";
					System.out.println("ID: " + b.getId() + " | タイトル: " + b.getTitle() + " | 状態: " + status);
					if (b.getRating() > 0) {
						System.out.println("   評価: ★" + b.getRating() + " / 感想: " + b.getReview());

					}
				}
			}

			else if (menu == 3) {
				System.out.print("変更したい本のIDを入力: ");
				int targetId = -1;
				try {
					targetId = scanner.nextInt();
					scanner.nextLine();
				} catch (InputMismatchException e) {
					System.out.println("エラー: 数字を入力してください。");
					scanner.nextLine();
					continue;
				}

				Book targetBook = null;
				for (int i = 0; i < books.size(); i++) {
					if (books.get(i).getId() == targetId) {
						targetBook = books.get(i);
						break;
					}
				}

				if (targetBook == null) {
					System.out.println("指定されたIDの本は見つかりませんでした。");
					continue;
				}

				if (!targetBook.isBorrowed()) {
					System.out.print("借りる人の名前を入力: ");
					String name = scanner.nextLine();
					targetBook.setBorrowed(true);
					targetBook.setBorrower(name);
					System.out.println(name + "さんに貸出しました。");
				} else {
					System.out.println("返却処理を行いました。");
					targetBook.setBorrowed(false);
					targetBook.setBorrower("なしです");
				}
			}
		}
	}
}
