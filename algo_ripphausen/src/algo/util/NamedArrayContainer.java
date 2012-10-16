package algo.util;

public class NamedArrayContainer {

	private Integer[] data;
	private String name;
	
	public NamedArrayContainer(Integer[] data, String name) {
		this.data = data;
		this.name = name;
	}

	public Integer[] getData() {
		return data;
	}

	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
