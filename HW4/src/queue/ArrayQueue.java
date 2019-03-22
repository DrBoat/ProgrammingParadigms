package queue;

//INV:
// ((size == 0 && start == end) || (size > 0 && start != end)) && size < elements.length
// elements from start to end in the case of orded
public class ArrayQueue extends AbstractQueue {
    private int head, tail;
    private Object elements[];

    public ArrayQueue() {
        elements = new Object[10];
        tail = head = size = 0;
    }

    //Pre:
    //Post:
    //      ((forall i = 0...elements.length - 1 : elements[i] == elements'[i]) ||
    //      (forall i = start, j = elements'.length - 1; i != end; i = getNextPos(i), j-- : elements'[j] == elements[i])) &&
    //      elements'.length == elements.length * 2 &&
    //      (end' == end || end' == start' - size) &&
    //      (start' == start || start == elements'.length - 1) && size' == size
    private void EnsureCapacity(int capacity) {
        if (size < capacity) {
            return;
        }
        Object buffer[] = new Object[elements.length * 2];
        System.arraycopy(elements, head, buffer, head + elements.length, elements.length - head);
        System.arraycopy(elements, 0, buffer, 0, tail);
        head += elements.length;
        elements = buffer;
    }

    //Pre: element != null
    //Post:
    //      ((forall i = 0...elements.length - 1 : elements[i] == elements'[i]) ||
    //      (forall i = start, j = elements'.length - 1; i != end; i = getNextPos(i), j-- : elements'[j] == elements[i])) &&
    //      elements'.length == elements.length * 2 &&
    //      (end' == getNextPos(end) || end' == getNextPos(start' - size)) &&
    //      (start' == start || start == elements'.length - 1) && size' == size + 1
    //      elements[end] == element
    public void enqueue1(Object element) {
        EnsureCapacity(elements.length);
        elements[tail] = element;
        tail = (tail + 1) % elements.length;
    }

    //Pre: element != null
    //Post:
    //      ((forall i = 0...elements.length - 1 : elements[i] == elements'[i]) ||
    //      (forall i = start, j = elements'.length - 1; i != end; i = getNextPos(i), j-- : elements'[j] == elements[i])) &&
    //      elements'.length == elements.length * 2 &&
    //      (end' == getNextPos(end) || end' == getNextPos(start' - size)) &&
    //      (start' == start || start == elements'.length - 1) && size' == size + 1
    //      (head++: head < end)
    public void dequeue1() {
        head = (head + 1) % elements.length;
    }

    //Pre: size > 0
    //Post: R = elements[start] && forall i = 0...elements.length - 1 : elements[i] == elements'[i] &&
    //      start == start' && end == end' && size == size'
    public Object element1() {
        return elements[head];
    }

    //Pre:
    //Post:
    //      forall i = 0...elements.length - 1 : elements[i] == elements'[i] &&
    //      start' == elements.length - 1 && end == start' && size == 0
    public void clear1() {
        head = tail = 0;
    }
}


