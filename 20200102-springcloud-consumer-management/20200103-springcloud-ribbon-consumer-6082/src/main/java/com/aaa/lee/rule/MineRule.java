package com.aaa.lee.rule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2020/1/4 9:23
 * @Description
 *      1.ribbon在实现自定义的负载均衡的时候一定不能放在主启动类下，并且也绝对不能和主启动平级(@ComponentScan)
 *      因为官方文档明确指出@ComponentScan绝对不允许和主启动同级/下级
 *      如果需要把这个类标识为配置类的时候:
 *          @Configuration,@SpringBootApplication
 *      @SpringBootApplication--->会默认去扫描该注解下所有注解(Controller,service,mapper,component...)
 *      @ComponentScan--->扫描@Component注解的一个专用注解
 *
 *      2.必须使用@Configuration注解标识
 *
 *      3.实现ribbon自定义负载均衡算法的时候，必须使用一个注解来进行标识
 *              @RibbonClient(name = "", configuration = ):name值就代表了eureka中Application的值(大写)
 *                  configuration:自定义算法配置类
 *
 **/
@Configuration
public class MineRule {

    @Bean
    public IRule mineRule() {
        return  new RandomRule();
    }

}
