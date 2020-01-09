package com.aaa.lee.springcloud.test;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2020/1/3 11:43
 * @Description
 **/
public class Random {

    /**
     * Randomly choose from all living servers
     */
    /*public Server choose(ILoadBalancer lb, Object key) {
        // 1.首先先判断负载均衡的策略，如果策略为了就意味没有算法，直接return null(没有找到provider)
        if (lb == null) {
            return null;
        }
        // 2.创建Server对象，没有初始化(就是最终返回给consumer的provider)
        Server server = null;

        // 3.直接进入死循环(袁世凯选择大总统的策略--->选不出来誓不罢休)
        while (server == null) {
            // 4.多线程判断(负载均衡一定是多线程(并发))
            // interrupted()--->表示线程被中断(true:被中断，false:正常运行)
            // 当打开京东页面--->线程已经到达了服务端--->突然客户端关闭浏览器--->线程会出于游离--->根据自己的配置等待10s(等待客户端)
            // 10s之后没有等到客户端--->这个时候线程就会默认处于中断状态--->这个线程就会永久的停留到了服务器端，会占用服务器资源--->如果没有人清理则java的GC会自动清理(一般不会这样做，容易造成服务器的宕机)
            // Thread.interrupted()判断线程是否处于中断，如果中断则直接return，不会占用服务器的资源
            if (Thread.interrupted()) {
                return null;
            }
            *//**
             * 5.获取高可用的--->高可用节点不能代表全部(1,2,3,4)
             * 6.全部(1,2,3,4,5,6)
             *      ---> 就算服务器宕机了也有重启的一天
             *
             *//*
            // 5.获取高可用的provider节点(provider满载是20000--->1万9)
            List<Server> upList = lb.getReachableServers();
            // 6.获取所有的节点(因为需要从这些所有节点中获取随机数)
            List<Server> allList = lb.getAllServers();
            // 7.获取所有provider节点的个数--->需要开始随机数了
            int serverCount = allList.size();
            *//**
             * List selectAll();
             * if(list.size() > 0)
             *//*
            // 8.判断是否有provider，如果没有则直接return null
            if (serverCount == 0) {
                *//*
                 * No servers. End regardless of pass, because subsequent passes
                 * only get more restrictive.
                 *//*
                return null;
            }

            int index = chooseRandomInt(serverCount);
            // 9.随机出的数字为3(upList:1(0),2(1),3(2),4(3))
            server = upList.get(index);
            // 10.再次判断一次(double check双重检测)
            if (server == null) {
                *//*
                 * The only time this should happen is if the server list were
                 * somehow trimmed. This is a transient condition. Retry after
                 * yielding.
                 *//*
                *//**
                 * yield:表示线程谦让，线程一旦被启动，则绝对不会出现谦让
                 *      谦让的目的就是解决了死锁的问题
                 *      线程发现server是空的，表示自己就没有事了，睡觉了(sleep(抱着锁睡觉))--->其他的线程就会等待--->服务积压--->服务器宕机
                 *      绝对不允许这种情况的发现，所以线程之间必须要谦让
                 *      yield():是一旦线程发现自己无所事事的时候，就会处于就绪状态(等待另外一个请求过来，进行接客)
                 *//*
                Thread.yield();
                continue;// 跳出本次循环进入下次循环
            }

            // 11.再次判断provider是否存在
            if (server.isAlive()) {
                return (server);
            }

            // Shouldn't actually happen.. but must be transient or a bug.
            server = null;
            // 12.再次进行线程谦让，让线程处于就绪状态
            Thread.yield();
        }

        return server;

    }

    *//**
     * nextInt()--->就是做随机数
     * serverCount--->provider的个数---->从所有的provider中获取出(随机)一个provider
     * 为什么不用Random？
     *      因为random是线程安全的，因为random是可以被预测的
     *      HashMap和HashTable的区别--->线程安全和线程不安全
     *      线程安全一定意味性能降低
     *      必须要考虑安全性的时候(对数据库做写操作的一定是线程安全的)(支付，库存，注册....)
     *      ThreadLocalRandom是线程不安全的，而且效率会非常高，并且是不可预测(thread.getId())
     *      目前时间微秒数:3178641876371247199--->同时进来了两个请求--->nextInt(8)
     *      在java的API上--->如果是线程的随机数一定要使用ThreadLocalRandom
     * @param serverCount
     * @return
     *//*
    protected int chooseRandomInt(int serverCount) {
        *//*java.util.Random random = new java.util.Random();
        random.nextInt(serverCount);*//*
        return ThreadLocalRandom.current().nextInt(serverCount);
    }

    @Override
    public Server choose(Object key) {
        return choose(getLoadBalancer(), key);
    }*/
}
