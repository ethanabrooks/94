/**
 * Created by Ethan on 2/19/16.
 */
public class Prionode<T> {
    T value;
    int priority;

    public Prionode(T value, int priority) {
        this.value = value;
        this.priority = priority;
    }
}
