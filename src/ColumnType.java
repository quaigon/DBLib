import java.util.Date;

public enum ColumnType {
	STRING {
		@Override
		public Class<?> getJavaType() {
			return String.class;
		}
	},
	INT {
		@Override
		public Class<?> getJavaType() {
			return Integer.class;
		}
	},
	DATE {
		@Override
		public Class<?> getJavaType() {
			return Date.class;
		}

	},
	DOUBLE {
		@Override
		public Class<?> getJavaType() {
			return Double.class;
		}
	},
	LONG {
		@Override
		public Class<?> getJavaType() {
			return Long.class;
		}
	};

	public abstract Class<?> getJavaType();

	public static ColumnType lookup(String id) {
		try {
			return ColumnType.valueOf(id.toUpperCase());
		} catch (IllegalArgumentException e) {
			throw new RuntimeException("Invaild type " + id);
		}
	}
}
