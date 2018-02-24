package com.xh.json;

import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.Array;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * json com.xh.json 2018 2018-2-8 下午3:26:09 instructions： author:liuhuiliang
 * email:825378291@qq.com
 **/

public class ParsArray {
	List objects2;
	Class cl;

	protected ParsArray(Reader reader, Class cl) throws Exception {
		// TODO Auto-generated constructor stub
		this(cl);
		pars1(reader);

	}

	public ParsArray(Class cl) {
		// TODO Auto-generated constructor stub
		init();
		this.cl = cl;
	}

	private void init() {
		// TODO Auto-generated method stub
		objects2 = new ArrayList<>();
	}

	private ParsArray pars1(Reader reader) throws Exception {
		Object value = null;
		int len = -1;
		StringBuffer sb = new StringBuffer();
		while ((len = reader.read()) != -1) {
			if (len == Constant.LEFT_BARCKETS) {// 开始数组
				// value = new ParsArray(reader);
				if (cl.isArray()) {
					Object objects[] = new ParsArray(reader,
							cl.getComponentType()).objects2.toArray();
					Object object = Array.newInstance(cl.getComponentType(),
							objects.length);
					System.arraycopy(objects, 0, object, 0, objects.length);
					objects2.add(object);
				}
			} else if (len == Constant.LEFT_CURLY_BRACES) {// 开始对象
				objects2.add(new ParsObject(reader, cl).object);
			} else if (len == Constant.RIGHT_BARCKETS) {// 结束自己
				if (sb.length() > 0) {
					value = sb.toString();
					sb.setLength(0);
					objects2.add(value);
				}
				break;
			} else if (len == Constant.POINT) {

			} else if (len == Constant.comma) {
				if (sb.length() > 0) {
					value = sb.toString();
					sb.setLength(0);
					objects2.add(value);
				}
			} else {
				sb.append((char) len);
			}
		}
		return this;
	}

	public ParsArray pars(Reader reader) throws Exception {
		Object value = null;
		int len = -1;
		StringBuffer sb = new StringBuffer();
		len = reader.read();
		if (len != Constant.LEFT_BARCKETS)
			throw new RuntimeException("is not jsonArray");
		while ((len = reader.read()) != -1) {
			if (len == Constant.LEFT_BARCKETS) {// 开始数组
				// value = new ParsArray(reader);
				if (cl.isArray()) {
					Object objects[] = new ParsArray(reader,
							cl.getComponentType()).objects2.toArray();
					Object object = Array.newInstance(cl.getComponentType(),
							objects.length);
					System.arraycopy(objects, 0, object, 0, objects.length);
					objects2.add(object);
				}
			} else if (len == Constant.LEFT_CURLY_BRACES) {// 开始对象
				objects2.add(new ParsObject(reader, cl).object);
			} else if (len == Constant.RIGHT_BARCKETS) {// 结束自己
				if (sb.length() > 0) {
					value = sb.toString();
					sb.setLength(0);
					objects2.add(value);
				}
				break;
			} else if (len == Constant.POINT) {

			} else if (len == Constant.comma) {
				if (sb.length() > 0) {
					value = sb.toString();
					sb.setLength(0);
					objects2.add(value);
				}
			} else {
				sb.append((char) len);
			}
		}
		return this;
	}

	public ParsArray pars(InputStream is) throws Exception {
		return pars(new InputStreamReader(is));
	}

	public ParsArray pars(InputStream is, String charsetName) throws Exception {
		return pars(new InputStreamReader(is, charsetName));
	}

	public ParsArray pars(String string) throws Exception {
		return pars(new StringReader(string));
	}

	public ParsArray pars(URL url) throws Exception {
		return pars(url.openStream());
	}

	public ParsArray pars(URL url, String charsetName) throws Exception {
		return pars(url.openStream(), charsetName);
	}

	public ParsArray parsUrl(String url) throws Exception {
		return pars(new URL(url));
	}

	public ParsArray parsUrl(String url, String charsetName) throws Exception {
		return pars(new URL(url), charsetName);
	}

	public ParsArray pars(File file) throws Exception {
		return pars(new FileReader(file));
	}

	public ParsArray parsFile(String file) throws Exception {
		return pars(new FileReader(file));
	}

	public ParsArray pars(URI url) throws Exception {
		return pars(url.toURL());
	}

	public ParsArray pars(URI url, String charsetName) throws Exception {
		return pars(url.toURL(), charsetName);
	}

	public ParsArray parsUri(String url) throws Exception {
		return pars(new URI(url));
	}

	public ParsArray parsUri(String url, String charsetName) throws Exception {
		return pars(new URI(url), charsetName);
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
		return Long.valueOf(key, into);
	}

	public long getLong(int i, int into) {
		try {
			if (into == 16) {
				String value = getString(i);
				if (value.startsWith("0x"))
					return string2long(value.substring(2), 16);
				return string2long(value, 16);
			}
			return string2long(getString(i), into);
		} catch (NumberFormatException e) {
			// TODO: handle exception
			throw e;
		}
	}

	public long getLong(int i) {
		String value = getString(i);
		if (value.startsWith("0x"))
			return string2long(value.substring(2), 16);
		if (value.startsWith("0"))
			return string2long(value.substring(1), 8);
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

	public ParsArray getJsonArray(int i) {
		Object object = get(i);
		if (object instanceof ParsArray)
			return (ParsArray) object;
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
