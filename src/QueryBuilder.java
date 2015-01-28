import java.util.List;

/**
 * Klasa budujaca zapytania do bazy danych
 */
public class QueryBuilder {
	
	/** MetaModel bazy danych */
	private MetaModel metaModel;

	/**
	 * Tworzenie instancji QueryBuildera
	 *
	 * @param metaModel MetaModel bazy danych
	 */
	public QueryBuilder(MetaModel metaModel) {
		this.metaModel = metaModel;
	}

	
	/**
	 * Tworzenie zapytanie select by id
	 *
	 * @param tableName nazwa tabelki do ktorej ma byc stworzene zapytanie
	 * @return zapytanie select
	 */
	public String buildSelectByIdStatement (String tableName) {
		StringBuilder selectByIdStatement = new StringBuilder();
		
		selectByIdStatement.append("select * from " + tableName
				+ " where id = ?");
		
		return selectByIdStatement.toString();
	}
	
	/**
	 * Tworzy zapytanie sluzace do dodawania obiektow do tabelki
	 *
	 * @param tableName nazwa tabelki do ktorej dodawany jest obiekt
	 * @return zapytanie insert
	 */
	public String buildCreateStatement(String tableName) {
		List<Column> columnList = metaModel.getTable(tableName).getColumns();
		StringBuilder createStatement = new StringBuilder();
		createStatement.append("insert into " + tableName).append("( ");
		for (int i = 0; i < columnList.size(); i++) {
			if ((i + 1) != columnList.size()) {
				createStatement.append(columnList.get(i).getName() + ", ");
			} else {
				createStatement.append(columnList.get(i).getName()
						+ ") values ( ");
			}
		}
		for (int i = 0; i < columnList.size(); i++) {
			if ((i + 1) != columnList.size()) {
				createStatement.append("?, ");
			} else {
				createStatement.append("?)");
			}
		}

		return createStatement.toString();
	}

	/**
	 * Tworzy zapytanie do aktualizowania obiektu w tabelce 
	 *
	 * @param tableName nazwa aktualizowanej tabelki
	 * @return zapytanie sluzace do aktualizowania tabelki
	 */
	public String buildUpdateStatement(String tableName) {
		List<Column> columnList = metaModel.getTable(tableName).getColumns();
		StringBuilder updateStatement = new StringBuilder();
		updateStatement.append("update " + tableName + " set ");
		for (int i = 0; i < columnList.size(); i++) {
			if (!columnList.get(i).getName().equals("id")) {
				if ((i + 2) != columnList.size()) {
					updateStatement.append(columnList.get(i).getName() + "=?,");
				} else {
					updateStatement.append(columnList.get(i).getName() + "=?");
				}
			}
		}

		updateStatement.append(" where id=?");

		return updateStatement.toString();
	}

	/**
	 * Tworzy zapytanie sluzace do usuwania obiektow z tabelki
	 *
	 * @param nazwa tabelki z ktorej ma zostac usuniety obiekt
	 * @return zapytanie sluzace do usuwania z tabelki
	 */
	public String buildDeleteStatement(String tableName) {
		StringBuilder deleteStatement = new StringBuilder();
		
		deleteStatement.append("delete from " + tableName + " where id=?");

		return deleteStatement.toString();
	}
}
