package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.ListBook;

/**
 * @author Misti Christianson - mchristianson CIS175 - Spring 2024 Feb 1, 2024
 */
public class ListBookHelper {
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("BooksRead");		//creating a global instance of the Entity Manager Factory

	public void insertBook(ListBook li) {							//add an book to the database/table
		EntityManager em = emfactory.createEntityManager();			//creates a new instance of the Entity Manager
		em.getTransaction().begin();
		em.persist(li);
		em.getTransaction().commit();
		em.close();													//closes Entity Manager
	}

	//show all books in the database/table
	@SuppressWarnings("unchecked")
	public List<ListBook> showAllBooks(){
		EntityManager em = emfactory.createEntityManager();											//creates a new instance of the Entity Manager
		List<ListBook> allBooks = em.createQuery("SELECT i FROM ListBook i").getResultList();		//retrieves all instances of the item from the table
		return allBooks;
		}

	//delete an book from the database
	public void deleteBook(ListBook toDelete) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListBook> typedQuery = em.createQuery(				//returns an object of a given parameter to a new list titled 'typedQuery'
				"Select li from ListBook li where li.author = :selectedAuthor and li.book = :selectedBook",
				ListBook.class);

		// Substitute parameter with actual data from the toDelete book
		typedQuery.setParameter("selectedAuthor", toDelete.getAuthor());			//toDelete ListBook object created from StartProgram and getAuthor method from ListBook
		typedQuery.setParameter("selectedBook", toDelete.getBook());				//toDelete ListBook object created from StartProgram and getBook method from ListBook

		// we only want on result
		typedQuery.setMaxResults(1);

		// get the result and save it into a new list book
		ListBook result = typedQuery.getSingleResult();					//creating a ListBook object for the results

		// remove it
		em.remove(result);					//removing result/book from table in database
		em.getTransaction().commit();
		em.close();							//closing the Entity Manager
	}
	
		//update book in database script
	public void updateBook(ListBook toEdit) {
		EntityManager em = emfactory.createEntityManager();			//new instance of Entity Manager Factory
		em.getTransaction().begin();
		em.merge(toEdit);				//merging edits with current item in database using toEdit object from ListBook created in Start Program created in editABook method
		em.getTransaction().commit();
		em.close();						//closing the Entity Manager
		}
	
		//search for book by author script
	public List<ListBook> searchForBookByAuthor(String authorName) {			//from StartProgram editABook method
		EntityManager em = emfactory.createEntityManager();			//new instance of Entity Manager Factory
		em.getTransaction().begin();
		TypedQuery<ListBook> typedQuery = em.createQuery("select li from ListBook li where li.author = :selectedAuthor", ListBook.class);		//returns an object of a given parameter to a new list titled 'typedQuery'
		typedQuery.setParameter("selectedAuthor", authorName);			//set new parameters for the typedQuery list
		List<ListBook> foundBooks = typedQuery.getResultList();			//returns a list of books that matched the search criteria
		em.close();														//closing the Entity Manager
		return foundBooks;
		}
	
		//search for book by name script
	public List<ListBook> searchForBookByTitle(String bookTitle) {			//from StartProgram editABook method
		EntityManager em = emfactory.createEntityManager();			//new instance of Entity Manager Factory
		em.getTransaction().begin();
		TypedQuery<ListBook> typedQuery = em.createQuery("select li from ListBook li where li.book = :selectedBook", ListBook.class);		//returns an object of a given parameter to a new list titled 'typedQuery'
		typedQuery.setParameter("selectedBook", bookTitle);			//set new parameters for the typedQuery list
		List<ListBook> foundBooks = typedQuery.getResultList();		//returns a list of books that matched the search criteria
		em.close();													//closing the Entity Manager
		return foundBooks;
		}
	
	//Search for Book by ID script
	public ListBook searchForBookById(int idToEdit) {
		EntityManager em = emfactory.createEntityManager();				//new instance of Entity Manager Factory
		em.getTransaction().begin();
		ListBook found = em.find(ListBook.class, idToEdit);				
		em.close();														//closing the Entity Manager
		return found;
		}

	
	//closing Entity Manager Factory
	public void cleanUp(){
		emfactory.close();
		}
}
