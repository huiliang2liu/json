package com.xh.json;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * json com.xh.json 2018 2018-2-8 ÏÂÎç3:23:27 instructions£º author:liuhuiliang
 * email:825378291@qq.com
 **/

public class JsonPars {
	public static Object pars(Reader reader) {
		try {
			int len = reader.read();
			if (len == Constant.LEFT_BARCKETS)
				return new JsonArray(reader);
			if (len == Constant.LEFT_CURLY_BRACES)
				return new JsonObject(reader);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static JsonObject parsJsonObject(Reader reader) {
		Object object = pars(reader);
		if (object instanceof JsonObject)
			return (JsonObject) object;
		throw new RuntimeException("is not JsonObject");
	}

	public static JsonObject parsJsonObject(InputStream is) {
		return parsJsonObject(new InputStreamReader(is));
	}

	public static JsonObject parsJsonObject(InputStream is, String charsetName)
			throws UnsupportedEncodingException {
		return parsJsonObject(new InputStreamReader(is, charsetName));
	}

	public static JsonObject parsJsonObject(String string) {
		return parsJsonObject(new StringReader(string));
	}

	public static JsonObject parsJsonObject(URL url) throws IOException {
		return parsJsonObject(url.openStream());
	}

	public static JsonObject parsJsonObject(URL url, String charsetName)
			throws IOException {
		return parsJsonObject(url.openStream(), charsetName);
	}

	public static JsonObject parsJsonObjectUrl(String url)
			throws MalformedURLException, IOException {
		return parsJsonObject(new URL(url));
	}

	public static JsonObject parsJsonObjectUrl(String url, String charsetName)
			throws MalformedURLException, IOException {
		return parsJsonObject(new URL(url), charsetName);
	}

	public static JsonObject parsJsonObject(File file)
			throws FileNotFoundException {
		return parsJsonObject(new FileReader(file));
	}

	public static JsonObject parsJsonObjectFile(String file)
			throws FileNotFoundException {
		return parsJsonObject(new FileReader(file));
	}

	public static JsonObject parsJsonObject(URI url)
			throws MalformedURLException, IOException {
		return parsJsonObject(url.toURL());
	}

	public static JsonObject parsJsonObjectUri(String uri)
			throws MalformedURLException, IOException, URISyntaxException {
		return parsJsonObject(new URI(uri));
	}

	public static JsonArray parsJsonArray(Reader reader) {
		Object object = pars(reader);
		if (object instanceof JsonArray)
			return (JsonArray) object;
		throw new RuntimeException("is not JsonArray");
	}

	public static JsonArray parsJsonArray(InputStream is) {
		return parsJsonArray(new InputStreamReader(is));
	}

	public static JsonArray parsJsonArray(InputStream is, String charsetName)
			throws UnsupportedEncodingException {
		return parsJsonArray(new InputStreamReader(is, charsetName));
	}

	public static JsonArray parsJsonArray(String string) {
		return parsJsonArray(new StringReader(string));
	}

	public static JsonArray parsJsonArray(URL url) throws IOException {
		return parsJsonArray(url.openStream());
	}

	public static JsonArray parsJsonArray(URL url, String charsetName)
			throws IOException {
		return parsJsonArray(url.openStream(), charsetName);
	}

	public static JsonArray parsJsonArrayUrl(String url)
			throws MalformedURLException, IOException {
		return parsJsonArray(new URL(url));
	}

	public static JsonArray parsJsonArrayUrl(String url, String charsetName)
			throws MalformedURLException, IOException {
		return parsJsonArray(new URL(url), charsetName);
	}

	public static JsonArray parsJsonArray(File file)
			throws FileNotFoundException {
		return parsJsonArray(new FileReader(file));
	}

	public static JsonArray parsJsonArrayFile(String file)
			throws FileNotFoundException {
		return parsJsonArray(new FileReader(file));
	}

	public static JsonArray parsJsonArray(URI url)
			throws MalformedURLException, IOException {
		return parsJsonArray(url.toURL());
	}

	public static JsonArray parsJsonArray(URI url, String charsetName)
			throws MalformedURLException, IOException {
		return parsJsonArray(url.toURL(), charsetName);
	}

	public static JsonArray parsJsonArrayUri(String url)
			throws MalformedURLException, IOException, URISyntaxException {
		return parsJsonArray(new URI(url));
	}

	public static JsonArray parsJsonArrayUri(String url, String charsetName)
			throws MalformedURLException, IOException, URISyntaxException {
		return parsJsonArray(new URI(url), charsetName);
	}

}
