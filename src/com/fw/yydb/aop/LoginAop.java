package com.fw.yydb.aop;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import com.fw.yydb.annotation.AnonymousAccess;

@Aspect
@Component
public class LoginAop {

	@Around("execution(* com.fw.yydb.web.*.*(..))")
	public Object isLogin(ProceedingJoinPoint joinPoint) throws Throwable {
		if (isAnonymous(joinPoint)) {
			return joinPoint.proceed();
		}
		Map<String, Object> respMap = new HashMap<String, Object>();
		HttpServletRequest request = (HttpServletRequest) joinPoint.getArgs()[1];
		//		String key = RedisHelper.getTokenKey(request.getSession().getId());
		//		RedisObject obj = RedisUtility.fetch(key);
		//		if (obj.getValue() == null) {
		//			respMap.put(ParamConstant.RESULT_CODE, ResultCodeConstant.TOKEN_IS_NULL);
		//			respMap.put(ParamConstant.RESULT_DESC, ResultCodeConstant.TOKEN_IS_ERR_MSG);
		//			return respMap;
		//		}
		return joinPoint.proceed();
	}

	/**
	 * 是否匿名访问请求
	 * @param pjp
	 * @return
	 */
	private boolean isAnonymous(ProceedingJoinPoint pjp) {
		MethodSignature ms = (MethodSignature) pjp.getSignature();
		Method method = ms.getMethod();
		// 匿名访问时，不需要获取openid
		if (AnnotationUtils.findAnnotation(method, AnonymousAccess.class) != null) {
			//			LOG.info("anonymous access.");
			return true;
		}

		return false;
	}
}
