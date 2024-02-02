
import java.util.List;
import java.util.Scanner;

import controller.ListBookHelper;
import model.ListBook;

public class StartProgram {

	static Scanner in = new Scanner(System.in);
	static ListBookHelper lih = new ListBookHelper();

	// main method constructor
	public static void main(String[] args) {
		runMenu();
	}

	// main menu script
	public static void runMenu() {
		boolean goAgain = true;
		System.out.println("--- Welcome to my awesome book list! ---");
		while (goAgain) {
			System.out.println("*  Select an author:");
			System.out.println("*  1 -- Add a book");
			System.out.println("*  2 -- Edit a book");
			System.out.println("*  3 -- Delete a book");
			System.out.println("*  4 -- View the list");
			System.out.println("*  5 -- Exit the Table");
			System.out.print("*  Your selection: ");
			int selection = in.nextInt();
			in.nextLine();

			if (selection == 1) {
				addABook();
			} else if (selection == 2) {
				editABook();
			} else if (selection == 3) {
				deleteABook();
			} else if (selection == 4) {
				viewTheList();
			} else {
//				lih.cleanUp();
				System.out.println("   Goodbye!   ");
				goAgain = false;
			}
		}
	}

	// add an author script
	private static void addABook() {
		System.out.print("Enter a author: ");
		String author = in.nextLine();
		System.out.print("Enter an book: ");
		String book = in.nextLine();
		ListBook toAdd = new ListBook(author, book);
		lih.insertBook(toAdd);

	}

	// delete an author script
	private static void deleteABook() {
		System.out.print("Enter the author to delete: ");
		String author = in.nextLine();
		System.out.print("Enter the book to delete: ");
		String book = in.nextLine();

		ListBook toDelete = new ListBook(author, book);
		lih.deleteBook(toDelete);

	}

	// edit an author constructor
	private static void editABook() {
		System.out.println("How would you like to search? ");
		System.out.println("1 : Search by Author");
		System.out.println("2 : Search by Book");
		int searchBy = in.nextInt();
		in.nextLine();

		List<ListBook> foundBooks;
		if (searchBy == 1) {
			System.out.print("Enter the author name: ");
			String authorName = in.nextLine();
			foundBooks = lih.searchForBookByAuthor(authorName);
			// add stubbs??
		} else {
			System.out.print("Enter the book title: ");
			String bookName = in.nextLine();
			foundBooks = lih.searchForBookByBook(bookName);
			// add stubbs??
		}

		if (!foundBooks.isEmpty()) {
			System.out.println("Found Results.");
			for (ListBook l : foundBooks) {
				System.out.println(l.getId() + " : " + l.toString());
			}
			System.out.print("Which ID to edit: ");
			int idToEdit = in.nextInt();

			ListBook toEdit = lih.searchForBookById(idToEdit);
			System.out.println("Retrieved " + toEdit.getBook() + " from " + toEdit.getAuthor());
			System.out.println("1 : Update Author");
			System.out.println("2 : Update Book");
			int update = in.nextInt();
			in.nextLine();

			if (update == 1) {
				System.out.print("New Author: ");
				String newAuthor = in.nextLine();
				toEdit.setAuthor(newAuthor);
			} else if (update == 2) {
				System.out.print("New Book: ");
				String newBook = in.nextLine();
				toEdit.setBook(newBook);
			}

			lih.updateBook(toEdit);

		} else {
			System.out.println("---- No results found");
		}
	}

	// Show all database/table authors
	private static void viewTheList() {
		List<ListBook> allBooks = lih.showAllBooks();
		for (ListBook singleBook : allBooks) {
			System.out.println(singleBook.returnBookDetails());
		}
	}

}