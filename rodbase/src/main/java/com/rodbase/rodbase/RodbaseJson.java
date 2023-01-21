package com.rodbase.rodbase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

class RodbaseJson {
    public static Map<String, Object> jsonToMap(String jsonStr) throws JSONException {

        Map<String, Object> mapObj = new HashMap<String, Object>();
        try {
            JSONObject jsonObject = new JSONObject(jsonStr);
            if(!jsonObject.equals(JSONObject.NULL)) {
                mapObj = toMap(jsonObject);
            }
        }catch (JSONException e){
            //TODO: Print json error
        }
        return mapObj;
    }
    public static List<Object> jsonToList(String jsonStr) throws JSONException {
        List<Object> listObj = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(jsonStr);
            if(!jsonArray.equals(JSONObject.NULL)) {
                listObj = toList(jsonArray);
            }
        }catch (JSONException e){
            //TODO: Print json error
        }
        return listObj;
    }
    public static Map<String, Object> toMap(JSONObject object) throws JSONException {
        Map<String, Object> map = new HashMap<String, Object>();

        Iterator<String> keysItr = object.keys();
        while(keysItr.hasNext()) {
            String key = keysItr.next();
            Object value = object.get(key);

            if(value instanceof JSONArray) {
                value = toList((JSONArray) value);
            }

            else if(value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            map.put(key, value);
        }
        return map;
    }
    public static List<Object> toList(JSONArray array) throws JSONException {
        List<Object> list = new ArrayList<Object>();
        for(int i = 0; i < array.length(); i++) {
            Object value = array.get(i);
            if(value instanceof JSONArray) {
                value = toList((JSONArray) value);
            }

            else if(value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            list.add(value);
        }
        return list;
    }
}
