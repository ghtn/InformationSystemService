package com.ghtn.util;

import org.json.JSONException;
import org.json.JSONObject;

import com.ghtn.model.User;
import com.google.gson.Gson;

import java.util.List;
import java.util.Map;

public class JsonUtil {

	public static JSONObject listToJson(List<?> list) throws JSONException {
		JSONObject json = new JSONObject();
		json.put("returnList", list);
		return json;
	}
	
	public static JSONObject mapToJson(Map<?,?> map) throws JSONException {
		JSONObject json = new JSONObject();
		json.put("returnMap", map);
		return json;
	}
	
	public static String objectToGson(Object obj){
		return new Gson().toJson(obj);
	}
	
	public static User gsonToUser(String gson){
		return new Gson().fromJson(gson, User.class);
	}
	
	
	
}
