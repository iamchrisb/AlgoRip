package algo.fibonacci;

public class Main {

	public static void main(String[] args) {

		long start = System.currentTimeMillis();
//		System.out.println(computeRekFibo(10));
		System.out.println(computeItFibo(50));
		System.out.println("time needed: " + (System.currentTimeMillis() - start));

	}

	public static int computeRekFibo(int index) {
		if (!(index == 0 || index == 1)) {
			return computeRekFibo(index - 1) + computeRekFibo(index - 2);
		}
		return 1;
	}
	
	public static int computeItFibo(int index) {
		int erg = 1;
		if(!(index == 0 || index == 1)){
			int fib1 = 0;
			int fib2 = 1;
			for(int i = 0; i < index; i++){
				fib2 = fib1;
				fib1 = erg;
				erg = fib1 + fib2;
			}
		}
		return erg;
	}

}
