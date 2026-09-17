# 技術書シェア＆レビュー管理システム (CLI)

Javaの基礎構文の定着とオブジェクト指向の理解を深めるために制作した、コンソール（CUI）上で動作する書籍・レビュー管理アプリケーションです。  
テキストで学んだ制御構文、コレクション、例外処理、クラス分割を実践に落とし込むことを目的としています。

---

## 📌 主な機能 (CRUD)

テキストの定番であるCRUD（作成・読取・更新・削除）の操作を網羅しています。

- **C (Create / 登録)**: 新しい技術書のタイトルを登録（IDは自動採番）
- **R (Read / 一覧表示)**: 登録されている書籍の一覧、貸出状況、レビューの閲覧
- **U (Update / 更新)**: 
  - 書籍の貸出／返却ステータスの切り替え（借り手の名前管理）
  - 1〜5段階の評価およびレビューテキストの追加
- **D (Delete / 削除)**: 指定したIDの書籍データを削除

---

## 🛠 技術スタック・学習テーマ

- **言語**: Java (Java SE 17 / 21)
- **開発環境**: Eclipse
- **実践した主な文法・概念**:
  - **制御構文**: `while` によるメインループ、`switch-case` によるメニュー分岐、`for` ループによる線形探索
  - **コレクション**: `ArrayList` による動的なデータ管理
  - **オブジェクト指向 / カプセル化**: 
    - `private` フィールドと Getter / Setter
    - 単一責任を意識したクラス分割 (`app`, `service`, `view`, `model`)
  - **例外処理**: `Scanner` 利用時の `InputMismatchException` のハンドリングとバッファクリア

---

## 📂 クラス構成
```text
src/
├── app/
│   └── Main.java         # プログラムの起点、メインループとメニュー制御
├── service/
│   └── BookManager.java  # 本のリスト操作、検索、CRUD処理（ロジック層）
├── view/
│   └── ConsoleView.java  # 標準入出力、Scannerの制御、例外処理（画面層）
└── model/
    └── Book.java         # 本のデータ構造（タイトル、貸出状態、レビュー等））
``` 

## 手順
```mermaid 
flowchart TD
    Start([アプリ起動]) --> Init[Manager / View の初期化]
    Init --> ShowMenu[メニュー表示: ConsoleView]
    ShowMenu --> InputMenu[番号入力: 1〜6]

    InputMenu --> Choice{選択した番号}
    
    Choice -->|1| F1[本を登録する: Create]
    Choice -->|2| F2[本の一覧を見る: Read]
    Choice -->|3| F3[貸出/返却を切り替える: Update]
    Choice -->|4| F4[評価・レビューを書く: Update]
    Choice -->|5| F5[本を削除する: Delete]
    Choice -->|6| Exit([アプリ終了])
    Choice -->|その他| ErrMsg[無効な番号のエラー表示]

    F1 --> ShowMenu
    F2 --> ShowMenu
    F3 --> ShowMenu
    F4 --> ShowMenu
    F5 --> ShowMenu
    ErrMsg --> ShowMenu
```
```mermaid 
sequenceDiagram
    autonumber
    actor User as ユーザー
    participant V as ConsoleView
    participant M as Main
    participant B as BookManager

    User->>V: メニュー「1」を選択
    M->>V: inputString("タイトルの入力要求")
    User->>V: 書籍タイトルを入力
    V-->>M: タイトル文字列を返却
    
    alt タイトルが空文字の場合
        M->>V: showMessage("登録中断エラー")
    else タイトルが正常な場合
        M->>B: addBook(title)
        Note over B: IDを自動採番 (+1)<br/>Bookインスタンス生成<br/>ArrayListに追加
        B-->>M: 生成したBookオブジェクト
        M->>V: showMessage("登録完了メッセージ (ID付き)")
    end
```
```mermaid 
sequenceDiagram
    autonumber
    actor User as ユーザー
    participant V as ConsoleView
    participant M as Main
    participant B as BookManager

    User->>V: メニュー「2」を選択
    M->>B: getBookList()
    B-->>M: ArrayList<Book> を返却
    M->>V: displayAllBooks(currentList)
    
    alt リストのサイズが 0 の場合
        V->>User: 「登録されている本はありません」と表示
    else 1冊以上存在する場合
        loop 本の件数分ループ
            V->>User: ID / 書名 / 貸出状態 / レビューを出力
        end
    end
```
```mermaid 
sequenceDiagram
    autonumber
    actor User as ユーザー
    participant V as ConsoleView
    participant M as Main
    participant B as BookManager

    User->>V: メニュー「3」を選択
    M->>V: inputNumber("対象本のID入力要求")
    User->>V: IDを入力
    V-->>M: ID数値を返却
    M->>B: findBookById(targetId)
    B-->>M: Book または null

    alt 本が見つからない場合 (null)
        M->>V: showMessage("存在しませんエラー")
    else 本が見つかった場合
        alt 未貸出の場合 (isBorrowed == false)
            M->>V: inputString("借用者名入力要求")
            User->>V: 名前を入力
            V-->>M: 名前文字列
            M->>M: book.setBorrowed(true)<br/>book.setBorrower(名前)
            M->>V: showMessage("貸出完了")
        else すでに貸出中の場合 (isBorrowed == true)
            M->>M: book.setBorrowed(false)<br/>book.setBorrower("なし")
            M->>V: showMessage("返却完了")
        end
    end
```
```mermaid 
sequenceDiagram
    autonumber
    actor User as ユーザー
    participant V as ConsoleView
    participant M as Main
    participant B as BookManager

    User->>V: メニュー「4」を選択
    M->>V: inputNumber("対象本のID入力要求")
    User->>V: IDを入力
    V-->>M: ID数値を返却
    M->>B: findBookById(targetId)
    B-->>M: Book または null

    alt 本が見つからない場合 (null)
        M->>V: showMessage("存在しませんエラー")
    else 本が見つかった場合
        loop 1〜5の数値が入るまで繰り返し
            M->>V: inputNumber("評価(1〜5)入力要求")
            User->>V: 評価数値を入力
            V-->>M: 数値
        end
        M->>V: inputString("感想・レビュー入力要求")
        User->>V: 感想文を入力
        V-->>M: 文字列
        M->>M: book.setRating(数値)<br/>book.setReview(文字列)
        M->>V: showMessage("レビュー保存完了")
    end
```
```mermaid 
sequenceDiagram
    autonumber
    actor User as ユーザー
    participant V as ConsoleView
    participant M as Main
    participant B as BookManager

    User->>V: メニュー「5」を選択
    M->>V: inputNumber("削除するID入力要求")
    User->>V: IDを入力
    V-->>M: ID数値を返却
    M->>B: deleteBookById(targetId)
    
    Note over B: リスト内を線形探索<br/>一致したら remove(index)
    
    alt 削除成功 (true)
        B-->>M: true
        M->>V: showMessage("削除完了メッセージ")
    else 該当なし (false)
        B-->>M: false
        M->>V: showMessage("見つかりませんでしたエラー")
    end
```
