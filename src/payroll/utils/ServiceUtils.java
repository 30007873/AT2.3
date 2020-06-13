package payroll.utils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author: Jai Ananda
 * @version: 1.0
 */
public class ServiceUtils<T extends Comparable<T>> {

    /* This is the Comparator */
    private Comparator<T> comparator;

    /**
     * The compare method
     *
     * @param o1
     * @param o2
     * @return
     */
    private int compare(T o1, T o2) {
        return o1.compareTo(o2);
    }

    /**
     * This method is used to bubble sort the list of integers, accepts list of integers as arguments, returns list of integers and does not throw any exception
     *
     * @param values
     * @return
     */
    public List<Integer> getByBubbleSort(List<Integer> values) {
        // get number of items
        int n = values.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                Integer previous = values.get(j);
                Integer next = values.get(j + 1);
                // swap if previous element is greater than the next
                if (compare((T) previous, (T) next) > 0) {
                    values.remove(j);
                    values.add(j, next);
                    values.remove(j + 1);
                    values.add(j + 1, previous);
                }
            }
        }
        return values;
    }

    /**
     * This method is used to selection sort the list of integers, accepts list of integers as arguments, returns list of integers and does not throw any exception
     *
     * @param values
     * @return
     */
    public List<Integer> getBySelectionSort(List<Integer> values) {
        for (int i = 0; i < values.size(); i++) {
            int position = i;
            for (int j = i; j < values.size(); j++) {
                // get position of the current if greater than value at position
                if (compare((T) values.get(j), (T) values.get(position)) < 0)
                    position = j;
            }
            // select the value at position
            int min = values.get(position);
            // set positionally the value which was at ith position
            values.set(position, values.get(i));
            // set value that was at 'position' to ith position
            values.set(i, min);
        }
        return values;
    }

    /**
     * This method is used to insertion sort the list of integers, accepts list of integers as arguments, returns list of integers and does not throw any exception
     *
     * @param values
     * @return
     */
    public List<Integer> getByInsertionSort(List<Integer> values) {
        // create an integer array
        Integer[] array = new Integer[values.size()];
        // set values to integer array elements
        for (int i = 0; i < array.length; i++) {
            array[i] = values.get(i);
        }
        // initialize array length
        int n = array.length;
        for (int i = 1; i < n; ++i) {
            Integer key = array[i];
            int j = i - 1;

            // loop through until key is greater or equal to jth element of the array
            while (j >= 0 && (compare((T) array[j], (T) key) > 0)) {
                // move jth element to the right
                array[j + 1] = array[j];
                // decrement j
                j = j - 1;
            }
            // make (j + 1)th element as the key
            array[j + 1] = key;
        }
        // create a list of sorted values
        List<Integer> sortedValues = new ArrayList<Integer>();
        for (int i = 0; i < array.length; i++) {
            sortedValues.add(array[i]);
        }
        // return sorted valuesS
        return sortedValues;
    }
}
