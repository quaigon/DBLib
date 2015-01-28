import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class DBCreator.
 */
public class DBCreator {

	/** Stala zawierajca nazwe modelu  */
	private static final String MODEL = "Model";
	/** Stala zawierajca nazwe drugiego modelu  */
	private static final String MODEL2 = "Model2";
	/** Stala zawierajaca url bazy danych. */
	private static final String DB_URL = "jdbc:h2:tcp://localhost/~/testbase";
	
	/** Stala zawierajaca nazwe uzytkownika bazy */
	private static final String DB_USER = "admin";
	
	/** Stala zawierajaca haslo uzytkownika bazy  */
	private static final String DB_PASSWD = "admin";

	/**
	 * Tworzy tabelki w bazie danych na podstawie skryptow
	 *
	 * @param dbConnection polaczenie do bazy danych
	 * @param scripts lista skryptow ktora ma byc uzyta do stworzenia tabelek w bazie
	 */
	public static void createDataBase(Connection dbConnection,
			List<String> scripts) {
		Statement stmt = null;

		try {
			stmt = dbConnection.createStatement();

			for (String script : scripts) {
				stmt.executeUpdate(script);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Usuwanie tabelek z bazy danych.
	 *
	 * @param dbConnection polaczenie do bazy danych
	 */
	public static void clearDataBase(Connection dbConnection) {
		Statement stmt = null;

		try {
			stmt = dbConnection.createStatement();
			stmt.executeUpdate("drop all objects");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Metoda main
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {

		Long l = (long) 1;
		String classes[] = new String[2];
		classes[0] = "Class";
		classes[1] = "Person";
		Model.Class classs = new Model.Class("modelklasy", l);
		Model.Class class2 = new Model.Class("modelklasy3234", (long) 2);
//		String classes2[] = new String[2];
//		classes[0] = "Testowy";
//		classes[1] = "Testowy2";
		ReflectionMetaModelCreator rmmc = new ReflectionMetaModelCreator(MODEL,
				classes);
		MetaModel metaModel = rmmc.create();
		H2ColumnTypeMapper mapper = new H2ColumnTypeMapper();
		DBSchemeCreator dbsc = new DBSchemeCreator(metaModel, mapper);
		List<String> scriptsList = dbsc.getCreates();
		// for (String s : scriptsList) {
		// System.out.println(s);
		// }
		try {
			Connection conn = DriverManager.getConnection(DB_URL, DB_USER,
					DB_PASSWD);
			 clearDataBase(conn);
			 createDataBase(conn, scriptsList);
			DAOImpl<Model.Class> dao = new DAOImpl<Model.Class>(conn,
					metaModel, Class.forName("Model.Class"));
			 dao.insert(classs);
			 dao.insert(class2);
//			dao.delete(class2);
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
