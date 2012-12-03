package util;

import java.util.Iterator;
import java.util.LinkedList;

import util.interfaces.Map;

/**
 * 
 * @author Christopher Bleckmann, Christoph Nützel, Norman Reszka	
 * 
 * <h1> HashMap </h1>
 * <p> this class uses an array of LinkedList<Pair<K,V> with the inner class Pair<K,V>
 * overflow...
 * calculation...
 * size...</p>
 *
 * @param <K> datatype of the key
 * @param <V> datatype of the value
 */
public class HashMapArray<K, V> implements Map<K, V> {

	@SuppressWarnings("hiding")
	class Pair<K, V> {

		private K key;
		private V value;

		public Pair(K key, V value) {
			this.key = key;
			this.value = value;
		}

		@Override
		public String toString() {
			return "key: " + this.key.toString() + " - value: "
					+ this.value.toString();
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + ((key == null) ? 0 : key.hashCode());
			result = prime * result + ((value == null) ? 0 : value.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			@SuppressWarnings("unchecked")
			Pair<K, V> other = (Pair<K, V>) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (key == null) {
				if (other.key != null)
					return false;
			} else if (!key.equals(other.key))
				return false;
			if (value == null) {
				if (other.value != null)
					return false;
			} else if (!value.equals(other.value))
				return false;
			return true;
		}

		public K getKey() {
			return key;
		}

		public void setKey(K key) {
			this.key = key;
		}

		public V getValue() {
			return value;
		}

		public void setValue(V value) {
			this.value = value;
		}

		@SuppressWarnings("rawtypes")
		private HashMapArray getOuterType() {
			return HashMapArray.this;
		}
	}

	private LinkedList<Pair<K, V>>[] map;
	private int size;
	private int length;
	private double calculation;

	@SuppressWarnings("unchecked")
	public HashMapArray(int length, double calculation) {
		map = new LinkedList[length];

		for (int i = 0; i < length; i++) {
			map[i] = new LinkedList<Pair<K, V>>();
		}

		this.calculation = calculation;
		this.size = 0;
		this.length = length;
	}
	
	@SuppressWarnings("unchecked")
	public HashMapArray(int length) {
		map = new LinkedList[length];

		for (int i = 0; i < length; i++) {
			map[i] = new LinkedList<Pair<K, V>>();
		}
		
		this.calculation = 0.75;
		this.size = 0;
		this.length = length;
	}

	/**
	 * 
	 * @param
	 * @return 
	 */
	@Override
	public V put(K key, V value) {
		int Ikey = hashFunction(key);
		if (map[Ikey].isEmpty()) {
			map[Ikey].add(new Pair<K, V>(key, value));
			size++;
			checkReorganizeMap();
			return null;
		} else {
			for (Iterator<Pair<K, V>> iterator = map[Ikey].iterator(); iterator
					.hasNext();) {
				Pair<K, V> type = (Pair<K, V>) iterator.next();
				if (type.getKey().equals(key)) {
					V save = type.getValue();
					type.setValue(value);
					return save;
				}
			}
			map[Ikey].add(new Pair<K, V>(key, value));
			size++;
			checkReorganizeMap();
		}
		return null;
	}

	/**
	 * 
	 * @param
	 * @return the value of the pair if key was found, else null
	 */
	@Override
	public V get(K key) {
		int Ikey = hashFunction(key);
		if (!map[Ikey].isEmpty()) {
			for (Iterator<Pair<K, V>> iterator = map[Ikey].iterator(); iterator
					.hasNext();) {
				Pair<K, V> type = (Pair<K, V>) iterator.next();
				if (type.getKey().equals(key))
					return type.getValue();
			}
		}
		return null;
	}

	/**
	 * 
	 * @param key
	 * @return
	 */

	@Override
	public V remove(K key) {
		int Ikey = hashFunction(key);
		V value = null;
		if (!map[Ikey].isEmpty()) {
			for (Iterator<Pair<K, V>> iterator = map[Ikey].iterator(); iterator
					.hasNext();) {
				Pair<K, V> type = (Pair<K, V>) iterator.next();
				if (type.getKey().equals(key)) {
					value = type.getValue();
					iterator.remove();
					break;
				}
			}
		}
		return value;
	}

	private void checkReorganizeMap() {
		double compute = size / length;
		if (compute > calculation) {
			int nextPrime = 10;
			System.out.println("its time now");
			reorganizeMap(nextPrime);
		}
	}

	private void reorganizeMap(int nextPrime) {
		int change = map.length + nextPrime;
		HashMapArray<K, V> current = new HashMapArray<K, V>(change);

		for (int i = 0; i < map.length; i++) {
			if (!map[i].isEmpty()) {
				for (Iterator<Pair<K, V>> iterator = map[i].iterator(); iterator
						.hasNext();) {
					Pair<K, V> pair = (Pair<K, V>) iterator.next();
					current.put(pair.getKey(), pair.getValue());
				}
			}
		}

		this.map = current.map;
		this.size = current.size;
		this.length = current.length;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int hashFunction(K key) {
		return key.hashCode() % map.length;
	}

	public int size() {
		return size;
	}
	
	public int length() {
		return length;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("");
		for (int i = 0; i < map.length; i++) {
			sb.append("[" + i + "]");
			for (Iterator<Pair<K, V>> iterator = map[i].iterator(); iterator
					.hasNext();) {
				sb.append("->");
				Pair<K, V> type = (Pair<K, V>) iterator.next();
				sb.append("(" + type.toString() + ")");
			}
			sb.append("\n");
		}
		return sb.toString();
	}
}
