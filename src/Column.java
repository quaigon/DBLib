

/**
 * Klasa Columny, opisuje kolumne w bazie danych
 */
public class Column {
	
	/** Nazwa danej kolumny. */
	private String name; 
	
	/** Typ danej kolumny. */
	private ColumnType type;

	/**
	 * Tworzy nowa kolumny
	 *
	 * @param name jest to nazwa tworzonej kolumny
	 * @param type jest to typ tworzonej kolumny
	 */
	public Column(String name, ColumnType type) {
		this.name = name;
		this.type = type;
	}

	/**
	 * Getter nazwy kolumny
	 *
	 * @return nazwy kolumny
	 */
	public String getName() {
		return name;
	}

	/**
	 *  Setter nazwy kolumny
	 *
	 * @param name nazwa kolumny
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 *  Getter typu kolumny
	 *
	 * @return typ kolumny
	 */
	public ColumnType getType() {
		return type;
	}

	/**
	 *  Setter typu kolumny
	 *
	 * @param type nowy typ kolumny
	 */
	public void setType(ColumnType type) {
		this.type = type;
	}

}
