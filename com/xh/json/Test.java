package com.xh.json;


/**
 * json com.xh.json 2018 2018-2-8 上午9:48:31 instructions： author:liuhuiliang
 * email:825378291@qq.com
 **/

public class Test {
	public static char POINT = '\"';
	public static char semicolon = ':';
	public static char comma = ',';
	public final static char LEFT_BARCKETS = '[';
	public final static char RIGHT_BARCKETS = ']';
	public final static char LEFT_CURLY_BRACES = '{';
	public final static char RIGHT_CURLY_BRACES = '}';

	public static void main(String[] args) {
		try {
			// Reader reader = new FileReader("D:\\新建文件夹/test.txt");
			long time = System.currentTimeMillis();
			String string = "{刘:{慧:良,nihao:nihao,nihao:nihao},array:[{刘:0123},2,3,4,[1,2,3,4]]}";
			// Reader reader = new InputStreamReader(new FileInputStream(
			// "D:\\新建文件夹/test.txt"));
			JsonObject object1 = JsonPars
					.parsJsonObjectUrl(
							"http://ad.advst.cp33.ott.cibntv.net/ott/2.0/get?m=jmxml3IjjPlqVGycPgPYAYaNQlX%2FnN%2BwAu%2BvWeNzcPpwJgm27AT%2BJV37D%2FLj+RZSLvY7b89HF00fzmq8CHBTfBhf7UzTg2Q4j9fDRZkMODHlvQ%2FH9QwjzHJ6i+OYWR%2Ba4iVf4lctXaQten2%2FzOjdGlAApTxzv33unHDm0vtrmsxO5iCri0NUjs+45jRfFATG5GqUt7kT4Qgo2yNkDQeDxsBzDzmz489to%2BHsvlYWcIsHzTPGEl1+lsA9s8DGZERfTyWE6RVPSuRQTfxRTssbXtuIWgJuG%2BUMIj7Vh2ze1J3JQGGB+YcuHJDl022gONqUEPP0LvLBxwOEs3sWxTKxy%2BluzzYenOlj1yMIefh3yOx3y+48%2FqGHURne2ZxBYsV8v%2B%2BlNBdd6BrkxsHq1zQXp5oRLdKh%2FUttciYJ%2F0S4QP+55vthbL3eRhYrf0NwHCZfa0Ar51fcxiek%2F7xLXGzga6oCLxODgVWbA1ygLlp+FSAenB8fbYRBMuFiO7kcW14umxIFdFnG8i%2BTlqxVECoraSmj6zexd%2FyDPmqC+frL3zyK%2BZBX8tbY6do4HaslvcnuORD%2BWtBYz%2FfKvGrY0m5iy6QlrU%2FToz1XN+XdUTlPv8BDRlso5zB15%2FVSrCGeChCjERqwiCx8fMKip4gbfIhkAzlZyGNs8r+IBuF%2BeFcbXjy%2BHLp%2FQ45KfnlbMx2sgumwwwoDTp2tJaDHzY0JDgLDSn60ZYF+lu1F4ffpNQMpl%2BEuumCZPmkzxcqDgQuTL6PkTzSVN9jZZCamoIBiLhmiXeAG+JMZALqrcc3plJtuHiSjx%2FJqOf7iemz94wVTTMefLDoPUD3zpLl%2BqDg0tOT0q+XiKUf%2FEwC6lUAXANwyGOkrmWSQY%2Fa4N8xTmJwUnvA7WrkquzutuSAmKJ73tj+w1r5EqVobWJ45PSC4PXEVcPR5RMgIy5POcLoBZWHb6AT9Zh4Fi%2BKlV8SDhbp+LzrPHX1L5vMqK6f2DDV4N2y%2BBgzFezpKyulNzoTD397CrQu0FL4jLFawWUsr+W9OMc1C0aeMV7M7qrX%2FI1IMGtlbvytxTjldViivu%2FXneGfo1mxmRyBDBexzA+3LeDG62tLoqRlcqKkgMHLW4wDSVXvRgwhEFQxtxH",
							"utf-8");
//			JsonObject object1 = JsonPars.parsJsonObject(string);
			System.out.println(System.currentTimeMillis() - time);
			System.out.println(object1);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
