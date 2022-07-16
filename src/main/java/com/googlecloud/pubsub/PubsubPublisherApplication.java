package com.googlecloud.pubsub;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PubsubPublisherApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(PubsubPublisherApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		new PublisherUtil().main(args);
		
	}

}
