package egovframework.com.cmmn.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


public class CommIntercepter extends HandlerInterceptorAdapter{
		
	private final static Logger LOGGER = LoggerFactory.getLogger(CommIntercepter.class);
	

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		LOGGER.debug("============전처리 인터셉터 입니다.===========");
		
		String id = request.getParameter("id");
		
		boolean test = false;
		
		if(StringUtils.isNotBlank(id)) {
			LOGGER.debug("로그인 성공");
		}else {
			LOGGER.debug("로그인 실패");
			ModelAndView modelAndView = new ModelAndView("redirect:/login.do?auth_error=1");
			throw new ModelAndViewDefiningException(modelAndView);
		}
		
		
		return true;
		//return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		LOGGER.debug("============후처리 인터셉터 입니다.===========");
		
		modelAndView.addObject("test", "고생하셔습니다.");
		
		//return super.postHandle(request, response, handler, modelAndView);

	}
}
