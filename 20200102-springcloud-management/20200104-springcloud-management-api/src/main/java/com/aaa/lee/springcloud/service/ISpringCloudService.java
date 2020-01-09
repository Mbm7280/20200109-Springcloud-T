package com.aaa.lee.springcloud.service;

import com.aaa.lee.springcloud.fallback.ISpringCLoudFallbackFactory;
import com.aaa.lee.springcloud.model.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2020/1/4 10:56
 * @Description
 *      FeignClient就是consumer通往provide的桥梁，也就是说consumer调用api，api调用provider(通过这个FeignClient注解)
 *          feign中有负载均衡的功能，所以value不能写某一个provider的ip地址和端口号(localhost:8081,8082,8083)
 *          写eureka中Application的值
 *      注意事项:
 *          1.api和provider中的所有GetMapping必须要匹配(@RequestMapping)(请求方式必须一致)
 *          2.api和provider的返回值也必须一致
 *          3.api和provider的方法参数个数和类型包括参数名都必须一致
 *          !!!!4.因为使用的仍然 http协议传输，需要把实体类转换为二进制(implement Serializable)
 *              如果需要传参--->传递简单类型(8种基本类型，8种基本类型的包装类型,String...)--->@ReuqestParam
 *              api和provider都需要这个注解
 *              !!!!!@RequestParam注解一定可以出现多次
 *                        --->传递对象类型(实体类，Map...)--->@RequestBody
 *                        并且api和provider都必须用这个注解--->这个@RequestBody只能出现一次
 *                   Book和Book_Cat
 *                      一对多
 *                   不能使用","--->效率实在太低了
 *                   select * from book,book_cat:不要再出现
 *                   能用left join/right join就不要用inner join
 *                   union和union all--->能用union不要用union all
 *                   left join和right join规定一条sql最多只能出现两次
 *                   多用where
 *                   <select id="" paramemeterType="" resultType="BookVo">
 *                      select * from book b inner join book_cat bc on b.book_cat_id = bc.id
 *                   </select>
 *                pojo:代表了实体类
 *                povo:当所有的实体类都无法满足查询出来的结果的时候，需要封装一个vo类型
 *                   BookVo:
 *                      bookId
 *                      bookName
 *                      bookPricce
 *                      bookCatId
 *                      bookCatName
 *
 *                   BookVo extends Book{
 *                       bookCatId
 *                       bookCatName
 *                   }
 *
 *
 **/
@FeignClient(value = "BOOK-PROVIDER", fallbackFactory = ISpringCLoudFallbackFactory.class)
public interface ISpringCloudService {

    @GetMapping("/all")
    List<Book> selectAllBooks();

}
