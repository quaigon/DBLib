
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import Model.Teacher;

public class DBCreator {

	private static final String MODEL = "Model";
	private static final String DB_URL = "jdbc:h2:tcp://localhost/~/testbase";
	private static final String DB_USER = "admin";
	private static final String DB_PASSWD = "admin";
	
	public static void createDataBase (Connection dbConnection, List <String> scripts) {
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
	
	public static void clearDataBase (Connection dbConnection) {
		Statement stmt = null;
		
		try {
			stmt = dbConnection.createStatement();
			stmt.executeUpdate("drop all objects");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		Long l = (long) 3;
		String classes[] = new String[2];
		classes[0] = "Class";
		classes[1] = "Person";
		Model.Class classs = new Model.Class("modelklasy", l);
		Model.Class class2 = new Model.Class("modelklasy3234", (long) 4);
		ReflectionMetaModelCreator rmmc = new ReflectionMetaModelCreator(MODEL,
				classes);
		MetaModel metaModel = rmmc.create();
		H2ColumnTypeMapper mapper = new H2ColumnTypeMapper();
		DBSchemeCreator dbsc = new DBSchemeCreator(metaModel, mapper);
		QueryBuilder qb = new QueryBuilder(metaModel);
		List<String> scriptsList = dbsc.getCreates();
//		for (String s : scriptsList) {
//			System.out.println(s);
//		}
		try {
			Connection conn = DriverManager.getConnection(DB_URL, DB_USER,
					DB_PASSWD);
//			clearDataBase(conn);
//			createDataBase(conn, scriptsList);
			DAOImpl<Model.Class> dao = new DAOImpl<Model.Class>(conn, metaModel, Class.forName("Model.Class"));
			Model.Class c = dao.findByID(1);
			Model.Class c2 = dao.findByID(2);
			dao.insert(classs);
			dao.insert(class2);
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
