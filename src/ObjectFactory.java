import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;

// TODO: Auto-generated Javadoc
/**
 * Klasa tworzaca obiekt w javie na podstawie metaModelu za pomoca mechanizmu refleksji
 *
 * @param <T> typ tworzonego obiektu
 */
public class ObjectFactory<T> {

	/** Klasa modelowa tabelki */
	private Class<?> clazz;
	
	/** Meta model bazy danych */
	private MetaModel metaModel;

	/**
	 * Tworzy nowa instancje ObjectFactory
	 *
	 * @param klasa tworzonego obiektu
	 * @param metaModel bazy danych
	 */
	public ObjectFactory(Class<?> clazz, MetaModel metaModel) {
		this.clazz = clazz;
		this.metaModel = metaModel;
	}

	/**
	 * Tworzy nowy obiekt javowy na podstawie danych z bazydanych
	 *
	 * @param rs ResultSet z danymi z ktorych ma powstac obiekt
	 * @return the t
	 */
	@SuppressWarnings("unchecked")
	public T createObject(ResultSet rs) {
		T object = null;
		Table table = metaModel.getTable(clazz.getSimpleName());
		try {
			object = (T) clazz.newInstance();
			for (Column c : table.getColumns()) {
				String name = c.getName();
				name = name.substring(0, 1).toUpperCase() + name.substring(1);
				String methodName = "set" + name;
				Method method = clazz.getMethod(methodName, c.getType()
						.getJavaType());

				method.invoke(object, rs.getObject(name.toUpperCase()));
			}
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return object;
	}
}
