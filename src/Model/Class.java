package Model;

public class Class {
	private String name;
	private Long id;

	public Class(String name, Long id) {
		this.name = name;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Class() {
		// TODO Auto-generated constructor stub
	}

}
