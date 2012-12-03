package util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import util.interfaces.Map;

public class HashMap<K, V> implements Map<K, V> {

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

	ArrayList<LinkedList<Pair<K, V>>> map;
	private int length;
	private int size;

	public int size() {
		return size;
	}

	public HashMap(int length) {
		this.length = length;
		map = new ArrayList<LinkedList<Pair<K, V>>>(length);
		for (int i = 0; i < length; i++) {
			map.add(new LinkedList<Pair<K, V>>());
		}
	}

	@Override
	public V put(K key, V value) {
		if ((map.get(hashFunction(key)).isEmpty())) {
			map.get(hashFunction(key)).add(new Pair<K, V>(key, value));
			size++;
			return null;
		} else {
			if (map.get(hashFunction(key)).getFirst() != null) {
				Pair<K, V> swap = map.get(hashFunction(key)).getFirst();
				map.get(hashFunction(key)).clear();
				map.get(hashFunction(key)).add(new Pair<K, V>(key, value));
				return swap.getValue();
			} else {
				map.get(hashFunction(key)).add(new Pair<K, V>(key, value));
				size++;
				return null;
			}
		}
	}

	@Override
	public V get(K key) {
		int index = hashFunction(key);
		if (!map.get(index).isEmpty()) {
			if(map.get(index).getFirst()!=null){
				return map.get(index).getFirst().getValue();
			}
			else return null;
		} else {
			return null;
		}
	}

	@Override
	public V remove(K key) {
		if (map.get(hashFunction(key)).getFirst() != null) {
			Pair<K, V> swap = map.get(hashFunction(key)).getFirst();
			map.get(hashFunction(key)).clear();
			size--;
			return swap.getValue();
		} else {
			return null;
		}
	}

	private int hashFunction(K key) {
		int ret = key.hashCode() % this.length;
		return ret;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("");
		for (int i = 0; i < map.size(); i++) {
			sb.append("[" + i + "]");
			for (Iterator<Pair<K, V>> iterator = map.get(i).iterator(); iterator
					.hasNext();) {
				sb.append("->");
				Pair<K, V> type = (Pair<K, V>) iterator.next();
				sb.append("(" + type.toString() + ")");
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	
	public boolean isEmpty(){
		if(size==0) return true;
		else return false;
	}
}
