import java.util.List;

/**
 * MetaModel, ktory przechowuje informacje na temat tworzenej bazy danych
 */
public class MetaModel {

	/** Tabelki, ktore maja znalezc sie w bazie danych */
	private List<Table> tables;

	/**
	 * Tworzy instancje MetaModelu
	 * 
	 * @param tables
	 *            tabelki z ktorych ma zostac utworzona baza danych
	 */
	public MetaModel(List<Table> tables) {
		this.tables = tables;
	}

	/**
	 * Getter tabelek
	 * 
	 * @return tabelki z ktorych ma zostac utworzona baza danych
	 */
	public List<Table> getTables() {
		return tables;
	}

	/**
	 * Setter tabelek
	 * 
	 * @param tables
	 *            nowe tabelki z ktorych ma zostac utworzona baza danych
	 */
	public void setTables(List<Table> tables) {
		this.tables = tables;
	}

	/**
	 * Wyszukiwanie tabelki po nazwie
	 * 
	 * @param tableName
	 *            nazwa szukanej tabelki
	 * @return tabelke z dana nazwa
	 */
	public Table getTable(String tableName) {
		for (Table t : tables) {
			if (t.getTableName().equals(tableName)) {
				return t;
			}
		}
		return null;
	}

}