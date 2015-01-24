

// TODO: Auto-generated Javadoc
/**
 * The Class H2ColumnTypeMapper.
 */
public class H2ColumnTypeMapper implements ColumnTypeMapper {

	/**
	 * Map type.
	 *
	 * @param type the type
	 * @return the string
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
