

/**
 * Interfejs wzorca Data Access Object 
 *
 * @param <T> Typ obslugiwanego obiektu
 */
public interface DAOInterface<T> {

	/**
	 * Wyszukiwanie obiektu w bazie danych po id
	 *
	 * @param id identyfikator obiektu
	 * @return obiekt z bazy danych zmapowany na obiekt javy
	 */
	T findByID(long id);

	/**
	 * Umieszczenie obiektu javy w bazie danych
	 *
	 * @param obiekt umieszczany w bazie danych
	 * @return true jezeli sie powiedzie
	 */
	boolean insert(T obj);

	/**
	 * Aktualizacja obiektu w bazie danych
	 *
	 * @param obiekt aktualizowany w bazie danych
	 * @return true jezeli sie powiedzie
	 */
	boolean update(T obj);

	/**
	 * Usuwa obiekt z bazy danych
	 *
	 * @param obiekt ktory ma byc usuniety z bazy danych.
	 * @return true jezeli sie powiedzie
	 */
	boolean delete(T obj);
}
