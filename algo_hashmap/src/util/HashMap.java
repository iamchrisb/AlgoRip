package util;

import java.util.Iterator;
import java.util.LinkedList;

import util.interfaces.Map;

/**
 * 
 * @author Christopher Bleckmann, Christoph Nützel, Norman Reszka
 * 
 *         <h1>HashMap</h1>
 *         <p>
 *         this class uses an array of LinkedList<Pair<K,V> with the inner class
 *         Pair<K,V> overflow is solved with linkedlists and reorganization
 *         calculation defines the ratio when the reorganization starts size
 *         defines how many buckets are filled
 *         </p>
 * 
 * @param <K>
 *            datatype of the key
 * @param <V>
 *            datatype of the value
 */
public class HashMap<K, V> implements Map<K, V> {

	/**
	 * 
	 * @param <K>
	 *            datatype of the key
	 * @param <V>
	 *            datatype of the value
	 */
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
		private HashMap getOuterType() {
			return HashMap.this;
		}
	}

	private LinkedList<Pair<K, V>>[] map;
	private int size;
	private int length;
	private float calculation;
	private int addTerm = 10;

	@SuppressWarnings("unchecked")
	public HashMap(int length, float calculation) {
		map = new LinkedList[length];

		for (int i = 0; i < length; i++) {
			map[i] = new LinkedList<Pair<K, V>>();
		}

		this.calculation = calculation;
		this.size = 0;
		this.length = length;
	}

	@SuppressWarnings("unchecked")
	public HashMap(int length) {
		map = new LinkedList[length];

		for (int i = 0; i < length; i++) {
			map[i] = new LinkedList<Pair<K, V>>();
		}

		this.calculation = 0.75f;
		this.size = 0;
		this.length = length;
	}

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
			checkReorganizeMap();
		}
		return null;
	}

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
					// we can break now, cause there is no further similar key
					break;
				}
			}
		}
		return value;
	}

	private void checkReorganizeMap() {
		float compute = (float) size / length;
		if (compute > calculation) {
			reorganizeMap(addTerm);
		}
	}

	/**
	 * Initializes a new map with the new length ( length + additivTerm ) puts
	 * all old values into the new map
	 * 
	 * @param additivTerm
	 *            determines the new length together with the old length
	 */
	private void reorganizeMap(int additivTerm) {
		int change = map.length + additivTerm;
		HashMap<K, V> current = new HashMap<K, V>(change);

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

	/**
	 * 
	 * @return the value, that is added to the old length, when the map
	 *         reorganizes itself
	 */
	public int getAddTerm() {
		return addTerm;
	}

	/**
	 * 
	 * @param addTerm
	 *            the value, that is added to the old length, when the map
	 *            reorganizes itself
	 */
	public void setAddTerm(int addTerm) {
		this.addTerm = addTerm;
	}

	/**
	 * @return the current calculation to reorganize the map
	 */
	public float getCalculation() {
		return calculation;
	}

	/**
	 * @param calculation
	 *            determines the current calculation to reorganize the map
	 */
	public void setCalculation(float calculation) {
		this.calculation = calculation;
	}

	/**
	 * @return the current factor of size / length
	 */
	public float getCurrentRate() {
		return (float) size / length;
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
