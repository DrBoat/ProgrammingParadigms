package queue;
public abstract class AbstractQueue implements Queue {
    protected abstract Object element1();
    protected abstract void dequeue1();
    protected abstract void enqueue1(Object element);
    protected abstract void clear1();

    protected int size = 0;

    //Pre:
    //Post: R = q.size &&
    //      forall i = 0...q.elements.length - 1 : q.elements[i] == q.elements'[i] &&
    //      q.start == q.start' && q.end == q.end' && q.size == q.size'
    public int size() {
        return size;
    }

    //Pre:
    //Post: R = (q.size == 0)
    //      forall i = 0...q.elements.length - 1 : q.elements[i] == q.elements'[i] &&
    //      q.start == q.start' && q.end == q.end' && q.size == q.size'
    public boolean isEmpty() {
        return size == 0;
    }

    //Pre: q.size > 0
    //Post: R = q.elements[q.start] && forall i = 0...q.elements.length - 1 : q.elements[i] == q.elements'[i] &&
    //      q.start == q.start' && q.end == q.end' && q.size == q.size'
    public Object element() {
        assert size > 0;
        return element1();
    }

    //Pre: q.size > 0
    //Post: R = q.elements[q.start] &&
    //      forall i = 0...q.elements.length - 1 : q.elements[i] == q.elements'[i] &&
    //      q.start == q.start' && q.end == q.end' && q.size == q.size' - 1
    public Object dequeue() {
        Object el = element();
        size--;
        dequeue1();
        return el;
    }

    //Pre: element != null
    //Post:
    //      ((forall i = 0...q.elements.length - 1 : q.elements[i] == q.elements'[i]) ||
    //      (forall i = q.start, j = q.elements'.length - 1; i != q.end; i = getNextPos(q, i), j-- : q.elements'[j] == q.elements[i])) &&
    //      q.elements'.length == q.elements.length * 2 &&
    //      (q.end' == getNextPos(q, q.end) || q.end' == getNextPos(q, q.start' - q.size)) &&
    //      (q.start' == q.start || q.start == q.elements'.length - 1) && q.size' == q.size + 1
    public void enqueue(Object element) {
        assert element != null;
        enqueue1(element);
        size++;
    }

    //Pre:
    //Post:
    //      forall i = 0...elements.length - 1 : elements[i] == elements'[i] &&
    //      start' == q.elements.length - 1 && end' == start && size == 0
    public void clear() {
        size = 0;
        clear1();
    }

    //Pre:
    //Post: R = Object[size] && for i = q.start, j = 0; i != q.end; i = getNextPos(q, i), j++ : res[j] == q.elements[i]
    //      forall i = 0...q.elements.length - 1 : q.elements[i] == q.elements'[i] &&
    //      q.start == q.start' && q.end == q.end' && q.size == q.size'
    public Object[] toArray() {
        Object[] array = new Object[size];
        for (int i = 0; i < size; i++) {
            Object element = dequeue();
            array[i] = element;
            enqueue(element);
        }
        return array;
    }
}

