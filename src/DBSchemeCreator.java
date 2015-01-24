
import java.util.ArrayList;
import java.util.List;

public class DBSchemeCreator {
	private MetaModel metaModel;
	private ColumnTypeMapper mapper;
	List<Table> tables;

	public DBSchemeCreator(MetaModel metaModel, ColumnTypeMapper mapper) {
		this.mapper = mapper;
		this.metaModel = metaModel;
		tables = metaModel.getTables();
	}

	public MetaModel getMetaModel() {
		return metaModel;
	}

	public void setMetaModel(MetaModel metaModel) {
		this.metaModel = metaModel;
	}

	public List<String> getCreates() {
		List<String> createdScripts = new ArrayList<String>();

		for (Table t : tables) {
			createdScripts.add(getCreate(t));
		}

		return createdScripts;
	}

	private String getCreate(Table t) {
		StringBuilder create = new StringBuilder();
		create.append("create table ").append(t.getTableName()).append("(");
		List<Column> columns = t.getColumns();
		
		for (int i = 0; i < columns.size(); i++) {
			if (i == columns.size() - 1) {
				create.append(columns.get(i).getName()).append(" ")
						.append(mapper.mapType(columns.get(i).getType()));
				break;
			}
			create.append(columns.get(i).getName()).append(" ")
					.append(mapper.mapType(columns.get(i).getType()))
					.append(", ");
		}
		create.append(");");
		return create.toString();
	}

}
