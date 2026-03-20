import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// Threads
class AliceBob {
  public static void main(String[] args) {
    Lock paperLock = new ReentrantLock();
    Lock penLock = new ReentrantLock();

    Thread alice = new Thread(() -> acquireResources(paperLock, penLock, "alice"));
    Thread bob = new Thread(() -> acquireResources(paperLock, penLock,"bob"));

    alice.start();
    bob.start();
  }

  public static void acquireResources(Lock firstLock, Lock secondLock, String name) {
    while (true) {
      boolean getFirst = firstLock.tryLock();
      boolean getSecond = secondLock.tryLock();

      try {
        if (getFirst && getSecond) {
          System.out.println(name + " acquired both pen and paper");
          return;
        }
      } finally {
        if (getFirst && getSecond) {
          firstLock.unlock();
          secondLock.unlock();
        } else if (getFirst) {
          firstLock.unlock();
        } else if (getSecond) {
          secondLock.unlock();
        }
      }
    }
  }
}
