package test.connectPoolDemo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.concurrent.TimeUnit;

/**
 * @author xuzhihua
 * @date 2019/2/23 11:01 AM
 *
 * java.sql.Connection是一个接口，最终的实现是由数据库驱动提供方来实现的，
 * 本类模拟数据库驱动，通过动态代理构造一个Connection，
 * 该Connection的代理实现仅仅是在commit()方法调用休眠100毫秒
 */
public class ConnectionDriver {
    static class ConnectionHandler implements InvocationHandler {
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if ("commit".equals(method.getName())){
                TimeUnit.MILLISECONDS.sleep(100);
            }
            return null;
        }
    }

    // 创建一个Connection的代理，在commit时休眠100毫秒
    public static final Connection createConnection() {
        return (Connection) Proxy.newProxyInstance(ConnectionDriver.class.getClassLoader(), new Class[]{Connection.class}, new ConnectionHandler());
    }
}
