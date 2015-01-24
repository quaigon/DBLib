import java.util.List;

public class MetaModel {

	private List<Table> tables;

	public MetaModel(List<Table> tables) {
		this.tables = tables;
	}

	public List<Table> getTables() {
		return tables;
	}

	public void setTables(List<Table> tables) {
		this.tables = tables;
	}

	public Table getTable(String tableName) {
		for (Table t : tables) {
			if (t.getTableName().equals(tableName)) {
				return t;
			}
		}
		return null;
	}

}