package util.we;

import util.we.annotations.NoGSON;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

public class GraphGSONExclusionStrategy implements ExclusionStrategy {
	
	private final Class<?> typeToSkip;
	
	public GraphGSONExclusionStrategy(Class<?> typeToSkip) {
		this.typeToSkip = typeToSkip;
	}

	@Override
	public boolean shouldSkipClass(Class<?> clazz) {
		return (clazz == typeToSkip);
	}

	@Override
	public boolean shouldSkipField(FieldAttributes f) {
		return f.getAnnotation(NoGSON.class) != null;
	}
	
}
