package org.softlang.company.feature;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public interface Serialization<T>
{
	/**
	 * Custom deserializer for <code>ObservableList</code>s.
	 */
	static class ObservableListDeserializer implements JsonDeserializer<ObservableList<?>>
	{
		@Override
		public ObservableList<?> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
				throws JsonParseException
		{
			Type type = ((ParameterizedType) typeOfT).getActualTypeArguments()[0];

			ObservableList<Object> observableList = FXCollections.observableArrayList();
			json.getAsJsonArray().forEach(element -> observableList.add(GSON.fromJson(element, type)));
			return observableList;
		}
	}

	/**
	 * Gson constant with pretty-printing and a custom deserializer for
	 * <code>ObservableList</code>s.
	 */
	public static final Gson GSON = new GsonBuilder().setPrettyPrinting()
			.registerTypeAdapter(ObservableList.class, new ObservableListDeserializer()).create();

	/**
	 *
	 * @param json
	 * @return
	 */
	T deserialize(File in) throws IOException;

	void serialize(File out) throws IOException;
}
