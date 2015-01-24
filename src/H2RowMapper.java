import java.sql.ResultSet;
import java.sql.SQLException;

public class H2RowMapper<T> implements RowMapper<T> {

	
	
	public H2RowMapper ( ) {
		
	}
	
	
	public T map(ResultSet rs, ObjectFactory<T> objectFactory) {
		T object = objectFactory.createObject(rs);
		String name = null;
		try {
			Object obj = rs.getObject(name);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		return object;
	}

	

}
