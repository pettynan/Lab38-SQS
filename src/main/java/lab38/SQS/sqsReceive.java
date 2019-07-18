package lab38.SQS;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.Message;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class sqsReceive extends TimerTask {

    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.schedule(new sqsReceive(), 0, 5000);

    }

    public void run() {
        AmazonSQS amazonSQS = AmazonSQSClientBuilder.defaultClient();

//        ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest("https://sqs.us-west-2.amazonaws.com/423800957741/QueueA");

        List<Message> messageResponses = amazonSQS.receiveMessage("https://sqs.us-west-2.amazonaws.com/423800957741/QueueA")
               .getMessages();


        for (Message message : messageResponses) {
            String messageString = message.getBody();
            System.out.println(messageString);

            DeleteMessageRequest deleteMessageRequest = new DeleteMessageRequest();
            deleteMessageRequest.withQueueUrl("https://sqs.us-west-2.amazonaws.com/423800957741/QueueA")
                    .withReceiptHandle(message.getReceiptHandle());

            amazonSQS.deleteMessage(deleteMessageRequest);
        }

    }


}
