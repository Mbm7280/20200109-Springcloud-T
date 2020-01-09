package com.aaa.lee.springcloud.controller;

import com.aaa.lee.springcloud.model.Book;
import com.aaa.lee.springcloud.service.BookService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2020/1/7 10:56
 * @Description
 **/
@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    /**
     * @author Seven Lee
     * @description
     *      查询所有的图书信息
     *      !!! 在开发阶段，所有的熔断必须关闭，如果不关闭则不会抛异常(最后一个项目必须使用springcloud，而且必须要有熔断(在开发阶段不允许添加熔断，也就是说开发完毕之后再开启熔断机制)) !!!
     *          这种模式是由弊端
     *              一个controller会有很多个方法--->每一个方法都必须有一个备用方法
     *              ---->controller就会非常大(非常臃肿)--->封装
     *              feign+hystrix实现真正的熔断器
     *              UserController.java--->.class
     *                  selectUserById()
     *              BookController
     *                  selectAllBooks()--->.class
     *              OrderController
     *              --->是否可以把所有controller的后备方法全部封装到一个类中！
     *
     * @param []
     * @date 2020/1/7
     * @return java.util.List<com.aaa.lee.springcloud.model.Book>
     * @throws 
    **/
    @GetMapping("/all")
    // @HystrixCommand(fallbackMethod = "selectAllFallBack")
    public List<Book> selectAllBooks() throws Exception {
        List<Book> bookList = bookService.selectAllBooks();
        if(bookList.size() > 0) {
            // 说明数据库中有数据，有数据则抛异常
            throw new Exception("故意抛出异常！");
        }
        return bookList;
    }

    /*public List<Book> selectAllFallBack() {
        List<Book> bookList = new ArrayList<Book>();
        Book book = new Book();
        book.setId(20000L);
        book.setBookName("测试熔断是否启动");
        book.setBookPrice(11111111.2);
        bookList.add(book);
        return bookList;
    }*/

}
