package com.xh.json;

import java.util.ArrayList;
import java.util.List;

/**
 * json com.xh.json
 * 2018 2018-1-29 下午4:28:48
 * instructions：
 * author:liuhuiliang  email:825378291@qq.com
 **/

public class JsonArray {
	public static char POINT = '\"';
	public static char comma = ',';
	public final static char LEFT_BARCKETS = '[';
	public final static char RIGHT_BARCKETS = ']';
	public final static char LEFT_CURLY_BRACES = '{';
	public final static char RIGHT_CURLY_BRACES = '}';
	List<Object> objects2;

	public JsonArray(byte[] buff) throws Exception {
		// TODO Auto-generated constructor stub
		objects2 = new ArrayList<>();
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
							objects2.add(new JsonObject(by));
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
							objects2.add(new JsonArray(by));
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
			} else if (c == comma) {
				if (len > 0) {
					objects2.add(new String(buff, startWidth, len,
							JsonPars.EN_CODE));
					len = 0;
				}
			} else if (c == RIGHT_BARCKETS) {
				if (len > 0) {
					objects2.add(new String(buff, startWidth, len,
							JsonPars.EN_CODE));
					len = 0;
				}
			} else {
				if (len == 0)
					startWidth = i;
				len++;
			}
		}
	}

	public Object get(int i) {
		return objects2.get(i);
	}

	public String getString(int i) {
		return get(i).toString();
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
					sb.append(objects2.get(i).toString());
				sb.append(",");
			}
			return "[" + sb.substring(0, sb.length() - 1) + "]";
		}
		return "[" + sb.toString() + "]";
	}
}
