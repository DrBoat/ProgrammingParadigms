package queue;
public interface Queue {
    //post: R = size
    public int size();

    //post: R = (size == 0)
    public boolean isEmpty();

    //pre: element != null
    //post: size' = size + 1 && elements[size]' = element && elements[i]' = elements[i], 0 <= i < size
    public void enqueue(Object element);

    //pre: size > 0
    //post: R = elements[head]
    public Object element();

    //pre: size > 0
    //post: size' = size - 1 && R = elements[0] && elements[i]' = elements[i + 1], 0 <= i < size'
    public Object dequeue(); // удалить и вернуть элемент из начала (head) очереди

    //post: head = 0 && tail = 0 && size = 0;
    public void clear();

    //post: (head < tail && R = (elements[head, tail - 1])) || (head > tail && R = elements[head, n - 1] + elements[0, tail - 1])
    public Object[] toArray();
}