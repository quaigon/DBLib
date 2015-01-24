

public interface DAOInterface<T> {

	T findByID(long id);

	boolean insert(T obj);

	boolean update(T obj);

	boolean delete(T obj);
}
