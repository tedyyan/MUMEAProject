package pl.piomin.services.employee;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Sender implements CommandLineRunner {
	@Autowired
	private RabbitTemplate template;

	@Override
	public void run(String... args) throws Exception {
		while (true) {
			String queue = "default";
			String msg = "Hello World!";
			template.convertAndSend(queue, msg);
			System.out.println("Sent: " + msg + " to: " + queue);
			Thread.sleep(5000);
		}
	}
}
