import java.util.ArrayList;
import java.util.List;


/**
 * Generator schematu bazy danych
 */
public class DBSchemeCreator {
	
	/** Meta model na podstawie ktorego tworzona jest baza danych */
	private MetaModel metaModel;
	
	/** Mapper typu kolumn */
	private ColumnTypeMapper mapper;
	
	/** Lista tabelek ktore beda w bazie danych  */
	List<Table> tables;

	/**
	 * Tworzenie instacji generatora schematu bazy danych
	 *
	 * @param metaModel the meta model
	 * @param mapper the mapper
	 */
	public DBSchemeCreator(MetaModel metaModel, ColumnTypeMapper mapper) {
		this.mapper = mapper;
		this.metaModel = metaModel;
		tables = metaModel.getTables();
	}

	/**
	 * Getter metaModelu na podstawie ktorego tworzone sa skrypty
	 *
	 * @return metaModel na podstawie ktorego tworzone sa skrypty
	 */
	public MetaModel getMetaModel() {
		return metaModel;
	}

	/**
	 * Setter metaModelu na podstawie ktorego tworzone sa skrypty
	 *
	 * @param metaModel nowy metamodel
	 */
	public void setMetaModel(MetaModel metaModel) {
		this.metaModel = metaModel;
	}

	/**
	 * Tworzy liste skryptow sluzacych do tworzenia tabelek w bazie danych
	 *
	 * @return zwraca liste skrypotw sluzacych do tworzenia tabelek w bazie danych
	 */
	public List<String> getCreates() {
		List<String> createdScripts = new ArrayList<String>();

		for (Table t : tables) {
			createdScripts.add(getCreate(t));
		}

		return createdScripts;
	}

	/**
	 * Budowanie skryptu create table ktory tworzy tabelke w bazie danych
	 *
	 * @param t tabelka typu Table z ktorej ma powstac tabelka w bazie danych
	 * @return skrypt sluzacy do tworzenia tabelki
	 */
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
