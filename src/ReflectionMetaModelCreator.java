
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ReflectionMetaModelCreator implements MetaModelCreator {
	private String packageName;
	private String[] classesArray;

	public ReflectionMetaModelCreator(String packageName, String[] classesArray) {
		this.packageName = packageName;
		this.classesArray = classesArray;
	}

	protected List<Table> generateTables() {
		List<Table> tablesList = new ArrayList<Table>();

		for (String s : classesArray) {
			try {
				Class<?> c = Class.forName(packageName + "." + s);
				tablesList.add(new Table(c.getSimpleName(), generateColumns(c)));
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return tablesList;
	}

	@Override
	public MetaModel create() {
		return new MetaModel(generateTables());
	}

	protected List<Column> generateColumns(Class<?> c) {
		List<Column> columnList = new ArrayList<Column>();
		Field[] fields = c.getDeclaredFields();
		for (Field f : fields) {
			if (checkSetterGetter(c, f.getName())) {
			columnList.add(new Column (f.getName(),ColumnType.lookup(f.getType().getSimpleName())));
			}
		}
		return columnList;
	}
	
	
	private boolean checkSetterGetter (Class<?> clazz, String postfix) {
		postfix = postfix.substring(0, 1).toUpperCase() + postfix.substring(1);
		Method [] methods = clazz.getMethods();
		for (Method m : methods) {
			if (m.getName().equals("set"+postfix)) {
				for (Method m2 : methods) {
					if (m2.getName().equals("get"+postfix)) {
						return true;
					}
				}
			}
		}	
		return false;	
	}
	
}
