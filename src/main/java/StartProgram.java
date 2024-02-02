
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import controller.ListBookHelper;
import model.ListBook;

public class StartProgram {

	static Scanner in = new Scanner(System.in); // initiates the scanner function for user input
	static ListBookHelper lbh = new ListBookHelper(); // create a new instance of the class ListBookHelper with the
														// variable 'lbh'

	public static void main(String[] args) { // main method constructor -- runs the main menu below
		runMenu();
	}

	public static void runMenu() { // main menu script
		boolean goAgain = true;
		System.out.println("--- Welcome to my awesome book list! ---");
		try {
			while (goAgain) { // while loop that continues menu options as long as 1-4 is selected
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
//				lbh.cleanUp();
					System.out.println("   Goodbye!   ");
					goAgain = false;
				}
			}
		} catch (InputMismatchException e) {
			System.out.print("Please enter a valid response: ");
		}
	}

	private static void addABook() { // add an author script
		System.out.print("Enter a author: ");
		String author = in.nextLine();
		System.out.print("Enter an book: ");
		String book = in.nextLine();
		ListBook toAdd = new ListBook(author, book); // creates a new instance of ListBook class object
		lbh.insertBook(toAdd); // funnels new ListBook toAdd object into ListBookHelper insertBook method

	}

	private static void deleteABook() { // delete an author script
		System.out.print("Enter the author to delete: ");
		String author = in.nextLine();
		System.out.print("Enter the book to delete: ");
		String book = in.nextLine();

		ListBook toDelete = new ListBook(author, book); // creates a new instance of ListBook class object
		lbh.deleteBook(toDelete); // funnels new ListBook toDelete object into ListBookHelper deleteBook method

	}

	private static void editABook() { // edit an author constructor
		System.out.println("How would you like to search? ");
		System.out.println("1 : Search by Author");
		System.out.println("2 : Search by Book");
		int searchBy = in.nextInt();
		in.nextLine();

		List<ListBook> foundBooks;
		if (searchBy == 1) {
			System.out.print("Enter the author name: ");
			String authorName = in.nextLine();
			foundBooks = lbh.searchForBookByAuthor(authorName); // funnels author name into searchForBoookByAuthor
																// method in ListBookHelper class
			// add stubbs??
		} else {
			System.out.print("Enter the book title: ");
			String bookTitle = in.nextLine();
			foundBooks = lbh.searchForBookByTitle(bookTitle); // funnels author name into searchForBoookByTitle method
																// in ListBookHelper class
			// add stubbs??
		}

		if (!foundBooks.isEmpty()) {
			System.out.println("Found Results.");
			for (ListBook l : foundBooks) {
				System.out.println(l.getId() + " : " + l.toString());
			}
			System.out.print("Which ID to edit: ");
			int idToEdit = in.nextInt();

			ListBook toEdit = lbh.searchForBookById(idToEdit); // new instance ListBook object using the ListBookHelper
																// method searchForB
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

			lbh.updateBook(toEdit);

		} else {
			System.out.println("---- No results found"); // print out if nothing matched the search criteria
		}
	}

	private static void viewTheList() { // Show all database/table authors
		List<ListBook> allBooks = lbh.showAllBooks(); // calls method showAllBooks in the ListBookHelper class
		for (ListBook singleBook : allBooks) {
			System.out.println(singleBook.returnBookDetails());
		}
	}

}