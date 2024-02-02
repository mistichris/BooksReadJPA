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
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("BooksRead");

	//add an book to the database/table
	public void insertBook(ListBook li) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(li);
		em.getTransaction().commit();
		em.close();
	}

	//show all books in the database/table
	@SuppressWarnings("unchecked")
	public List<ListBook> showAllBooks(){
		EntityManager em = emfactory.createEntityManager();
		List<ListBook> allBooks = em.createQuery("SELECT i FROM ListBook i").getResultList();
		return allBooks;
		}

	//delete an book from the database
	public void deleteBook(ListBook toDelete) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListBook> typedQuery = em.createQuery(
				"Select li from ListBook li where li.author = :selectedAuthor and li.book = :selectedBook",
				ListBook.class);

		// Substitute parameter with actual data from the toDelete book
		typedQuery.setParameter("selectedAuthor", toDelete.getAuthor());
		typedQuery.setParameter("selectedBook", toDelete.getBook());

		// we only want on result
		typedQuery.setMaxResults(1);

		// get the result and save it into a new list book
		ListBook result = typedQuery.getSingleResult();

		// remove it
		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}
	
		//update book in database script
	public void updateBook(ListBook toEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
		}
	
		//search for book by author script
	public List<ListBook> searchForBookByAuthor(String authorName) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListBook> typedQuery = em.createQuery("select li from ListBook li where li.author = :selectedAuthor", ListBook.class);
		typedQuery.setParameter("selectedAuthor", authorName);
		List<ListBook> foundBooks = typedQuery.getResultList();
		em.close();
		return foundBooks;
		}
	
		//search for book by name script
	public List<ListBook> searchForBookByBook(String bookName) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListBook> typedQuery = em.createQuery("select li from ListBook li where li.book = :selectedBook", ListBook.class);
		typedQuery.setParameter("selectedBook", bookName);
		List<ListBook> foundBooks = typedQuery.getResultList();
		em.close();
		return foundBooks;
		}
	
	//Search for Book by ID script
	public ListBook searchForBookById(int idToEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		ListBook found = em.find(ListBook.class, idToEdit);
		em.close();
		return found;
		}

	
	//closing Entity Manager Factory
	public void cleanUp(){
		emfactory.close();
		}
}
