package springbook2.ch1;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.support.GenericApplicationContext;

public class BeanDefinitionUtils {
	
	public static void printBeanDefinitions(GenericApplicationContext gac) {
		List<List<String>> roleBeanInfos = new ArrayList<List<String>>();
		roleBeanInfos.add(new ArrayList<String>());
		roleBeanInfos.add(new ArrayList<String>());
		roleBeanInfos.add(new ArrayList<String>());
		
		for (String name : gac.getBeanDefinitionNames()) {
			int role = gac.getBeanDefinition(name).getRole();
			List<String> beanInfos = roleBeanInfos.get(role);
			beanInfos.add(role + "\t" + name + "\t" +
					gac.getBean(name).getClass().getName());
		}
		
		for(List<String> beanInfos : roleBeanInfos) {
			for(String beanInfo : beanInfos) {
				System.out.println(beanInfo);
			}
		}
	}

}
