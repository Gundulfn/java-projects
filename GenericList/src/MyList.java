import java.util.Arrays;

public class MyList<T> {
    private Object[] array;
    private final int DEFAULT_CAPACITY = 10;
    private int capacity = DEFAULT_CAPACITY;
    private int elementCount;

    // Constructors
    public MyList() {
        array = new Object[capacity];
    }

    public MyList(int size) {
        this.capacity = size;
        array = new Object[capacity];
    }

    // Information methods
    public T get(int index) {
        if (index < 0 || index >= elementCount) {
            return null;
        }

        return (T) array[index];
    }

    public int size() {
        return elementCount;
    }

    public boolean isEmpty() {
        return elementCount <= 0;
    }

    public int getCapacity() {
        return array.length;
    }

    public int indexOf(T value) {
        for (int i = 0; i < elementCount; i++) {
            if (array[i].equals(value)) {
                return i;
            }
        }

        return -1;
    }

    public int lastIndexOf(T value) {
        for (int i = elementCount - 1; i >= 0; i--) {
            if (array[i].equals(value)) {
                return i;
            }
        }

        return -1;
    }

    public T[] toArray() {
        return (T[]) array;
    }

    public MyList<T> sublist(int start, int finish) {
        MyList subList = new MyList();
        subList.setArray(Arrays.copyOfRange(array, start, finish + 1), finish - start + 1);
        return subList;
    }

    public boolean contains(T value) {
        for (int i = 0; i < elementCount; i++) {
            if (array[i].equals(value)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(array, elementCount));
    }

    // Add-Remove methods

    /**
     * This method either adding new item or updating existing item
     * and moving element to the left
     *
     * @param index
     * @param value
     * @return
     */
    public T set(int index, T value) {
        if (value.equals(null) || index < 0 || index >= array.length) {
            System.out.println("Cannot set at " + index + " with value: " + value);
            return null;
        }

        if (array[index] == null)
            elementCount++;

        array[index] = value;
        return (T) array[index];
    }

    private void setArray(T[] array, int elementCount) {
        this.array = array;
        this.capacity = array.length;
        this.elementCount = elementCount;
    }

    public void add(T value) {
        if (elementCount >= array.length) {
            increaseCapacity();
        }

        set(elementCount, value);
    }

    public T remove(int index) {
        if (index < 0 || index >= array.length) {
            return null;
        }

        array[index] = null;
        elementCount--;

        refreshArrayFrom(index);
        return (T) array[index];
    }

    public void increaseCapacity() {
        capacity *= 2;
        array = Arrays.copyOf(array, capacity);
    }

    public void clear() {
        capacity = DEFAULT_CAPACITY;
        elementCount = 0;
        array = new Object[capacity];
    }

    private void refreshArrayFrom(int removedIndex) {
        for (; removedIndex < array.length; removedIndex++) {
            if (removedIndex + 1 < array.length && array[removedIndex + 1] != null)
                array[removedIndex] = array[removedIndex + 1];
        }
    }
}