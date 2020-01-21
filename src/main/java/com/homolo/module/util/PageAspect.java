package com.homolo.module.util;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Aspect
public class PageAspect {

    @Around("execution(* *..*Service.*Pager(..))")
    public Object invoke(ProceedingJoinPoint args) throws Throwable{
        Object[] params = args.getArgs();
        PageBean pageBean=null;
        for (Object param : params) {
            if(param instanceof PageBean){
                pageBean=(PageBean) param;
                break;
            }
        }

        if(null!=pageBean&&pageBean.isPagination())
            PageHelper.startPage(pageBean.getPage(),pageBean.getRows());

        Object result = args.proceed(params);

        if(null!=pageBean&&pageBean.isPagination()){
            List lst=(List)result;
            PageInfo pageInfo=new PageInfo(lst);
            pageBean.setTotal(pageInfo.getTotal()+"");
        }
        return result;
    }
}
