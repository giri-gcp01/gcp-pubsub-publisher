package com.googlecloud.pubsub;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.google.api.core.ApiFuture;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.PubsubMessage;
import com.google.pubsub.v1.TopicName;

public class PublisherUtil {

	public void main(String... args) throws Exception {
		// TODO(developer): Replace these variables before running the sample.
		String projectId = "cloud-foundry-354707";
		String topicId = "pubsubtest";

		publisherExample(projectId, topicId);
	}

	public static void publisherExample(String projectId, String topicId)
			throws IOException, ExecutionException, InterruptedException {
		TopicName topicName = TopicName.of(projectId, topicId);

		Publisher publisher = null;
		try {
			// Create a publisher instance with default settings bound to the topic
			publisher = Publisher.newBuilder(topicName).build();

			String message = "Hello World!";
			
			for (int i = 0; i < 100; i++) {
				ByteString data = ByteString.copyFromUtf8("Message:" +i + " " +message);
				PubsubMessage pubsubMessage = PubsubMessage.newBuilder().setData(data)
						.setMessageId(String.valueOf(i)).build();

				// Once published, returns a server-assigned message id (unique within the
				// topic)
				ApiFuture<String> messageIdFuture = publisher.publish(pubsubMessage);
				String messageId = messageIdFuture.get();
				System.out.println("Published message: " + data.toString());
			}
		} finally {
			if (publisher != null) {
				// When finished with the publisher, shutdown to free up resources.
				publisher.shutdown();
				publisher.awaitTermination(1, TimeUnit.MINUTES);
			}
		}
	}

}
