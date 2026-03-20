class SharedBuffer {
  private String message;
  private boolean empty = true;

  public void producer(String message) {

    if (empty) {
      this.message = message;
      System.out.println("producer" + this.message);
      this.empty = false;
      notifyAll();
    } else {
      wait();
    }
  }

  public void consumer() throws InterruptedException {
    if (!empty) {
      this.empty = true;
      String emptyMessage = this.message;
      notifyAll();
      System.out.println("message " + message);
    } else {
      wait();
    }
  }
}

public class ProducerConsumer {
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
          for (int i = 0; i < 3; i++) {
            System.out.println("consumer" + buffer.consumer());
          }
        };

    Thread producerThread = new Thread(producer);
    Thread consumerThread = new Thread(consumer);

    producerThread.start();
    consumerThread.start();
  }
}

// Threads
public class DeadlockDemo {
  public static void main(String[] args) {
    // These are our two resources (the Pen and the Paper)
    Object lock1 = new Object();
    Object lock2 = new Object();

    // Thread 1: Alice wants Pen then Paper
    Thread alice =
        new Thread(
            () -> {
              synchronized (lock1) {
                System.out.println("Alice: Holding Pen...");

                System.out.println("Alice: Waiting for Paper...");
                synchronized (lock2) {
                  System.out.println("Alice: Got Pen and Paper!");
                }
              }
            });

    // Thread 2: Bob wants Paper then Pen
    Thread bob =
        new Thread(
            () -> {
              synchronized (lock2) {
                System.out.println("Bob: Holding Paper...");

                System.out.println("Bob: Waiting for Pen...");
                synchronized (lock1) {
                  System.out.println("Bob: Got Pen and Paper!");
                }
              }
            });

    alice.start();
    bob.start();
  }
}
