import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOImpl<T> implements DAOInterface<T> {

	private Connection dbConenction;
	private MetaModel metaModel;
	private Class<?> clazz;
	private QueryBuilder qb;

	DAOImpl(Connection dbConnection, MetaModel metaModel, Class<?> class1) {
		this.dbConenction = dbConnection;
		this.metaModel = metaModel;
		this.clazz = class1;
		qb = new QueryBuilder(metaModel);
	}

	@Override
	public T findByID(long id) {
		T obj = null;
		PreparedStatement stmt = null;
		String select = "select * from " + clazz.getSimpleName().toLowerCase()
				+ " where id = ?";
		try {
			stmt = dbConenction.prepareStatement(select,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			ObjectFactory<T> objectFactory = new ObjectFactory<>(clazz,
					metaModel);
			rs.next();
			obj = objectFactory.createObject(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return obj;
	}

	@Override
	public boolean insert(T obj) {
		int i = 0;
		Class<?> objClass = obj.getClass();
		String tableName = objClass.getSimpleName();
		String createStatement = qb.buildCreateStatement(tableName);
		Method[] methods = objClass.getMethods();
		System.out.println(tableName);
		try {
			PreparedStatement stmt = dbConenction
					.prepareStatement(createStatement);
			for (Method m : methods) {
				if (m.getName().startsWith("get")
						&& !m.getName().equals("getClass")) {
					i++;
					System.out.println(m.getName());
					Object value = m.invoke(obj, new Object[0]);
					stmt.setObject(i, value);
				}
			}

			stmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(createStatement);

		return false;
	}

	@Override
	public boolean update(T obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(T obj) {
		// TODO Auto-generated method stub
		return false;
	}

}
