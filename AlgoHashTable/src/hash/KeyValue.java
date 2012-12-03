package hash;


public class KeyValue<K, V> {
	private K Key;
	V Value;

	public KeyValue(HashFunction<K, V> hashtabelle, K Key, V Value) {
		this.Key = Key;
		this.Value = Value;
	}

	public K getKey() {
		return Key;
	}

	public V getValue() {
		return Value;
	}

	public V setValue(V Value) {
		return this.Value = Value;
	}

	public String toString() {
		StringBuilder ss = new StringBuilder();

		ss.append("K: " + Key);
		ss.append(" V: " + Value);

		return ss.toString();
	}
}