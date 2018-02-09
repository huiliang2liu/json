package com.xh.json;

import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * json com.xh.json 2018 2018-2-8 下午3:25:33 instructions： author:liuhuiliang
 * email:825378291@qq.com
 **/

public class JsonObject {
	List<Entity> entities;
	List<String> keys;

	protected JsonObject(Reader reader) throws Exception {
		// TODO Auto-generated constructor stub
		init();
		pars1(reader);
	}

	private JsonObject pars1(Reader reader) throws Exception {
		int len = -1;
		String key = null;
		Object value = null;
		StringBuffer sb = new StringBuffer();
		while ((len = reader.read()) != -1) {
			if (len == Constant.LEFT_BARCKETS) {// 开始数组
				value = new JsonArray(reader);
			} else if (len == Constant.LEFT_CURLY_BRACES) {// 开始对象
				value = new JsonObject(reader);
			} else if (len == Constant.RIGHT_CURLY_BRACES) {// 结束自己
				if (sb.length() > 0) {
					value = sb.toString();
					sb.setLength(0);
				}
				keys.add(key);
				entities.add(new Entity(key, value));
				break;
			} else if (len == Constant.POINT) {

			} else if (len == Constant.comma) {
				if (sb.length() > 0) {
					value = sb.toString();
					sb.setLength(0);
				}
				keys.add(key);
				entities.add(new Entity(key, value));
				value = null;
			} else if (len == Constant.semicolon) {
				key = sb.toString();
				sb.setLength(0);
			} else {
				sb.append((char) len);
			}
		}

		return this;
	}

	public JsonObject() {
		// TODO Auto-generated constructor stub
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		keys = new ArrayList<>();
		entities = new ArrayList<>();
	}

	protected JsonObject pars(Reader reader) throws Exception {
		int len = -1;
		String key = null;
		Object value = null;
		StringBuffer sb = new StringBuffer();
		len = reader.read();
		if (len != Constant.LEFT_CURLY_BRACES)
			throw new RuntimeException("is not jsonObject");
		while ((len = reader.read()) != -1) {
			if (len == Constant.LEFT_BARCKETS) {// 开始数组
				value = new JsonArray(reader);
			} else if (len == Constant.LEFT_CURLY_BRACES) {// 开始对象
				value = new JsonObject(reader);
			} else if (len == Constant.RIGHT_CURLY_BRACES) {// 结束自己
				if (sb.length() > 0) {
					value = sb.toString();
					sb.setLength(0);
				}
				keys.add(key);
				entities.add(new Entity(key, value));
				break;
			} else if (len == Constant.POINT) {

			} else if (len == Constant.comma) {
				if (sb.length() > 0) {
					value = sb.toString();
					sb.setLength(0);
				}
				keys.add(key);
				entities.add(new Entity(key, value));
				value = null;
			} else if (len == Constant.semicolon) {
				key = sb.toString();
				sb.setLength(0);
			} else {
				sb.append((char) len);
			}
		}

		return this;
	}

	public JsonObject pars(InputStream is) throws Exception {
		return pars(new InputStreamReader(is));
	}

	public JsonObject pars(InputStream is, String charsetName) throws Exception {
		return pars(new InputStreamReader(is, charsetName));
	}

	public JsonObject pars(String string) throws Exception {
		return pars(new StringReader(string));
	}

	public JsonObject pars(URL url) throws Exception {
		return pars(url.openStream());
	}

	public JsonObject pars(URL url, String charsetName) throws Exception {
		return pars(url.openStream(), charsetName);
	}

	public JsonObject parsUrl(String url) throws Exception {
		return pars(new URL(url));
	}

	public JsonObject parsUrl(String url, String charsetName) throws Exception {
		return pars(new URL(url), charsetName);
	}

	public JsonObject pars(File file) throws Exception {
		return pars(new FileReader(file));
	}

	public JsonObject parsFile(String file) throws Exception {
		return pars(new FileReader(file));
	}

	public JsonObject pars(URI url) throws Exception {
		return pars(url.toURL());
	}

	public JsonObject parsUri(String uri) throws Exception {
		return pars(new URI(uri));
	}

	public Object get(String key) {
		int index = entities.indexOf(new Entity(key, null));
		if (index >= 0)
			return entities.get(index).getObject();
		throw new RuntimeException("not key");
	}

	public String getString(String key) {
		Object object = get(key);
		return object == null ? "" : object.toString();
	}

	public int getInt(String key, int into) {
		try {
			if (into == 16) {
				String value = getString(key);
				if (value.startsWith("0x"))
					return string2int(value.substring(2), 16);
				return string2int(value, 16);
			}
			return string2int(getString(key), into);
		} catch (NumberFormatException e) {
			// TODO: handle exception
			throw e;
		}
	}

	public int getInt(String key) {
		String value = getString(key);
		if (value.startsWith("0x"))
			return string2int(value.substring(2), 16);
		return string2int(value, 10);
	}

	private int string2int(String key, int into) {
		return Integer.valueOf(key, into);
	}

	private long string2long(String key, int into) {
		return Long.valueOf(key, into);
	}

	public long getLong(String key, int into) {
		try {
			if (into == 16) {
				String value = getString(key);
				if (value.startsWith("0x"))
					return string2long(value.substring(2), 16);
				return string2long(value, 16);
			}
			return string2long(getString(key), into);
		} catch (NumberFormatException e) {
			// TODO: handle exception
			throw e;
		}
	}

	public long getLong(String key) {
		String value = getString(key);
		if (value.startsWith("0x"))
			return string2long(value.substring(2), 16);
		return string2long(value, 10);
	}

	public float getFloat(String key, int into) {
		try {
			return Float.valueOf(getString(key));
		} catch (NumberFormatException e) {
			// TODO: handle exception
			throw e;
		}
	}

	public double getDouble(String key, int into) {
		try {
			return Double.valueOf(getString(key));
		} catch (NumberFormatException e) {
			// TODO: handle exception
			throw e;
		}
	}

	public JsonObject getJsonObject(String key) {
		Object object = get(key);
		if (object instanceof JsonObject)
			return (JsonObject) object;
		throw new RuntimeException("this key is not jsonObject");
	}

	public JsonArray getJsonArray(String key) {
		Object object = get(key);
		if (object instanceof JsonArray)
			return (JsonArray) object;
		throw new RuntimeException("this key is not jsonArray");
	}

	public Iterator<String> getKeys() {
		return keys.iterator();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		if (entities != null && entities.size() > 0) {
			for (int i = 0; i < entities.size(); i++) {
				Entity entity = entities.get(i);
				Object value = entity.object;
				sb.append("\"").append(entity.key).append("\"").append(":");
				if (value instanceof String) {
					sb.append("\"").append(entity.object.toString())
							.append("\"");
				} else
					sb.append(entity.object);
				sb.append(",");
			}
			return "{" + sb.substring(0, sb.length() - 1) + "}";
		}
		return "{" + sb.toString() + "}";
	}

	private class Entity {
		String key;
		Object object;

		public Entity(String key, Object object) {
			// TODO Auto-generated constructor stub
			this.key = key;
			this.object = object;
		}

		public Object getObject() {
			return object;
		}

		@Override
		public boolean equals(Object arg0) {
			// TODO Auto-generated method stub
			if (arg0 == null
					|| !arg0.getClass().getName().equals(getClass().getName()))
				return false;
			Entity entity = (Entity) arg0;
			return entity.key.equals(key);
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return object.toString();
		}
	}
}
