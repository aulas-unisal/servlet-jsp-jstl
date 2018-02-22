package br.unisal.util;

import java.util.UUID;

public final class Util {

	public static String UUIDGenerate() {
		return UUID.randomUUID().toString().replace("-", "");
	}

}
