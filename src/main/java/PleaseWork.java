import com.microsoft.windowsazure.services.queue.client.*;

public class BackgroundWorker {
  final CloudStorageAccount storageAccount;
  final CloudQueueClient queueClient;
  final CloudQueue taskQueue;

  final CloudBlobClient blobClient;
  final CloudBlobContainer taskContainer;
  final CloudBlobContainer textContainer;
  
  public static void main(String[] args) throws Exception {
    storageAccount = CloudStorageAccount.parse(getStorageConnectionString());
    queueClient = storageAccount.createCloudQueueClient();
    taskQueue = queueClient.getQueueReference(args[1]);
    taskQueue.createIfNotExist();

    CloudQueueMessage message = taskQueue.retrieveMessage();
    if(message == null) {
      System.err.prinln("No message in the queue, please try again.");
    } else {
      System.out.println(message.getMessageContentAsString());
      System.err.prinln("Deleting message...");
      taskQueue.deleteMessage(message);
    }
  }
}
