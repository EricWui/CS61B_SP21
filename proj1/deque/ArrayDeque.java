package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private T[] elements;
    private int startIndex;
    private int endIndex;

    public ArrayDeque() {
        elements = (T[]) new Object[8];
        startIndex = 3;
        endIndex = 4;
    }

    /**
     * Adds an item of type T to the front of the deque.
     */

    @Override
    public void addFirst(T item) {
        resize();
        elements[startIndex--] = item;
    }

    /**
     * Adds an item of type T to the back of the deque
     */
    @Override
    public void addLast(T item) {
        resize();
        elements[endIndex++] = item;
    }

    /**
     * Returns the number of items in the deque
     */
    @Override
    public int size() {
        return endIndex - startIndex - 1;
    }

    /**
     * Prints the items in the deque from first to last, separated by a space.
     */
    @Override
    public void printDeque() {
        for (int i = startIndex + 1; i < endIndex; i++) {
            System.out.print(elements[i].toString() + " ");
        }
        System.out.println();
    }

    /**
     * Removes and returns the item at the front of the deque.
     * If no such item exists, returns null.
     */
    @Override
    public T removeFirst() {
        if (size() == 0) {
            return null;
        }
        T item = elements[++startIndex];
        resize();
        return item;
    }

    /**
     * Removes and returns the item at the back of the deque.
     * If no such item exists, returns null.
     */
    @Override
    public T removeLast() {
        if (size() == 0) {
            return null;
        }
        T item = elements[--endIndex];
        resize();
        return item;
    }

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null.
     */
    @Override
    public T get(int index) {
        int size = size();
        if (index < 0 || index >= size) {
            return null;
        }
        return elements[startIndex + index + 1];
    }

    /**
     * when factor is very low or very high,
     * build a new array and copy the elements to the new array.
     */
    private void resize() {
        int length = elements.length;
        int size = size();
        double factor = size / (length + 0.0);
        int newLength;
        if (factor < 0.25 && length > 16) {
            //make size of elements small
            T[] newElements = (T[]) new Object[length / 2];
            cpElements(newElements);
            elements = newElements;
        }
        if (factor >= 0.85) {
            //make size of elements large
            T[] newElements = (T[]) new Object[length * 2];
            cpElements(newElements);
            elements = newElements;
        }
        if (startIndex <= 0 || endIndex >= elements.length - 1) {
            //when only addFirst or addLast
            T[] newElements = (T[]) new Object[length];
            cpElements(newElements);
            elements = newElements;
        }
    }

    /**
     * build a new array and copy the elements to the new array.
     */

    private void cpElements(T[] newElements) {
        int length = newElements.length;
        int index = startIndex + 1;
        int size = size();
        startIndex = (length - size()) / 2 - 1;
        for (int i = 0; i < size; ++i) {
            newElements[startIndex + i + 1] = elements[index++];
        }
        endIndex = startIndex + size + 1;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != LinkedListDeque.class
                && obj.getClass() != ArrayDeque.class) {
            return false;
        }
        Deque o = (Deque) obj;
        if (o.size() != this.size()) {
            return false;
        }
        int size = this.size();
        for (int i = 0; i < size; ++i) {
            if (this.get(i) != o.get(i) && !this.get(i).equals(o.get(i))) {
                return false;
            }
        }
        return true;
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int index;

        ArrayDequeIterator() {
            index = 0;
        }

        @Override
        public boolean hasNext() {
            return index < size();
        }

        @Override
        public T next() {
            return get(index++);
        }
    }
}
