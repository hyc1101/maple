package com.maple.cat.annotation;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Transaction;

@Aspect
public class CatAopService {

	@Around("@annotation(CatAnnotation)")
	public Object aroundMethod(ProceedingJoinPoint pjp) {
		MethodSignature joinPointObject = (MethodSignature) pjp.getSignature();
		Method method = joinPointObject.getMethod();

		Transaction t = Cat.newTransaction("method", method.getName());

		try {
			// 放行，不放行方法调用会处于阻塞状态
			Object obj = pjp.proceed();

			t.setSuccessStatus();
			return obj;
		} catch (Throwable e) {
			t.setStatus(e);
			Cat.logError(e);
			throw new RuntimeException("Exception thrown by CAT aop", e);
		} finally {
			t.complete();
		}
	}

}