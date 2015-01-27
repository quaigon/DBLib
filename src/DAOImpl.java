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
		boolean result = false;
		Class<?> objClass = obj.getClass();
		String tableName = objClass.getSimpleName();
		String createStatement = qb.buildCreateStatement(tableName);
		Method[] methods = objClass.getMethods();
		try {
			PreparedStatement stmt = dbConenction
					.prepareStatement(createStatement);
			for (Method m : methods) {
				if (m.getName().startsWith("get")
						&& !m.getName().equals("getClass")) {
					i++;
					Object value = m.invoke(obj, new Object[0]);
					stmt.setObject(i, value);
				}
				result = true;
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

		return result;
	}

	@Override
	public boolean update(T obj) {
		boolean result = false;
		int i = 0;
		Class<?> objClass = obj.getClass();
		String tableName = objClass.getSimpleName();
		String updateStatement = qb.buildUpdateStatement(tableName);
		Method[] methods = objClass.getMethods();
		System.out.println(tableName);

		try {
			PreparedStatement stmt = dbConenction
					.prepareStatement(updateStatement);
			for (Method m : methods) {
				if (m.getName().startsWith("get")
						&& !m.getName().equals("getClass")) {
					i++;
					Object value = m.invoke(obj, new Object[0]);
					stmt.setObject(i, value);
				}
				result = true;
			}
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public boolean delete(T obj) {
		boolean result = false;
		Class<?> objClass = obj.getClass();
		String tableName = objClass.getSimpleName();
		String deleteStatement = qb.buildDeleteStatement(tableName);
		System.out.println(deleteStatement);
		Method[] methods = objClass.getMethods();
		try {
			PreparedStatement stmt = dbConenction
					.prepareStatement(deleteStatement);
			for (Method m : methods) {
				if (m.getName().equals("getId")) {
				Object value = m.invoke(obj, new Object[0]);
				System.out.println(value);
				stmt.setObject(1, value);
				}
			}
			stmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

}
