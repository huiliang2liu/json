package com.xh.json;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.net.URI;
import java.net.URL;

/**
 * json com.xh.json
 * 2018 2018-1-29 下午4:28:09
 * instructions：
 * author:liuhuiliang  email:825378291@qq.com
 **/

public class JsonPars {
	public static char POINT = '\"';
	public static char semicolon = ':';
	public static char comma = ',';
	public final static char LEFT_BARCKETS='[';
	public final static char RIGHT_BARCKETS=']';
	public final static char LEFT_CURLY_BRACES='{';
	public final static char RIGHT_CURLY_BRACES='}';
	protected static String EN_CODE = "utf-8";
	public static void setEnCode(String enCode) {
		EN_CODE = enCode;
	}
/**
 * 
 * 2018 2018-1-29 下午4:19:47
 * annotation：
 * author：liuhuiliang
 * email ：825378291@qq.com
 * @param buff
 * @return
 * @throws Exception
 *Object
 */
	public static Object pars(byte[] buff) throws Exception {
		if (buff[0] == LEFT_BARCKETS) {
			return new JsonArray(buff);
		} else if (buff[0] == LEFT_CURLY_BRACES) {
			return new JsonObject(buff);
		}
		throw new RuntimeException("not json");
	}

	public static Object pars(InputStream is) throws Exception {
		if (is == null)
			return null;
		is = getInputStream(is);
		int len;
		byte[] buff = new byte[1024 * 1024];
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		while ((len = is.read(buff)) > 0) {
			os.write(buff, 0, len);
		}
		os.flush();
		buff = null;
		byte[] buf = os.toByteArray();
		is.close();
		return pars(buf);
	}

	public static Object pars(String string) throws Exception {
		return pars(string.getBytes(EN_CODE));
	}

	public static Object parsUrl(URL url) throws Exception {
		if (url == null)
			return null;
		return pars(url.openStream());
	}

	public static Object parsUrl(String url) throws Exception {
		if (url == null || url.isEmpty())
			return null;
		return parsUrl(new URL(url));
	}

	public static Object parsFile(File file) throws Exception {
		if (file == null)
			return null;
		return parsUri(file.toURI());
	}

	public static Object parsFile(String file) throws Exception {
		if (file == null || file.isEmpty())
			return null;
		return parsFile(new File(file));
	}

	public static Object parsUri(URI url) throws Exception {
		if (url == null)
			return null;
		return parsUrl(url.toURL());
	}

	public static Object parsUri(String uri) throws Exception {
		if (uri == null || uri.isEmpty())
			return null;
		return parsUri(new URI(uri));
	}

	public static JsonObject parsJsonObject(byte[] buff) throws Exception {
		if (buff[0] == LEFT_CURLY_BRACES) {
			return new JsonObject(buff);
		}
		throw new RuntimeException("not jsonObject");
	}

	public static JsonObject parsJsonObject(InputStream is) throws Exception {
		if (is == null)
			return null;
		is = getInputStream(is);
		int len;
		byte[] buff = new byte[1024 * 1024];
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		while ((len = is.read(buff)) > 0) {
			os.write(buff, 0, len);
		}
		os.flush();
		buff = null;
		byte[] buf = os.toByteArray();
		is.close();
		return parsJsonObject(buf);
	}

	public static JsonObject parsJsonObject(String string) throws Exception {
		return parsJsonObject(string.getBytes(EN_CODE));
	}

	public static JsonObject parsJsonObject(URL url) throws Exception {
		if (url == null)
			return null;
		return parsJsonObject(url.openStream());
	}

	public static JsonObject parsUrlJsonObject(String url) throws Exception {
		if (url == null || url.isEmpty())
			return null;
		return parsJsonObject(new URL(url));
	}

	public static JsonObject parsJsonObject(File file) throws Exception {
		if (file == null)
			return null;
		return parsJsonObject(file.toURI());
	}

	public static JsonObject parsFileJsonObject(String file) throws Exception {
		if (file == null || file.isEmpty())
			return null;
		return parsJsonObject(new File(file));
	}

	public static JsonObject parsJsonObject(URI url) throws Exception {
		if (url == null)
			return null;
		return parsJsonObject(url.toURL());
	}

	public static JsonObject parsJsonObjectUri(String uri) throws Exception {
		if (uri == null || uri.isEmpty())
			return null;
		return parsJsonObject(new URI(uri));
	}

	public static JsonArray parsJsonArray(byte[] buff) throws Exception {
		if (buff[0] == LEFT_BARCKETS)
			return new JsonArray(buff);
		throw new RuntimeException("not jsonArray");
	}

	public static JsonArray parsJsonArray(InputStream is) throws Exception {
		if (is == null)
			return null;
		is = getInputStream(is);
		int len;
		byte[] buff = new byte[1024 * 1024];
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		while ((len = is.read(buff)) > 0) {
			os.write(buff, 0, len);
		}
		os.flush();
		buff = null;
		byte[] buf = os.toByteArray();
		is.close();
		return parsJsonArray(buf);
	}

	public static JsonArray parsJsonArray(String string) throws Exception {
		return parsJsonArray(string.getBytes(EN_CODE));
	}

	public static JsonArray parsJsonArray(URL url) throws Exception {
		if (url == null)
			return null;
		return parsJsonArray(url.openStream());
	}

	public static JsonArray parsUrlJsonArray(String url) throws Exception {
		if (url == null || url.isEmpty())
			return null;
		return parsJsonArray(new URL(url));
	}

	public static JsonArray parsJsonArray(File file) throws Exception {
		if (file == null)
			return null;
		return parsJsonArray(file.toURI());
	}

	public static JsonArray parsFileJsonArray(String file) throws Exception {
		if (file == null || file.isEmpty())
			return null;
		return parsJsonArray(new File(file));
	}

	public static JsonArray parsJsonArray(URI url) throws Exception {
		if (url == null)
			return null;
		return parsJsonArray(url.toURL());
	}

	public static JsonArray parsUriJsonArray(String uri) throws Exception {
		if (uri == null || uri.isEmpty())
			return null;
		return parsJsonArray(new URI(uri));
	}

	public static void main(String[] args) {
//		String string = "{\"nihao\":{\"nihao\":\"nihao\",\"nihao\":\"nihao\",\"nihao\":\"nihao\"},\"array\":[{\"nihao\":0123},2,3,4,[1,2,3,4]]}";
		String string = "{刘:{慧:良,nihao:nihao,nihao:nihao},array:[{刘:0123},2,3,4,[1,2,3,4]]}";
		try {
//			System.out.println(string);
			// File file = new File("D:\\新建文件夹/test.txt");
			// FileInputStream fis = new FileInputStream(file);
			// byte[] buff = new byte[(int) file.length()];
			// fis.read(buff);
			// fis.close();
			// System.out.println(new String(buff));
			// URL url = new URL(
			// "http://ad.tvblack.com/ott/2.0/get?m=jmxml3IjjPlqVGycPgPYAYaNQlX%2FnN%2BwZJkqTOWN7hy1fKpUj0NYdaDhN7B8+uGeWe5jO0faTmTsXJIhXw2KlvJoIL2N6XcxRONSue4LZo2SDBFumtgBh2Icb+DXwh5pgCQ7lXWbN72bJGWs96hBg1tPYDkSnezbjO8yX53PFXtNRnXYhKXbG0+cj4Jbhx1pWNE8frHrz6zhT%2B9643uoI%2BiJg%2BgKxPbIgnprQ9f4Q5QozIdMSsX+3o0xJuGBSYfkN8S%2B7kz0RZG6FMRgzy5dFCnQp7%2Fj6DZmBtccm4uac28Ww0bb+KyQ9lwL1ndaHy8Uyk8PAx5OEVueWhBghJHZKHgN%2FTQlshNh%2BxACY44URZqNz+cUhnD0766g0eYRweIFO26OyVmrhFIscjJEo%2FvcB%2BEh5%2B3Z9WlJi573SrDCGF+Hlr%2FrKE%2Fc3K%2BFpqP7vv5zc6WFGfmKesudP5%2FxxftDZaqRGahEYWUa5DgYWPk+AR8NgoRXhi065zlMYS9HDt1mhWHI0I%2Fwgptwlai9gosQ9WgYOBfJyPQzurgu+9nSnfkn7JjAdxJ5jpo7PODtKavvarVueFgyUP4CBShOguPyjIZlmmAJJlsn9+Yh7WUvlLjhbBJaSv9ziFsLaWvukSE8wX5db03pcZhobfscIn68TWxFiZtKPS+R1ZbZXsVY6Xl5GbKJ%2BvJFKRok6b30WY%2FeDbqx9DA%2FVKue36GcFEEa2f%2F1SVR+37oh7o8WzHIFCHn9GMekv1ztyzjuehf9CyYHantexkS7Vr962ByNR%2FSIMwpe+tWDThdlNUxJ1BTCdWegANeNtDV3f9Q%2B2bEkYgybtDRKZl1Go4qTJiyP7jPSI+MX7ku0K8Jmsh3yRbsEiTBeBv3vsBJh1VZZqb4FUTInWqhYcMN0wNfKXLM3WD+YaRb932AADUgnUTxauDYMC8QheAziwlKGzV6xAx9QqTXUFOYN0Ouzy%2FegZB8+D9%2F%2Bas1gh78OEDoSfz459Qpf3%2Fubw0jr4ITCGGiAdLEXpVuWs7QRnWxJWz6b+TDbPM4bGPL93%2Fbbd7Q5%2BJibMxHiNdjZoZ1y9QhKMyEwmVHAnXUeMRzbEKKj3+ZEPW7XLSdaosJCUW1J1dKr9iSll6xj887VDKYBgO0BxgZMboxUDx8keDd0qL+qS6iFKGGfHh%2F");
			// URLConnection connection = url.openConnection();
//			JsonObject jo = (JsonObject) parsFile("D:\\新建文件夹/test.txt");
			JsonObject jo=parsJsonObject(string);
			System.out.println(jo.getJsonArray("array").getJsonObject(0)
					.getLong("刘", 5));
			System.out.println(jo.getJsonObject("刘").getString("慧"));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**
	 * 读取流中前面的字符，看是否有bom，如果有bom，将bom头先读掉丢弃
	 * 
	 * @param in
	 * @return
	 * @throws java.io.IOException
	 */
	public static InputStream getInputStream(InputStream in) throws IOException {

		PushbackInputStream testin = new PushbackInputStream(in);
		int ch = testin.read();
		if (ch != 0xEF) {
			testin.unread(ch);
		} else if ((ch = testin.read()) != 0xBB) {
			testin.unread(ch);
			testin.unread(0xef);
		} else if ((ch = testin.read()) != 0xBF) {
			throw new IOException("错误的UTF-8格式文件");
		} else {
			// 不需要做，这里是bom头被读完了
			// System.out.println("still exist bom");
		}
		return testin;

	}

	/**
	 * 根据一个文件名，读取完文件，干掉bom头。
	 * 
	 * @param fileName
	 * @throws java.io.IOException
	 */
	public static void trimBom(String fileName) throws IOException {

		FileInputStream fin = new FileInputStream(fileName);
		// 开始写临时文件
		InputStream in = getInputStream(fin);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte b[] = new byte[4096];

		int len = 0;
		while (in.available() > 0) {
			len = in.read(b, 0, 4096);
			// out.write(b, 0, len);
			bos.write(b, 0, len);
		}

		in.close();
		fin.close();
		bos.close();

		// 临时文件写完，开始将临时文件写回本文件。
		FileOutputStream out = new FileOutputStream(fileName);
		out.write(bos.toByteArray());
		out.close();
	}
}
