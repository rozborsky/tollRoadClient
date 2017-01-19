package ua.rozborsky.tollRoadClient;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.rozborsky.tollRoadClient.interfaces.View;


/**
 * Created by roman on 17.01.2017.
 */
public class Main {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationConfig.xml");

        View view = (View) context.getBean("window");
        view.create();
    }
}
