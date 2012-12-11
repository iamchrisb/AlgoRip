package util;

/**
 * @author Christopher Bleckmann, Norman Reszka, Christoph Nützel
 * 
 */
public class Main {

	public static void main(String[] args) {
		HashMap<Integer, String> arMap = new HashMap<Integer, String>(3);

		System.out.println("length: " + arMap.length());
		System.out.println("size: " + arMap.size());
		System.out.println("additiver Term: " + arMap.getAddTerm());
		System.out.println("calculation: " + arMap.getCalculation());
		System.out.println("get == null: " + arMap.get(7392));
		System.out.println("remove == null: " + arMap.remove(3424392));

		System.out.println("put == null: " + arMap.put(1, "Hallo Welt"));
		System.out.println("put == null: " + arMap.put(7, "Algorithem"));
		System.out.println("length: " + arMap.length());
		System.out.println("size: " + arMap.size());
		System.out.println("rate: " + arMap.getCurrentRate());
		System.out.println(arMap.put(4, "Storman"));
		System.out.println("remove != null: " + arMap.remove(4));
		System.out.println("put != null: " + arMap.put(7, "Christoph"));
		System.out.println("put == null: " + arMap.put(5, "Any Key"));
		System.out.println("size: " + arMap.size());
		System.out.println("rate: " + arMap.getCurrentRate());
		System.out.println("calculation: " + arMap.getCalculation());
		System.out.println("put == null: " + arMap.put(0, "8tracks"));
		System.out.println("size: " + arMap.size());
		System.out.println("rate: " + arMap.getCurrentRate());
		System.out.println("length: " + arMap.length());
		arMap.setAddTerm(5);
		arMap.setCalculation(0.5f);
		System.out.println("rate: " + arMap.getCurrentRate());
		System.out.println("put == null: " + arMap.put(4, "Dict"));
		System.out.println("rate: " + arMap.getCurrentRate());
		System.out.println("put == null: " + arMap.put(5, "Namefinding"));
		System.out.println("rate: " + arMap.getCurrentRate());
		System.out.println("put == null: " + arMap.put(11, "Eleven"));
		System.out.println("rate: " + arMap.getCurrentRate());
		System.out.println("put == null: " + arMap.put(10, "Greatest Divider"));
		System.out.println("rate: " + arMap.getCurrentRate());
		System.out.println("calculation: " + arMap.getCalculation());
		System.out.println("length: " + arMap.length());
		System.out.println(arMap);

		arMap.setCalculation(0.8f);
		for (int i = 0; i < 100; i++) {
			arMap.put(i, "Loop" + i);
		}

		System.out.println("size: " + arMap.size());
		System.out.println("rate: " + arMap.getCurrentRate());
		System.out.println("calculation: " + arMap.getCalculation());
		System.out.println("length: " + arMap.length());
		System.out.println(arMap);
		System.out.println("get != null: " + arMap.get(20));
		System.out.println("remove != null: " + arMap.remove(30));

		System.out.println();
		HashMap<Float, Character> charfloat = new HashMap<Float, Character>(20);
		charfloat.put(2.0f, 'k');
		charfloat.put(0.7f, 'O');
		charfloat.put(0.33f, 'Ö');
		charfloat.put(0.6534f, 'H');
		System.out.println(charfloat.get(0.33f));
		System.out.println(charfloat.remove(0.33f));
		System.out.println(charfloat);

	}

}
