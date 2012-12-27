package util.we;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class GraphJSON {

	/**
	 * 
	 * <h2>GraphJSON</h2>
	 * <p>
	 * With this class it is possible to construct an Graph with 2d-Vectors from
	 * a JSON-File. All necessary informations will be in the JSON-File. Its
	 * similar with the "GraphLesen"-Class.
	 * 
	 */

	public static void getFromJSON(String path) {
		System.out.println("getFromJSON");
		String jsonString = "";
		try {
			BufferedReader reader = new BufferedReader(new FileReader(path));
			String line = "";
			while ((line = reader.readLine()) != null) {
				jsonString += line;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(jsonString);
		Gson gson = new GsonBuilder()
				.setExclusionStrategies(
						new GraphGSONExclusionStrategy(
								Vertex2dSearchContent.class)).serializeNulls()
				.create();

//		Vertex2d vert = gson.fromJson(jsonString, Vertex2d.class);
//		System.out.println(vert);
		
		/**
		 * Type collectionType = new TypeToken<Collection<Integer>>(){}.getType();
			Collection<Integer> ints2 = gson.fromJson(json, collectionType);
		 */
		
		Type collType = new TypeToken<Collection<Vertex2d>>(){}.getType();
		Collection<Vertex2d> vertices = gson.fromJson(jsonString, collType);
		
		for (Vertex2d vert : vertices) {
			System.out.println(vert);
		}
	}

}
