package lab38.SQS;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageRequest;

import java.util.Timer;
import java.util.TimerTask;

public class sqsSend extends TimerTask {
    int i = 0;

    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.schedule(new sqsSend(), 0, 2000);

    }

        public void run() {
            AmazonSQS amazonSQS = AmazonSQSClientBuilder.defaultClient();

            SendMessageRequest sendMessageRequest = new SendMessageRequest()
                    .withMessageBody("This is message number " + i++)
                    .withQueueUrl("https://sqs.us-west-2.amazonaws.com/423800957741/QueueA");

            amazonSQS.sendMessage(sendMessageRequest);
            System.out.println("Message " + i + " sent");
        }
}
