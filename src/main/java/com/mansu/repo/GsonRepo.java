package com.mansu.repo;

import com.google.gson.Gson;

public class GsonRepo {
	public static Gson gson = new Gson();
	
	public static Gson getInstance() {
		return gson;
	}
}
