package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> comparator;

    public MaxArrayDeque(Comparator<T> c) {
        this.comparator = c;
    }

    /**
     * returns the maximum element in the deque as governed by the previously
     * given Comparator.If the MaxArrayDeque is empty, simply return null.
     */
    public T max() {
        if (comparator == null) {
            return null;
        }
        return max(this.comparator);
    }

    /**
     * returns the maximum element in the deque as governed
     * by the parameter Comparator c. If the MaxArrayDeque is empty, simply return null.
     */
    public T max(Comparator<T> c) {
        T max = get(0);
        for (int i = 1; i < size(); ++i) {
            if (c.compare(max, get(i)) < 0) {
                max = get(i);
            }
        }
        return max;
    }
}
