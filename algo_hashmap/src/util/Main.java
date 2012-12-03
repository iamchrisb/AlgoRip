package util;

public class Main {

	public static void main(String[] args) {
//		testArrayListMap();
		HashMapArray<Integer, String> arMap = new HashMapArray<Integer, String>(3);
		arMap.put(44, "Hallo welt");
		arMap.put(0, "adfaö");
		System.out.println(arMap);
		arMap.put(23, "Kekse");
		System.out.println(arMap.length());
		System.out.println(arMap);
		arMap.put(3, "Kekse");
//		arMap.put(46, "Kuchen");
//		arMap.put(23, "__--");
//		arMap.put(20, "uaher");
//		arMap.remove(44);
//		arMap.remove(0);
//		arMap.remove(21);
//		arMap.put(4, "spinne");
//		System.out.println(arMap);
	} 

	private static void testArrayListMap() {
		HashMap<Integer, String> table = new HashMap<Integer, String>(23);
		System.out.println(table.isEmpty());
		table.put(34, "Hallo");
		table.remove(34);
		System.out.println(table.isEmpty());
		table.put(100, "schwachsinn");
		table.put(101, "schwammsinn");
		table.put(56, "Hallo2");
		table.put(58, "BLAbla");
		table.put(57, "KEKSE");
		table.put(55, "9 man");
		System.out.println(table.get(9));
		System.out.println(table.get(0));
		
		for(int i=0; i < 1000 ; i++){
			table.put(i, "Haifisch" + i);
		}
//		
		table.remove(9);
		table.remove(0);
//		table.remove(988);
		System.out.println(table);
		System.out.println(table.size());
	}
}
