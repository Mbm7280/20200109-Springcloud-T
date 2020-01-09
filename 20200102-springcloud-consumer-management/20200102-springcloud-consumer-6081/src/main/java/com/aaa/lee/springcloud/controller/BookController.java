package com.aaa.lee.springcloud.controller;

import com.aaa.lee.springcloud.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2020/1/2 9:39
 * @Description
 *      是consumer的controller--->需要调用provider的controller(需要http协议进行传输数据)
 *          也就是说需要模拟http协议
 *      getForObject("",xxx)
 *      第一个参数:
 *          代表了provider的地址(http://localhost:8081/all)
 *      第二个参数:
 *          provider所返回的类型，也就是说是返回值类型
 **/
@RestController
public class BookController {

    private static final String PROVIDER_URL = "http://localhost:8081/all";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/all")
    public List<Book> selectAllBooks() {
        return restTemplate.getForObject(PROVIDER_URL, List.class);
    }

}
