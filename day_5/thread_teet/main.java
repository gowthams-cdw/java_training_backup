class SharedBuffer {
  private String message;
  private boolean empty = true;

  public void producer(String message) {
    if (empty) {
      this.message = message;
      System.out.println("producer" + this.message);
      this.empty = false;
    } else {
      try {
        wait(200);
      } catch (InterruptedException e) {
        System.out.println("Thread Interrupted.");
      }
    }
  }

  public void consumer() {
    if (!empty) {
      this.empty = true;
      String emptyMessage = this.message;
      System.out.println("message " + emptyMessage);
    } else {
      try {
        wait(200);
      } catch (InterruptedException e) {
        System.out.println("Thread Interrupted.");
      }
    }
  }
}

class ProducerConsumer {
  public static void main(String[] args) {

    SharedBuffer buffer = new SharedBuffer();
    // producer task
    Runnable producer =
        () -> {
          String[] fruits = {"apple", "orange", "strawberry"};
          for (String fruit : fruits) {
            buffer.producer(fruit);
          }
        };

    // consumer task
    Runnable consumer =
        () -> {
          buffer.consumer();
        };

    Thread producerThread = new Thread(producer);
    Thread consumerThread = new Thread(consumer);

    producerThread.start();
    consumerThread.start();
  }
}
