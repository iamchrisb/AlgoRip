package algo.util;

public class TestSortTime {

	private String date;
	private long time;
	private String arrayname;
	private String sort;
	private int array_length;
	
	public TestSortTime(String date, long time, String arrayname, String sort) {
		this.date = date;
		this.time = time;
		this.arrayname = arrayname;
		this.sort = sort;
	}
	
	public TestSortTime() {}
	
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public long getTime() {
		return time;
	}
	public void setTime(Long start) {
		this.time = start;
	}
	public String getArrayname() {
		return arrayname;
	}
	public void setArrayname(String arrayname) {
		this.arrayname = arrayname;
	}
	
	public int getArray_length() {
		return array_length;
	}

	public void setArray_length(int array_length) {
		this.array_length = array_length;
	}

	@Override
	public String toString() {
		return "method: " + sort + " - time: " + time + "ns - Array: " + arrayname + " - Length: " + array_length + " - Date: " + date;
	}
}
