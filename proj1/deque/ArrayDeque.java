package deque;

/* Invariants:
front: denotes the index of the first element of the deque; always ranges from 0 to array.length-1
back: denotes (the index of the last element of the deque + 1) % array.length
*/

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private T[] array;
    private int size = 0;
    private int front = 0;
    private int back = 0;
    private final double ENLARGE_RATE = 2;
    private final double SHRINK_RATE = 0.5;

    public ArrayDeque() {
        array = (T[]) new Object[8];
    }

    @Override
    public void addFirst(T item) {
        if (size == 0) {
            array[front] = item;
            size += 1;
            return;
        }

        if (size == array.length) {
            resize((int)(size*ENLARGE_RATE));
        }

        front = ((front-1)%array.length + array.length) % array.length;
        array[front] = item;
        size += 1;
    }

    @Override
    public void addLast(T item) {
        if (size == 0) {
            array[back] = item;
            size += 1;
            return;
        }

        if (size == array.length) {
            resize((int)(size*ENLARGE_RATE));
        }
        back = (back+1) % array.length;
        array[back] = item;
        size += 1;
    }

    private void resize(int capacity) {
        T[] new_array = (T[]) new Object[capacity];
        for (int i=0; i<size; i+=1) {
            new_array[i] = array[(front+i) % array.length];
        }
        array = new_array;
        front = 0;
        back = size-1;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        for (int i=0; i<size; i+=1) {
            System.out.print(array[(front+i) % array.length] + " ");
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        // shrink array before a remove operation that will bring the number of elements in the array under 25% the length of the array
        if ((array.length >= 16) && ((size-1) < array.length*0.25)) {
            resize((int)(array.length*SHRINK_RATE));
        }

        if (size == 1) {
            T temp = array[front];
            array[front] = null;
            size = 0;
            return temp;
        }

        T temp = array[front];
        array[front] = null;
        front = (front+1) % array.length;
        size -= 1;
        return temp;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        // shrink array before a remove operation that will bring the number of elements in the array under 25% the length of the array
        if ((array.length >= 16) && ((size-1) < array.length*0.25)) {
            resize((int)(array.length*SHRINK_RATE));
        }

        if (size == 1) {
            T temp = array[back];
            array[back] = null;
            size = 0;
            return temp;
        }


        T temp = array[back];
        array[back] = null;
        back = ((back-1)%array.length+array.length) % array.length;
        size -= 1;
        return temp;
    }

    @Override
    public T get(int index) {
        return array[(front+index) % array.length];
    }

    public boolean equals(Object o) {
        if (!(o instanceof Deque<?>)) {
            return false;
        } else {
            Deque<T> o1 = (Deque<T>) o;
            if (size != o1.size()) {
                return false;
            }
            if (isEmpty() && o1.isEmpty()) {
                return true;
            }
            for (int i=0; i<size; i+=1) {
                if (array[(front+i)%array.length] != o1.get(i)) {
                    return false;
                }
            }
            return true;
        }
    }

    private class ArrayDequeIterator implements Iterator<T> {
        int pos = 0;
        @Override
        public boolean hasNext() {
            return pos < size;
        }

        @Override
        public T next() {
            T returnItem = array[(front+pos) % array.length];
            pos += 1;
            return returnItem;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }
}
