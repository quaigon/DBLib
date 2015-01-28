
import java.util.List;

/**
 * Klasa opisujaca tabelke w bazie danych
 */
public class Table {
	
	/** Nazwa tabelki */
	private String tableName;
	
	/** Lista kolumn tabelki */
	private List<Column> columns;
	
	/**
	 * Tworzy nowa tabelke
	 *
	 * @param tableName nazwa tabelki
	 * @param lista kolumn tabelki
	 */
	public Table(String tableName, List<Column> columns) {
		this.tableName = tableName;
		this.columns = columns;
	}
	
	/**
	 * Zwwraca nazwe tabelki
	 *
	 * @return nazwa tabelki
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * Setter nazwy tabelki
	 *
	 * @param tableName nowa nazwa tabelki
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/**
	 * Zwraca liste kolumn
	 *
	 * @return liste kolumn
	 */
	public List<Column> getColumns() {
		return columns;
	}

	/**
	 * Setter listy kolumn
	 *
	 * @param columns nowa lista kolumn
	 */
	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}

	/**
	 * Wyswietla po kolei nazwy kolumn wraz z typami
	 */
	public void printColumns () {
		for (Column c : columns) {
		System.out.print(c.getName() + " " + c.getType());
		}
	}
	
}
