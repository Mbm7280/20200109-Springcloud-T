package com.aaa.lee.springcloud;

import com.aaa.lee.rule.MineRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2020/1/3 10:09
 * @Description
 *      @Loadbalaced:负载均衡的注解
 **/
@SpringBootApplication
@EnableDiscoveryClient
// @RibbonClient(name = "BOOK-PROVIDER", configuration = MineRule.class)
public class ApplicationRun6082 {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationRun6082.class, args);
    }

}
