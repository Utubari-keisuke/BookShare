import java.awt.print.Book;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

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
				booklist.add(newBook);
				System.out.println("『" + title + " 』を登録しました。(ID: " + nextId + ")");

				nextId++;
			}

			else if (menu == 2) {
				if (bookList.isEmpty()) {
					System.out.println("登録されている本はありません。");
					continue;
				}

				System.out.println("\n--- 本の一覧 ---");
				for (int i = 0; i < bookList.size(); i++) {
					Book b = bookList.get(i);
					String status = b.isBorrowed() ? "貸出中(" + b.getBorrower() + ")" : "貸出可能";
					System.out.println("ID: " + b.getId() + " | タイトル: " + b.getTitle() + " | 状態: " + status);
					if (b.getRating() > 0) {
						System.out.println("   評価: ★" + b.getRating() + " / 感想: " + b.getReview());
					}
				}
			}
		}
	}
}
