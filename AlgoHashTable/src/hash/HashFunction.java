package hash;

import java.util.LinkedList;


public class HashFunction<K, V> implements Map<K, V> {

	LinkedList<KeyValue<K, V>>[] list;

	@SuppressWarnings("unchecked")
	public HashFunction(int lenght) {
		list = new LinkedList[lenght];
		for (int i = 0; i < list.length; i++) {
			list[i] = new LinkedList<KeyValue<K, V>>();
		}
	}

	private int hashFunction(K key) {
		int hash;
		hash = Math.abs(key.hashCode() % list.length);
		return hash;
	}

	/*
	 * If list is empty - put keyValuePair in the List
	 * Else Iterate over all and override the old value and return it
	 * If Value Pair isn´t in the list yet - put it in
	 */
	@Override
	public V put(K key, V value) {
		final KeyValue<K, V> keyValue = new KeyValue<K, V>(this, key, value);
		int hash = hashFunction(key);
		
		if (list[hash].size() == 0) {
			list[hash].add(keyValue);
			return null;
		
		}else{
			for (KeyValue<K, V> kv : list[hash]) {
				if (kv.getKey().equals(key)) {
					V varvalue = kv.getValue();
					kv.setValue(value);
					return varvalue;
				}
			}
		}
		list[hash].add(keyValue);
		
		return null;
	}

	@Override
	public V get(K key) {
		int hash = hashFunction(key);
		
		for (KeyValue<K, V> kv : list[hash]) {
			if (kv.getKey().equals(key)) {
				return kv.getValue();
			}
		}
		return null;
	}

	@Override
	public V remove(K key) {
		int hash = hashFunction(key);
		
		for (KeyValue<K, V> kv : list[hash]) {
			if (kv.getKey().equals(key)) {
				final KeyValue<K, V> keyValue = new KeyValue<K, V>(this, key, kv.getValue());
				list[hash].remove(keyValue);
				return kv.getValue();
			}
		}
		return null;
	}
	
	public String toString() {
		// KeyValue kev: ll[i
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.length; i++) {
			sb.append("Index" + i + " ");
			sb.append(list[i]);
			sb.append("\n");
		}
		return sb.toString();
	}

}
