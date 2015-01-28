import java.util.Date;

/**
 * Enum opisujacy typy kolumn.
 */
public enum ColumnType {
	
	/** The string. */
	STRING {
		@Override
		public Class<?> getJavaType() {
			return String.class;
		}
	},
	
	/** The int. */
	INT {
		@Override
		public Class<?> getJavaType() {
			return Integer.class;
		}
	},
	
	/** The date. */
	DATE {
		@Override
		public Class<?> getJavaType() {
			return Date.class;
		}

	},
	
	/** The double. */
	DOUBLE {
		@Override
		public Class<?> getJavaType() {
			return Double.class;
		}
	},
	
	/** The long. */
	LONG {
		@Override
		public Class<?> getJavaType() {
			return Long.class;
		}
	};

	/**
	 * Zwraca klase typu w javie
	 *
	 * @return Typ w javie
	 */
	public abstract Class<?> getJavaType();

	/**
	 * Metody lookup sluzaca do pobierania typu.
	 *
	 * @param id jest to nazwa typu
	 * @return zwraca typ kolumny, w razie niepowodzenia wyrzuca RunTimeException
	 */
	public static ColumnType lookup(String id) {
		try {
			return ColumnType.valueOf(id.toUpperCase());
		} catch (IllegalArgumentException e) {
			throw new RuntimeException("Invaild type " + id);
		}
	}
}
