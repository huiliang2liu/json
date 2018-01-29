package com.xh.json;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * json com.xh.json 2018 2018-1-29 ÏÂÎç4:29:20 instructions£º author:liuhuiliang
 * email:825378291@qq.com
 **/

public class JsonObject {
	public static char POINT = '\"';
	public static char semicolon = ':';
	public static char comma = ',';
	public final static char LEFT_BARCKETS = '[';
	public final static char RIGHT_BARCKETS = ']';
	public final static char LEFT_CURLY_BRACES = '{';
	public final static char RIGHT_CURLY_BRACES = '}';
	List<Entity> entities;
	List<String> keys;

	protected JsonObject(byte[] buff) throws Exception {
		// TODO Auto-generated constructor stub
		entities = new ArrayList<>();
		keys = new ArrayList<>();
		// StringBuffer sb = new StringBuffer();

		String key = null;
		int startWidth = 0;
		int len = 0;
		for (int i = 1; i < buff.length; i++) {
			int c = buff[i];
			int k = i;
			if (c == LEFT_CURLY_BRACES) {
				int t = 0;
				for (i++; i < buff.length - 1; i++) {
					int b = buff[i];
					if (b == LEFT_CURLY_BRACES) {
						t++;
					} else if (b == RIGHT_CURLY_BRACES) {
						if (t == 0) {
							byte[] by = new byte[i - k + 1];
							System.arraycopy(buff, k, by, 0, by.length);
							entities.add(new Entity(key, new JsonObject(by)));
							keys.add(key);
							break;
						} else
							t--;
					}
				}
			} else if (c == LEFT_BARCKETS) {
				int t = 0;
				for (i++; i < buff.length - 1; i++) {
					int b = buff[i];
					if (b == LEFT_BARCKETS) {
						t++;
					} else if (b == RIGHT_BARCKETS) {
						if (t == 0) {
							byte[] by = new byte[i - k + 1];
							System.arraycopy(buff, k, by, 0, by.length);
							entities.add(new Entity(key, new JsonArray(by)));
							keys.add(key);
							break;
						} else
							t--;
					}
				}
			} else if (c == POINT) {
				// if (add) {
				// add = false;
				// } else
				// add = true;
			} else if (c == semicolon) {
				key = new String(buff, startWidth, len, JsonPars.EN_CODE);
				// sb.setLength(0);
				len = 0;
			} else if (c == comma) {
				entities.add(new Entity(key, new String(buff, startWidth, len,
						JsonPars.EN_CODE)));
				keys.add(key);
				len = 0;
			} else if (c == RIGHT_CURLY_BRACES) {
				if (len > 0) {
					entities.add(new Entity(key, new String(buff, startWidth,
							len, JsonPars.EN_CODE)));
					keys.add(key);
					len = 0;
				}
			} else {
				if (len == 0)
					startWidth = i;
				len++;
			}
		}
	}

	public Object get(String key) {
		int index = entities.indexOf(new Entity(key, null));
		if (index >= 0)
			return entities.get(index).getObject();
		throw new RuntimeException("not key");
	}

	public String getString(String key) {
		return get(key).toString();
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
		return Integer.valueOf(key, into);
	}

	public long getLong(String key, int into) {
		try {
			if (into == 16) {
				String value = getString(key);
				if (value.startsWith("0x"))
					return string2long(value.substring(2), 16);
				return string2long(value, 16);
			}
			return Long.valueOf(getString(key), into);
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
					sb.append(entity.object.toString());
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
