import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementacja interfejsu ktory tworzy metaModel. 
 * Ta implementacja tworzy  metamodel na podstawie paczki z klasami.
 *  Jedna klasa reprezentuje jedna tabelke. 
 *  Jedno pole klasy reprezentuje jedna kolumne tabelki
 */
public class ReflectionMetaModelCreator implements MetaModelCreator {

	/** Nazwa paczki w ktorej znajaduja sie klasy */
	private String packageName;

	/** Tablica z nazwami klas ktore maja byc wykorzystane w tworzeniu metamodelu */
	private String[] classesArray;

	/**
	 * Tworzy instancje RefelectionMetaModelCreatora
	 * 
	 * @param packageName
	 *            Nazwa paczki w ktorej znajaduja sie klasy 
	 * @param classesArray
	 *            Tablica z nazwami klas ktore maja byc wykorzystane w tworzeniu metamodelu
	 */
	public ReflectionMetaModelCreator(String packageName, String[] classesArray) {
		this.packageName = packageName;
		this.classesArray = classesArray;
	}

	/**
	 * Tworzy tabelki metamodelu bazy danych
	 * 
	 * @return lista tabelek ktore beda umieszczone w bazie danych
	 */
	protected List<Table> generateTables() {
		List<Table> tablesList = new ArrayList<Table>();

		for (String s : classesArray) {
			try {
				Class<?> c = Class.forName(packageName + "." + s);
				tablesList
						.add(new Table(c.getSimpleName(), generateColumns(c)));
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return tablesList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see MetaModelCreator#create()
	 */
	@Override
	public MetaModel create() {
		return new MetaModel(generateTables());
	}

	/**
	 * Generate columns.
	 * 
	 * @param c
	 *            the c
	 * @return the list
	 */
	protected List<Column> generateColumns(Class<?> c) {
		List<Column> columnList = new ArrayList<Column>();
		Field[] fields = c.getDeclaredFields();
		for (Field f : fields) {
			if (checkSetterGetter(c, f.getName())) {
				columnList.add(new Column(f.getName(), ColumnType.lookup(f
						.getType().getSimpleName())));
			}
		}
		return columnList;
	}

	/**
	 * Sprawdza czy dane pole klasy posiada settery i gettery
	 * 
	 * @param clazz
	 *            klasa sprawdzanego pola
	 * @param postfix
	 *            nazwa sprawdzenego pola
	 * @return true jezeli pole posiada gettery i settery
	 */
	private boolean checkSetterGetter(Class<?> clazz, String postfix) {
		postfix = postfix.substring(0, 1).toUpperCase() + postfix.substring(1);
		Method[] methods = clazz.getMethods();
		for (Method m : methods) {
			if (m.getName().equals("set" + postfix)) {
				for (Method m2 : methods) {
					if (m2.getName().equals("get" + postfix)) {
						return true;
					}
				}
			}
		}
		return false;
	}

}
