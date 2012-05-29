import com.microsoft.windowsazure.services.core.storage.*;
import com.microsoft.windowsazure.services.queue.client.*;

public class Receiver {

  public static void main(String[] args) throws Exception {
    CloudStorageAccount storageAccount = CloudStorageAccount.parse("UseDevelopmentStorage=true");
    CloudQueueClient queueClient = storageAccount.createCloudQueueClient();
    CloudQueue queue = queueClient.getQueueReference("ariofrio-language-interop-test-queue");
    queue.createIfNotExist();

    CloudQueueMessage message = queue.retrieveMessage();
    if(message == null) {
      System.err.println("No message in the queue, please try again.");
    } else {
      System.out.println(message.getMessageContentAsString());
      System.err.println("Deleting message...");
      queue.deleteMessage(message);
    }
  }
}
