package com.xh.json;

import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URL;
import java.util.List;

import com.xh.reflect.ClassManager;
import com.xh.reflect.FieldManager;

/**
 * json com.xh.json 2018 2018-2-8 下午3:25:33 instructions： author:liuhuiliang
 * email:825378291@qq.com
 **/

public class ParsObject {
	private Class cl;
	Object object;

	protected ParsObject(Reader reader, Class cl) throws Exception {
		// TODO Auto-generated constructor stub
		this(cl);
		System.out.println(cl.getName());
		pars1(reader);
	}

	protected ParsObject(Class cl) {
		// TODO Auto-generated constructor stub
		this.cl = cl;
		init();
	}

	private void pars1(Reader reader) throws Exception {
		int len = -1;
		String key = null;
		StringBuffer sb = new StringBuffer();
		while ((len = reader.read()) != -1) {
			if (len == Constant.LEFT_BARCKETS) {// 开始数组
				setArray(key, reader);
			} else if (len == Constant.LEFT_CURLY_BRACES) {// 开始对象
				Field field = FieldManager.field(cl, key);
				if (field != null)
					FieldManager.set_field(object, field, new ParsObject(
							reader, field.getType()).object);
			} else if (len == Constant.RIGHT_CURLY_BRACES) {// 结束自己
				if (sb.length() > 0) {
					setField(key, sb.toString());
					sb.setLength(0);
				}
				break;
			} else if (len == Constant.POINT) {

			} else if (len == Constant.comma) {
				if (sb.length() > 0) {
					setField(key, sb.toString());
					sb.setLength(0);
				}
			} else if (len == Constant.semicolon) {
				key = sb.toString();
				sb.setLength(0);
			} else {
				sb.append((char) len);
			}
		}

	}

	private void setArray(String key, Reader reader) throws Exception {
		Field field = FieldManager.field(cl, key);
		if (field == null)
			return;
		Class typeClass = field.getType();
		Class mClass = null;
		boolean isList = false;
		if (typeClass.isArray()) {// 数组
			mClass = typeClass.getComponentType();
		} else {// list类型
			if (java.util.List.class.isAssignableFrom(field.getType())
					|| field.getType() == java.util.List.class) {
				isList = true;
				// 如果是List类型，得到其Generic的类型
				Type genericType = field.getGenericType();
				if (genericType == null)
					return;
				// 如果是泛型参数的类型
				if (genericType instanceof ParameterizedType) {
					ParameterizedType pt = (ParameterizedType) genericType;
					// 得到泛型里的class类型对象
					mClass = (Class<?>) pt.getActualTypeArguments()[0];
				}
			}
		}
		if (mClass != null) {
			List objects2 = new ParsArray(reader, mClass).objects2;
			if (isList)
				FieldManager.set_field(object, field, objects2);
			else
				FieldManager.set_field(object, field, objects2.toArray());
		}
	}

	private void setField(String key, String value) {
		Field field = FieldManager.field(cl, key);
		if (field == null)
			return;
		Class mClass = field.getType();
		Object object2 = null;
		if (Boolean.class.isAssignableFrom(mClass)
				|| boolean.class.isAssignableFrom(mClass))
			object2 = string2boolean(value);
		else if (Byte.class.isAssignableFrom(mClass)
				|| byte.class.isAssignableFrom(mClass)) {
			byte b = (byte) string2long(value);
			object2 = b;
		} else if (Short.class.isAssignableFrom(mClass)
				|| short.class.isAssignableFrom(mClass)) {
			short s = (short) string2long(value);
			object2 = s;
		} else if (Integer.class.isAssignableFrom(mClass)
				|| int.class.isAssignableFrom(mClass)) {
			int i = (int) string2long(value);
			object2 = i;
		} else if (Long.class.isAssignableFrom(mClass)
				|| long.class.isAssignableFrom(mClass)) {
			object2 = string2long(value);
		} else if (Float.class.isAssignableFrom(mClass)
				|| float.class.isAssignableFrom(mClass)) {
			object2 = string2float(value);
		} else if (Double.class.isAssignableFrom(mClass)
				|| double.class.isAssignableFrom(mClass)) {
			object2 = string2double(value);
		} else if (String.class.isAssignableFrom(mClass))
			object2 = value;
		else
			return;
		FieldManager.set_field(object, field, object2);
	}

	private void init() {
		// TODO Auto-generated method stub
		Exception exception = null;
		try {
			object = ClassManager.new_object(cl);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			exception = e;
		}
		if (exception != null)
			throw new RuntimeException(exception);
	}

	protected ParsObject pars(Reader reader) throws Exception {
		int len = -1;
		String key = null;
		StringBuffer sb = new StringBuffer();
		len = reader.read();
		if (len != Constant.LEFT_CURLY_BRACES)
			throw new RuntimeException("is not jsonObject");
		while ((len = reader.read()) != -1) {
			if (len == Constant.LEFT_BARCKETS) {// 开始数组
				setArray(key, reader);
			} else if (len == Constant.LEFT_CURLY_BRACES) {// 开始对象
				Field field = FieldManager.field(cl, key);
				if (field != null)
					FieldManager.set_field(object, field, new ParsObject(
							reader, field.getType()).object);
			} else if (len == Constant.RIGHT_CURLY_BRACES) {// 结束自己
				if (sb.length() > 0) {
					setField(key, sb.toString());
					sb.setLength(0);
				}
				break;
			} else if (len == Constant.POINT) {

			} else if (len == Constant.comma) {
				if (sb.length() > 0) {
					setField(key, sb.toString());
					sb.setLength(0);
				}
			} else if (len == Constant.semicolon) {
				key = sb.toString();
				sb.setLength(0);
			} else {
				sb.append((char) len);
			}
		}

		return this;
	}

	public ParsObject pars(InputStream is) throws Exception {
		return pars(new InputStreamReader(is));
	}

	public ParsObject pars(InputStream is, String charsetName) throws Exception {
		return pars(new InputStreamReader(is, charsetName));
	}

	public ParsObject pars(String string) throws Exception {
		return pars(new StringReader(string));
	}

	public ParsObject pars(URL url) throws Exception {
		return pars(url.openStream());
	}

	public ParsObject pars(URL url, String charsetName) throws Exception {
		return pars(url.openStream(), charsetName);
	}

	public ParsObject parsUrl(String url) throws Exception {
		return pars(new URL(url));
	}

	public ParsObject parsUrl(String url, String charsetName) throws Exception {
		return pars(new URL(url), charsetName);
	}

	public ParsObject pars(File file) throws Exception {
		return pars(new FileReader(file));
	}

	public ParsObject parsFile(String file) throws Exception {
		return pars(new FileReader(file));
	}

	public ParsObject pars(URI url) throws Exception {
		return pars(url.toURL());
	}

	public ParsObject parsUri(String uri) throws Exception {
		return pars(new URI(uri));
	}

	public long string2long(String value) {
		try {
			if (value.startsWith("0x"))
				return Long.valueOf(value.substring(2), 16);
			if (value.startsWith("0"))
				return Long.valueOf(value.substring(1), 8);
			return Long.valueOf(value, 10);
		} catch (NumberFormatException e) {
			// TODO: handle exception
			throw e;
		}
	}

	public boolean string2boolean(String key) {
		try {
			return Boolean.valueOf(key);
		} catch (Exception e) {
			// TODO: handle exception
			try {
				return Double.valueOf(key) > 0;
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return false;
	}

	public float string2float(String key) {
		try {
			return Float.valueOf(key);
		} catch (NumberFormatException e) {
			// TODO: handle exception
			throw e;
		}
	}

	public double string2double(String key) {
		try {
			return Double.valueOf(key);
		} catch (NumberFormatException e) {
			// TODO: handle exception
			throw e;
		}
	}

}
