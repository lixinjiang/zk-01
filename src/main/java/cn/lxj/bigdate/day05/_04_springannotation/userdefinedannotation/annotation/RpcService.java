package cn.lxj.bigdate.day05._04_springannotation.userdefinedannotation.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE}) // 注解用在接口上
@Retention(RetentionPolicy.RUNTIME) // VM将在运行期也保留注释，因此可以通过反射机制读取注解的信息
@Component  // 由spring加入spring容器中并交给spring管理
public @interface RpcService {
    String value();
}
