package com.site.web.utils;

import java.io.IOException;
import java.util.Map;
import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonReader {
	
	/**
     * Reads a JSON file and returns it as a Map<String, Object>.
     * 
     * @param filePath the path to the JSON file
     * @return a Map representing the JSON data
     * @throws IOException if an I/O error occurs
     */
    @SuppressWarnings("unchecked")
	public static Map<String, Object> readJsonFileAsMap(String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File(filePath), Map.class);
    }
    
    /**
     * Retrieves the value of the given key from a JSON file.
     * 
     * @param filePath the path to the JSON file
     * @param key the key whose value is to be retrieved
     * @return the value associated with the given key, or null if the key does not exist
     * @throws IOException if an I/O error occurs
     */
    public static Object getValueFromJsonFile(String filePath, String key) throws IOException {
        Map<String, Object> jsonMap = readJsonFileAsMap(filePath);
        return jsonMap.get(key);
    }
    

}
