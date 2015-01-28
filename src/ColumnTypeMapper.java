

/**
 * Interfejs mapujacy typ kolumny na typy w danej bazie
 * Rozne implemenetacje dla roznych baz danych
 */
public interface ColumnTypeMapper {

	/**
	 * Mapuje typ kolumny na string
	 *
	 * @param type typ kolumny
	 * @return zmapowany na string typ kolumny
	 */
	String mapType(ColumnType type);
}
