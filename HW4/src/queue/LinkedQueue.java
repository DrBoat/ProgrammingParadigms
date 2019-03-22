package queue;
public class LinkedQueue extends AbstractQueue {
    private static class Node {
        Object value;
        Node next;

        Node(Object value, Node next) {
            assert value != null;
            this.value = value;
            this.next = next;
        }
    }

    private Node head, tail;

    public LinkedQueue() {
        head = tail = null;
        size = 0;
    }

    //Pre: element != null
    //Post:
    //      ((forall i = 0...q.elements.length - 1 : q.elements[i] == q.elements'[i]) ||
    //      (forall i = q.start, j = q.elements'.length - 1; i != q.end; i = getNextPos(q, i), j-- : q.elements'[j] == q.elements[i])) &&
    //      q.elements'.length == q.elements.length * 2 &&
    //      (q.end' == getNextPos(q, q.end) || q.end' == getNextPos(q, q.start' - q.size)) &&
    //      (q.start' == q.start || q.start == q.elements'.length - 1) && q.size' == q.size + 1
    //      q.elements[end] == q.element
    public void enqueue1(Object element) {
        if (size == 0) {
            tail = head = new Node(element, null);
        } else {
            Node newTail = new Node(element, null);
            tail.next = newTail;
            tail = newTail;
        }
    }

    //Pre: q.size > 0
    //Post: R = q.elements[q.start] &&
    //      forall i = 0...q.elements.length - 1 : q.elements[i] == q.elements'[i] &&
    //      q.start == q.start' && q.end == q.end' && q.size == q.size' - 1
    public void dequeue1() {
        head = head.next;
    }

    //Pre: q.size > 0
    //Post: R = q.elements[q.start] && forall i = 0...q.elements.length - 1 : q.elements[i] == q.elements'[i] &&
    //      q.start == q.start' && q.end == q.end' && q.size == q.size'
    public Object element1() {
        return head.value;
    }

    //Pre:
    //Post:
    //      forall i = 0...elements.length - 1 : elements[i] == elements'[i] &&
    //      start' == elements.length - 1 && end == start' && size == 0
    public void clear1() {
        head = tail = null;
    }
}



