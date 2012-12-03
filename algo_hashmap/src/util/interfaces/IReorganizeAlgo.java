package util.interfaces;

import util.HashMap;

public interface IReorganizeAlgo {
	<K,V> HashMap<K, V> reorganize(HashMap<K,V> map);
}
