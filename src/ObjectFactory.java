import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ObjectFactory<T> {

	private Class<?> clazz;
	private MetaModel metaModel;

	public ObjectFactory(Class<?> clazz, MetaModel metaModel) {
		this.clazz = clazz;
		this.metaModel = metaModel;
	}

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
