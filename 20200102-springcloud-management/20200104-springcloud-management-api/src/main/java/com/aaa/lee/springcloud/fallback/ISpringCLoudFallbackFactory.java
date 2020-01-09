package com.aaa.lee.springcloud.fallback;

import com.aaa.lee.springcloud.model.Book;
import com.aaa.lee.springcloud.service.ISpringCloudService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2020/1/7 11:52
 * @Description
 **/
@Component
public class ISpringCLoudFallbackFactory implements FallbackFactory<ISpringCloudService> {
    @Override
    public ISpringCloudService create(Throwable throwable) {
        ISpringCloudService springCloudService = new ISpringCloudService() {
            @Override
            public List<Book> selectAllBooks() {
                System.out.println("我是测试熔断的方法，我被访问了！！");
                return null;
            }
        };
        return springCloudService;
    }
}
