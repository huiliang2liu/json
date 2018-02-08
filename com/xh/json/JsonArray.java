package com.xh.json;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * json com.xh.json 2018 2018-2-8 下午3:26:09 instructions： author:liuhuiliang
 * email:825378291@qq.com
 **/

public class JsonArray {
	List<Object> objects2;

	public JsonArray(Reader reader) throws Exception {
		// TODO Auto-generated constructor stub
		objects2 = new ArrayList<>();
		Object value = null;
		int len = -1;
		StringBuffer sb = new StringBuffer();
		while ((len = reader.read()) != -1) {
			if (len == Constant.LEFT_BARCKETS) {// 开始数组
				value = new JsonArray(reader);
			} else if (len == Constant.LEFT_CURLY_BRACES) {// 开始对象
				value = new JsonObject(reader);
			} else if (len == Constant.RIGHT_BARCKETS) {// 结束自己
				if (sb.length() > 0) {
					value = sb.toString();
					sb.setLength(0);
				}
				objects2.add(value);
				break;
			} else if (len == Constant.POINT) {

			} else if (len == Constant.comma) {
				if (sb.length() > 0) {
					value = sb.toString();
					sb.setLength(0);
				}
				objects2.add(value);
			} else {
				sb.append((char) len);
			}
		}
	}

	public Object get(int i) {
		return objects2.get(i);
	}

	public String getString(int i) {
		Object object = get(i);
		return object == null ? "" : object.toString();
	}

	public int getInt(int i, int into) {
		try {
			if (into == 16) {
				String value = getString(i);
				if (value.startsWith("0x"))
					return string2int(value.substring(2), 16);
				return string2int(value, 16);
			}
			return Integer.valueOf(getString(i), into);
		} catch (NumberFormatException e) {
			// TODO: handle exception
			throw e;
		}
	}

	public int getInt(int i) {
		String value = getString(i);
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

	public long getLong(int i, int into) {
		try {
			if (into == 16) {
				String value = getString(i);
				if (value.startsWith("0x"))
					return string2long(value.substring(2), 16);
				return string2long(value, 16);
			}
			return Long.valueOf(getString(i), into);
		} catch (NumberFormatException e) {
			// TODO: handle exception
			throw e;
		}
	}

	public long getLong(int i) {
		String value = getString(i);
		if (value.startsWith("0x"))
			return string2long(value.substring(2), 16);
		return string2long(value, 10);
	}

	public float getFloat(int i) {
		try {
			return Float.valueOf(getString(i));
		} catch (NumberFormatException e) {
			// TODO: handle exception
			throw e;
		}
	}

	public double getDouble(int i) {
		try {
			return Double.valueOf(getString(i));
		} catch (NumberFormatException e) {
			// TODO: handle exception
			throw e;
		}
	}

	public JsonObject getJsonObject(int i) {
		Object object = get(i);
		if (object instanceof JsonObject)
			return (JsonObject) object;
		throw new RuntimeException("this index is not jsonObject");
	}

	public JsonArray getJsonArray(int i) {
		Object object = get(i);
		if (object instanceof JsonArray)
			return (JsonArray) object;
		throw new RuntimeException("this index is not jsonArray");
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		if (objects2 != null && objects2.size() > 0) {
			for (int i = 0; i < objects2.size(); i++) {
				Object value = objects2.get(i);
				if (value instanceof String) {
					sb.append("\"").append(objects2.get(i).toString())
							.append("\"");
				} else
					sb.append(objects2.get(i));
				sb.append(",");
			}
			return "[" + sb.substring(0, sb.length() - 1) + "]";
		}
		return "[" + sb.toString() + "]";
	}
}
