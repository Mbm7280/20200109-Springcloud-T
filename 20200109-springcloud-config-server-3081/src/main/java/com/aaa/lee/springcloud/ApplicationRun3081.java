package com.aaa.lee.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2020/1/9 10:36
 * @Description
 *      是否需要配置eureka？
 *          不需要，因为config的服务器端只是一个管理配置的文件的项目，不需要在eureka中注册
 **/
@SpringBootApplication
@EnableConfigServer
public class ApplicationRun3081 {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationRun3081.class, args);
    }

}
