import java.util.List;

public class QueryBuilder {
	private MetaModel metaModel;

	public QueryBuilder(MetaModel metaModel) {
		this.metaModel = metaModel;
	}

	public String buildCreateStatement(String tableName) {
		List<Column> columnList = metaModel.getTable(tableName).getColumns();
		StringBuilder createStatement = new StringBuilder();
		createStatement.append("insert into " + tableName).append("( ");
		for (int i = 0; i < columnList.size(); i++) {
			if ((i + 1) != columnList.size()) {
				createStatement.append(columnList.get(i).getName() + ", ");
			} else {
				createStatement.append(columnList.get(i).getName() + ") values ( ");
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
}
