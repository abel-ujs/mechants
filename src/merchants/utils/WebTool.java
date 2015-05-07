package merchants.utils;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import merchants.entity.PageIndex;
import merchants.entity.canvassGroup.HRPerson;



public class WebTool {

	public static PageIndex getPageIndex(int currentpage,int totalpage){
		PageIndex pageIndex = new PageIndex();
		if(currentpage-4>=1){
			pageIndex.setFirstindex(currentpage-4);
			if(currentpage+5>totalpage){
				pageIndex.setLastindex(totalpage);
			}else{
				pageIndex.setLastindex(currentpage+5);
			}
		}else{
			pageIndex.setFirstindex(1);
			pageIndex.setLastindex(10>totalpage?totalpage:10);
		}
		return pageIndex;
	}
	/**
	 * 添加cookie
	 * @param response 连接浏览器的对象
	 * @param cookiename
	 * @param value cookie的值
	 * @param maxAge cookie的有效期,以秒为单位
	 */
	public static void addCookie(HttpServletResponse response,String cookiename,String value,int maxAge){
		Cookie cookie = new Cookie(cookiename, value);
		cookie.setPath("/");//表示对根目录下的所有路径都能访问该cookie
		if(maxAge > 0){
			cookie.setMaxAge(maxAge);
		}
		response.addCookie(cookie);
	}
	/**
	 * 通过cookie的名字获取cookie的值
	 * @param request
	 * @param cookieName
	 * @return
	 */
	public static String getCookieByName(HttpServletRequest request,String cookieName){
		Map<String,Cookie> cookiemap = readCookieMap(request);
		if(cookiemap.containsKey(cookieName)){
			return cookiemap.get(cookieName).getValue();
		}else{
			return null;
		}
	}
	//获取所有的cookie，并用cookie的名称作为键值
	protected static Map<String,Cookie> readCookieMap(HttpServletRequest request){
		Map<String,Cookie> cookiemap = new HashMap<String, Cookie>();
		Cookie[] cookies = request.getCookies();
		if(cookies != null){
			for(Cookie cookie : cookies){
				cookiemap.put(cookie.getName(), cookie);
			}
		}
		return cookiemap;
	}
	
	//解决Get方法产生的中文乱码问题将ISO-8859-1转换成UTF-8码
	public static String changeToUTF8(String  oriString){
		 String desString="";
		try {
			desString = new String(
					 oriString.getBytes("ISO-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			System.out.println("转码失败，将返回空串"); 
			e.printStackTrace();
		}
		return desString;
	}
	public static HRPerson getEmployee(HttpServletRequest request) {
		return (HRPerson) request.getSession().getAttribute("user");
	}

}
