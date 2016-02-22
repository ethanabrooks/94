import java.util.ArrayList;
import java.util.EmptyStackException;

/**
 * Created by Ethan on 2/19/16.
 */
public class Prioqueue<T> {
    ArrayList<Prionode<T>> list = new ArrayList<Prionode<T>>();
    private int size;

    public Prioqueue() {
    }

    public T dequeue() throws Exception {
        size--;
        int minIdx = peek_index();
        T retVal = list.get(minIdx).value;
        list.remove(minIdx);
        return retVal;
    }

    public void enqueue(T value, int priority) {
        list.add(new Prionode<T>(value, priority));
    }

    private int peek_index() throws Exception {
        if (isempty()) {
            throw new EmptyStackException();
        }
        int min = 0;
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).priority < list.get(min).priority) {
                min = i;
            }
        }
        return min;
    }

    public boolean isempty() {
        return size==0;
    }
}
