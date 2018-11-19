import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.nc.entity.User;

public class SpringBeanTest {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		User user = (User) ctx.getBean("carRef");
		System.out.println(user);
	}
}
