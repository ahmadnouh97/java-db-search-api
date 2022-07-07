package com.example.fulltextsearchapi.Product;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Utils {
    public static ArrayList<HashMap<String, Object>> readJson(String path) {
        JSONParser parser = null;
        try {
            parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(path));
            ObjectMapper mapper = new ObjectMapper();
            ArrayList<HashMap<String, Object>> data = new ArrayList<>();
            for (Object json : jsonArray) { //cropDetails is the JSONArray

                HashMap<String, Object> map = (HashMap<String, Object>) mapper.convertValue(json, HashMap.class);
                data.add(map);
            }
            return data;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
