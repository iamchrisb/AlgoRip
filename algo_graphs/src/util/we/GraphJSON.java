package util.we;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;

import util.ripphausen.Edge;
import util.ripphausen.Graph;

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

	public static <V extends Vertex2d, E extends Edge<V>> GraphJsonPojo<V, E> getFromJSON(String path) {
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
		Gson gson = new Gson();

		Type foo = new TypeToken<GraphJsonPojo<Vertex2d, Edge<Vertex2d>>>() {
		}.getType();
		GraphJsonPojo<V, E> g = gson.fromJson(jsonString, foo);
		for (V vert : g.getVertices()) {
			vert.setContent(new Vertex2dSearchContent());
			vert.getContent().setColor(Color.green);
		}
		return g;

	}

}
