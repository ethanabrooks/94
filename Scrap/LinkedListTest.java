import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Ethan on 2/12/16.
 */
@RunWith(Parameterized.class)
public class LinkedListTest {

    @Parameterized.Parameters(name = "value: {0}, list: {1}")
    public static Collection<Object []> data() {
        return Arrays.asList(new Object[][] {
                {2, Collections.emptyList()},
                {0, Collections.singletonList(1)},
                {1, Collections.singletonList(1)},
                {3, Collections.singletonList(1)},
                {3, Arrays.asList(1, 2, 3, 4, 5, 6)},
                {6, Arrays.asList(1, 2, 3, 4, 5, 6)},
                {7, Arrays.asList(1, 2, 3, 4, 5, 6)},
                {'a', Collections.singletonList('a')},
                {true, Arrays.asList(false, true)},
        });
    }


    private Object value;
    private ArrayList<Object> list;
    private MyLinkedList<Object> linkedList;


    public LinkedListTest(Object value, List<Object> list) {
        this.value = value;
        this.list = new ArrayList<>(list);
        this.linkedList = new MyLinkedList<>(list);
    }


    @Test
    public void testAddFirst() throws Exception {
        linkedList.add(value);
        list.add(0, value);
        assertThat(linkedList.toList(), is(list));
    }

    @Test
    public void testAddLast() throws Exception {
        linkedList.addLast(new Node<>(value));
        list.add(value);
        assertThat(linkedList.toList(), is(list));
    }

    private static String toString(List<Object> list) {
        if (list.isEmpty()) {
            return "";
        }
        String string = "Printing out the contents of the linked list\n"
                + list.get(0);
        for (Object item: list.subList(1, list.size())) {
            string += " ---> " + item.toString();
        }
        return string;
    }


    @Test
    public void testDisplay() throws Exception {
        ByteArrayOutputStream display = new ByteArrayOutputStream();
        System.setOut(new PrintStream(display));
        linkedList.display();
        assertThat(display.toString(), is(toString(list) + "\n"));
        System.setOut(null);
    }
}