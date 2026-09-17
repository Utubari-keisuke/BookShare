package app;

import java.util.ArrayList;

import model.Book;
import service.BookManager;
import view.ConsoleView;

public class Main {
	public static void main(String[] args) {
		BookManager manager = new BookManager();
		ConsoleView view = new ConsoleView();

		// フラグ変数
		boolean isRunning = true;

		while (isRunning) {
			view.printMenu();
			int selectedMenu = view.inputNumber();

			// if-elseで書くと長くなるのでテキストおすすめのswitch文を採用
			switch (selectedMenu) {
			case 1:
				String inputTitle = view.inputString("登録する本のタイトルを入力: ");

				// 空白だけのときも弾くべきかもだけど一旦equalsで判定
				if (inputTitle.equals("")) {
					view.showMessage("エラー: タイトルが空文字です。登録を中断します。");
				} else {
					Book createdBook = manager.addBook(inputTitle);
					view.showMessage("『" + createdBook.getTitle() + "』を登録しました。(ID: " + createdBook.getId() + ")");
				}
				break;

			case 2:
				ArrayList<Book> currentList = manager.getBookList();
				view.displayAllBooks(currentList);
				break;

			case 3:
				view.showMessage("貸出・返却を切り替える本のIDを指定してください。");
				System.out.print("ID: ");
				int targetIdForBorrow = view.inputNumber();

				Book bookToBorrow = manager.findBookById(targetIdForBorrow);
				if (bookToBorrow == null) {
					view.showMessage("エラー: 該当するIDの本が存在しません。");
				} else {
					// テキストの三項演算子はまだ難しいから素直にif-else
					if (bookToBorrow.isBorrowed() == false) {
						String borrowerName = view.inputString("借りる人の名前を入力してください: ");
						bookToBorrow.setBorrowed(true);
						bookToBorrow.setBorrower(borrowerName);
						view.showMessage(borrowerName + " さんに本を貸し出しました。");
					} else {
						bookToBorrow.setBorrowed(false);
						bookToBorrow.setBorrower("なし");
						view.showMessage("本の返却を受け付けました。");
					}
				}
				break;

			case 4:
				view.showMessage("レビューを投稿する本のIDを指定してください。");
				System.out.print("ID: ");
				int targetIdForReview = view.inputNumber();

				Book bookToReview = manager.findBookById(targetIdForReview);
				if (bookToReview == null) {
					view.showMessage("エラー: 該当するIDの本が存在しません。");
				} else {
					int ratingValue = 0;
					while (true) {
						System.out.print("評価を1〜5の数値で入力してください: ");
						ratingValue = view.inputNumber();
						if (ratingValue >= 1 && ratingValue <= 5) {
							break; // 正常値なら抜ける！
						} else {
							view.showMessage("エラー: 範囲外の数値です。1から5で入力してください。");
						}
					}

					String reviewText = view.inputString("感想・レビュー本文を入力: ");
					bookToReview.setRating(ratingValue);
					bookToReview.setReview(reviewText);
					view.showMessage("レビュー情報を更新しました。");
				}
				break;

			case 5:
				view.showMessage("削除したい本のIDを指定してください。");
				System.out.print("ID: ");
				int targetIdForDelete = view.inputNumber();

				boolean isDeleted = manager.deleteBookById(targetIdForDelete);
				if (isDeleted == true) { // 冗長だけどわかりやすいのであえて == true と書く
					view.showMessage("指定された本を正常に削除しました。");
				} else {
					view.showMessage("エラー: 該当するIDの本が見つからなかったため、削除できませんでした。");
				}
				break;

			case 6:
				view.showMessage("プログラムを終了します。お疲れ様でした。");
				isRunning = false;
				break;

			default:
				view.showMessage("エラー: 1から6の番号を選択してください。");
				break;
			}
		}
	}
}