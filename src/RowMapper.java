import java.sql.ResultSet;


public interface RowMapper<T> {

	public T map(ResultSet rs, ObjectFactory<T> objectFactory);

}
