package br.unisal.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 
 * @author JETHER ROIS
 *
 */
public class PropertyUtil {
	private static final PropertyUtil INSTANCE = new PropertyUtil();

	private PropertyUtil() {
	}

	public static final PropertyUtil getInstance() {
		return INSTANCE;
	}

	public Properties readFromClasspath(String filename) throws IOException {
		final InputStream stream = getClass().getClassLoader().getResourceAsStream(filename);
		if (stream == null) {
			return null;
		}
		try {
			Properties props = new Properties();
			props.load(stream);
			return props;
		} finally {
			stream.close();
		}

	}
}
