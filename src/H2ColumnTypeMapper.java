

/**
 * Implementacja ColumnTypeMappera dla bazy danych H2
 */
public class H2ColumnTypeMapper implements ColumnTypeMapper {

	/**
	 * Mapuje typ kolumny z ColumnType na typ bazodanowy
	 *
	 * @param type typ Kolumny
	 * @return typ w bazie danych jako String
	 */
	@Override
	public String mapType(ColumnType type) {
		if (type == ColumnType.STRING) {
			return "varchar(255)";
		}

		if (type == ColumnType.LONG) {
			return "BIGINT";
		}
		return type.toString().toLowerCase();
		
	}

}
