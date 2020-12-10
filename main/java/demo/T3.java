package demo;

import java.net.MalformedURLException;
import java.net.URLDecoder;
import java.net.URLEncoder;
 
public class T3 {
	public static void main(String[] args) throws MalformedURLException {
		String test = "图文问诊";
		String encode = URLEncoder.encode(test);
		String decode = URLDecoder.decode("%E5%9B%BE%E6%96%87%E9%97%AE%E8%AF%8A");
		
		System.out.println("汉字("+test+")转码为 : "+encode);
		System.out.println(encode+" 转换为汉字为 : "+decode);

		java.net.URL  url = new  java.net.URL("http://blog.csdn.net:8989/zhujianlin1990");

		String host = url.getHost();// 获取主机名

		String file = url.getFile();

		System.out.println("host:"+host);// 结果 blog.csdn.net

		System.out.println("file:"+file);// 结果 blog.csdn.net



	}
}