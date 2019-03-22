package queue;

import java.util.Arrays;

//INV:
// ((size == 0 && start == end) || (size > 0 && start != end)) && size < elements.length
// elements from start to end in the case of orded
public class ArrayQueue {
    private int size;
    private int start = 4;
    private int end = 4;
    private Object[] elements = new Object[5];

    //Pre: element != null
    //Post: 
    //      ((forall i = 0...elements.length - 1 : elements[i] == elements'[i]) || 
    //      (forall i = start, j = elements'.length - 1; i != end; i = getNextPos(i), j-- : elements'[j] == elements[i])) && 
    //      elements'.length == elements.length * 2 &&
    //      (end' == getNextPos(end) || end' == getNextPos(start' - size)) && 
    //      (start' == start || start == elements'.length - 1) && size' == size + 1
    //      elements[end] == element
    public void enqueue(Object element) {
        assert element != null;

        ensureCapacity(size + 1);
        elements[end] = element;
        end = getNextPos(end);
        size++;
    }

    //Pre: 
    //Post: 
    //      ((forall i = 0...elements.length - 1 : elements[i] == elements'[i]) || 
    //      (forall i = start, j = elements'.length - 1; i != end; i = getNextPos(i), j-- : elements'[j] == elements[i])) && 
    //      elements'.length == elements.length * 2 &&
    //      (end' == end || end' == start' - size) && 
    //      (start' == start || start == elements'.length - 1) && size' == size
    private void ensureCapacity(int capacity) {
        if (capacity < elements.length) {
            return;
        }
        int newCapacity = 2 * capacity;
        Object[] newElements = new Object[newCapacity];
        for (int i = start, j = newCapacity - 1; i != end; i = getNextPos(i), j--) {
            newElements[j] = elements[i];
        }
        elements = newElements;
        start = newCapacity - 1;
        end = start - size;
    }

    //Pre: 0 <= pos < elements.length
    //Post: R = ((pos - 1 + elements.length) % elements.length) &&
    //      forall i = 0...elements.length - 1 : elements[i] == elements'[i] &&
    //      start == start' && end == end' && size == size'
    private int getNextPos(int pos) {
        return (pos - 1 + elements.length) % elements.length;
    }

    //Pre: size > 0
    //Post: R = elements[start] &&
    //      forall i = 0...elements.length - 1 : elements[i] == elements'[i] &&
    //      start' == getNextPos(start) && end == end' && size' == size - 1
    public Object dequeue() {
        assert size > 0;

        Object value = element();
        size--;
        start = getNextPos(start);
        return value;
    }

    //Pre: size > 0
    //Post: R = elements[start] && forall i = 0...elements.length - 1 : elements[i] == elements'[i] &&
    //      start == start' && end == end' && size == size'
    public Object element() {
        assert size > 0;

        return elements[start];
    }

    //Pre: 
    //Post: R = size &&
    //      forall i = 0...elements.length - 1 : elements[i] == elements'[i] &&
    //      start == start' && end == end' && size == size'
    public int size() {
        return size;
    }

    //Pre: 
    //Post: R = (size == 0) 
    //      forall i = 0...elements.length - 1 : elements[i] == elements'[i] &&
    //      start == start' && end == end' && size == size'
    public boolean isEmpty() {
        return size == 0;
    }

    //Pre: 
    //Post: R = instance of ArrayQueue && 
    //      R.start == start && R.end == end && R.size == size && R.elements.length == elements.length && 
    //      forall i = 0...elements.length - 1 : elements[i] == R.elements[i] 
    //
    //      forall i = 0...elements.length - 1 : elements[i] == elements'[i] &&
    //      start == start' && end == end' && size == size'
    public ArrayQueue makeCopy() {
        final ArrayQueue copy = new ArrayQueue();
        copy.size = size;
        copy.elements = Arrays.copyOf(elements, size);
        copy.start = start;
        copy.end = end;
        return copy;
    }

    //Pre: 
    //Post: 
    //      forall i = 0...elements.length - 1 : elements[i] == elements'[i] &&
    //      start' == elements.length - 1 && end == start' && size == 0
    public void clear() {
        start = elements.length - 1;
        end = start;
        size = 0;
    }


    //Pre: 
    //Post: R = Object[size] && for i = start, j = 0; i != end; i = getNextPos(i), j++ : res[j] == elements[i]
    //      forall i = 0...elements.length - 1 : elements[i] == elements'[i] &&
    //      start == start' && end == end' && size == size'
    public Object[] toArray() {
        Object[] res = new Object[size];
        for (int i = start, j = 0; i != end; i = getNextPos(i), j++) {
            res[j] = elements[i];
        }
        return res;
    }
}
