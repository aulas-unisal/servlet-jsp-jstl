package br.unisal.util;

import com.google.gson.Gson;

public class GsonSingleton {

	private static Gson INSTANCE;

	private GsonSingleton() {

	}

	public static Gson getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Gson();
		}
		return INSTANCE;
	}

}
