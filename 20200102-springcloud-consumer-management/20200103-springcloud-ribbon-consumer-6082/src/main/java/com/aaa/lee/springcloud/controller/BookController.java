package com.aaa.lee.springcloud.controller;

import com.aaa.lee.springcloud.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2020/1/3 10:40
 * @Description
 *      因为并发量比较大，并且不能让两次随机都随到一台服务器上，这个时候ribbon所默认提供的8种算法就无法满足，需要自己去写
 *      因为并发量比较大，在5分钟之内如果两次随机出的都是同一个provider的话，就重新随机
 **/
@RestController
public class BookController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private LoadBalancerClient loadBalancerClient; // 是为了获取所有provider的信息

    @GetMapping("/all")
    public List<Book> selectAllBooks() {
        // 在这不是provider的application.properties中所配置的spring.application.name的值
        // 在eureka页面上所显示Application的值(必须要大写，如果是小写，匹配不到，推荐使用复制粘贴)
        return restTemplate.getForObject("http://BOOK-PROVIDER/all", List.class);
    }

    /**
     * @author Seven Lee
     * @description
     *      使用ribbon作为负载均衡脱离eureka实现
     *      当使用ribbon脱离了eureka实现负载均衡的时候，一定需要的是正式环境，也就是说如果要进行测试就不能使用localhost(正式域名)
     *      并且不能使用内网映射，必须要使用外网，否则就会报错:No instances available for localhost
     *      (花生壳--->内网穿透)
     * @param []
     * @date 2020/1/4
     * @return java.util.List<com.aaa.lee.springcloud.model.Book>
     * @throws
    **/
    @GetMapping("/allLB")
    public List<Book> selectAllBooksNoEureka() {
        // 1.如果需要实现负载均衡调用，就必须要知道provider的ip地址和端口号(获取service的实例对象)
        // serviceId:就是spring.application.name的值
        ServiceInstance choose = loadBalancerClient.choose("book-provider");
        // 2.获取host信息
        String host = choose.getHost();
        // 3.获取端口号
        int port = choose.getPort();
        System.out.println(port);
        return restTemplate.getForObject("http://"+host+":"+port+"/all", List.class);
    }

}
