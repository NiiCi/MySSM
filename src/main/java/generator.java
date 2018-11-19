

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
/*import org.springframework.context.support.ClassPathXmlApplicationContext;

import entity.Admin;*/

public class generator {
	public static void main(String[] args) {
	/*	ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		Admin admin=(Admin) ctx.getBean("admin");*/
		  try {
			List<String> warnings = new ArrayList<String>();
			boolean overwrite = true;
			File configFile = new File("mbg.xml");
			ConfigurationParser cp = new ConfigurationParser(warnings);
			Configuration config = cp.parseConfiguration(configFile);
			DefaultShellCallback callback = new DefaultShellCallback(overwrite);
			MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
			myBatisGenerator.generate(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
