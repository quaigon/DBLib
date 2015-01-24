
import java.util.List;

public class Table {
	private String tableName;
	private List<Column> columns;
	
	public Table(String tableName, List<Column> columns) {
		this.tableName = tableName;
		this.columns = columns;
	}
	
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public List<Column> getColumns() {
		return columns;
	}

	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}

	public void printColumns () {
		for (Column c : columns) {
		System.out.print(c.getName() + " " + c.getType());
		}
	}
	
}
