package com.bean.breakfast.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.breakfast.constants.IConstants;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
* @描述：系统拦截器，拦截未登录直接请求url等的非法访问
*/
public class SystemInterceptor extends HandlerInterceptorAdapter{
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 后台session控制
		String[] noFilters = new String[] {
				"/bfmanage/.do",
                "/bfmanage/basic/login.do",
                "/bfmanage/mobile/.do",
                "/bfmanage/mobile/getValidateCode.do",
                "/bfmanage/mobile/getValidateCodeImage.do",
                "/bfmanage/mobile/regist.do",
                "/bfmanage/mobile/getUserInfo.do",
                "/bfmanage/mobile/getMyLatestOrder.do",
                "/bfmanage/mobile/getMyOrder.do",
                "/bfmanage/mobile/saveOrder.do",
                "/bfmanage/mobile/getFood.do",
                "/bfmanage/mobile/toLogin.do",
                "/bfmanage/mobile/toBuyNow.do",
                "/bfmanage/mobile/toMyOrders.do",
                "/bfmanage/mobile/foodDetail.do",
                "/bfmanage/mobile/foodList.do",
				"/bfmanage/mobile/updateCourierPostion.do",
				"/bfmanage/mobile/getAllNewOrders.do",

        };
		String uri = request.getRequestURI();
		boolean beFilter = true;
		for (String s : noFilters) {
			if(uri.endsWith(".do")){
				if (uri.equals(s)) {
					beFilter = false;
					break;
				}
			}else{
				if ((uri+".do").equals(s)) {
					beFilter = false;
					break;
				}
			}
			if(uri.endsWith(".jpg")||uri.endsWith(".JPEG")||uri.endsWith(".png")||uri.endsWith(".PNG")
					||uri.endsWith(".bmp")||uri.endsWith(".BMP")||uri.endsWith(".gif")||uri.endsWith(".GIF")){
				beFilter = false;
			}

		}
		if (beFilter) {
			Object obj = request.getSession().getAttribute(IConstants.LOGINED);
			if (null == obj) {
				// 未登录
				PrintWriter out = response.getWriter();
				StringBuilder builder = new StringBuilder();
				builder.append("<script type=\"text/javascript\" charset=\"UTF-8\">");
				builder.append("alert(\"长时间未操作，请重新登录！\");");
				builder.append("window.location.href=\"");
				builder.append("/bfmanage/");
				builder.append("\";</script>");
				out.print(builder.toString());
				out.close();
				return false;
			}
		}
		return super.preHandle(request, response, handler);
	}
}
