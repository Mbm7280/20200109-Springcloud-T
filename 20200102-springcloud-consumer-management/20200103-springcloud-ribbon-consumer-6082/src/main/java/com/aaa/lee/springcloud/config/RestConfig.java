package com.aaa.lee.springcloud.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2020/1/3 10:11
 * @Description
 *      当使用ribbon作为负载均衡的时候，默认算法就是轮询
 *      ribbon中一共提供多少种默认的算法？8种
 *      比较常用的算法：
 *          BestAvailableRule:
 *              选择一个最小的并发服务
 *              8081-->100个
 *              8082-->300个
 *              8083-->200个
 *              这个时候当consumer调用provider的时候，会直接选择8081
 *           RetryRule:
 *              重新连接
 *              8081
 *              8082
 *              8083
 *              客户端发送请求到达consumer--->调用provider(3台)--->8081正常
 *              第二次请求8082(宕机)--->直接跳过8082，请求8083(宕机)--->8081
 *              可以手动更改(默认值为0--->1,2,3...)--->8082宕机--->再次尝试去连接一次8082
 *           RoundRibbonRule:
 *              轮询(默认的，没什么说的，不懂就自杀！)
 *           RandomRule:
 *              随机(随便选)
 *              Random random = new Random();
 *              random.nextInt(99); 0-99进行随机
 *              获取的是当前系统时间的微秒
 *      dubbo所默认提供负载均衡算法有4种
 *
 *      UserServiceImpl
 *      IUserService
 **/
@Configuration
public class RestConfig {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /*@Bean
    public IRule mineRule() {
        return new RandomRule();
    }*/

}
