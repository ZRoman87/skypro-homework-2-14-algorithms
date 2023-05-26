package hw_2_14;

import hw_2_14.exception.StringListNotExistElementExeption;
import hw_2_14.exception.StringArrayOutOfBoundsExeption;
import hw_2_14.exception.StringListOutOfSizeExeption;

import java.util.Arrays;

public class StringListImpl implements StringList {

    private String[] list;
    private final int initCapacity = 5;
    private int size;
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
    private static final int DEFAULT_CAPACITY = 3;
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    public StringListImpl() {
         this.list = new String[initCapacity];
    }

    @Override
    public String add(String item) {

        if (size == list.length) {
            list = grow();
        }
        list[size] = item;
        size++;
        return item;
    }

    @Override
    public String add(int index, String item){

        final int s = list.length;
        if (index >= s)
            throw new StringArrayOutOfBoundsExeption();
        else if (index >= size) {
            throw new StringListOutOfSizeExeption();
        } else {
            if (size == s) {
                list = grow();
            }
            System.arraycopy(list, index,
                    list, index + 1,
                    size - index);
            list[index] = item;
            size++;
            return item;
        }
    }

    @Override
    public String set(int index, String item){
        final int s = list.length;
        if (index >= s)
            throw new StringArrayOutOfBoundsExeption();
        else if (index >= size) {
            throw new StringListOutOfSizeExeption();
        } else {
            list[index] = item;
            return item;
        }
    }
    @Override
    public String remove(String item){
        String oldValue = null;
        for (int i = 0; i < size; i++) {
            if (list[i].equals(item)) {
                oldValue = list[i];
                System.arraycopy(list, i+1,
                         list, i,
                        list.length - i-1);
                --size;
            }
        }
        if (oldValue == null) {
            throw new StringListNotExistElementExeption();
        }
        return oldValue;
    }

    @Override
    public String remove(int index){
        String oldValue = null;
        final int s = list.length;
        if (index >= s)
            throw new StringArrayOutOfBoundsExeption();
        else if (index >= size) {
            throw new StringListOutOfSizeExeption();
        } else {
            oldValue = list[index];
            System.arraycopy(list, index+1,
                    list, index,
                    list.length - index-1);
            --size;
            return oldValue;
        }
    }
    @Override
    public boolean equals(StringList otherList){
        boolean equal = false;
        if (size == otherList.size()) {
            equal = true;
            for (int i = 0; i < size; i++) {
                if (!list[i].equals(otherList.get(i))){
                    equal = false;
                    break;
                }
            }
        }
        else {
            if (otherList.size() == 0) throw new StringListOutOfSizeExeption();
        }
        return equal;
    }

    @Override
    public int size(){
        return size;
    }
    @Override
    public boolean isEmpty(){
        return size == 0;
    }
    @Override
    public String toString(){
        return Arrays.toString(list);
    }
    @Override
    public boolean contains(String item){
        boolean result = false;
        for (int i = 0; i < size; i++) {
            if (list[i].equals(item)) {
                result = true;
                break;
            }
        }
        return result;
    }
    @Override
    public int indexOf(String item){
        int result = 0;
        for (int i = 0; i < size; i++) {
            if (list[i].equals(item)) {
                result = i;
                break;
            } else {
                result = -1;
            }
        }
        return result;
    }
    @Override
    public int lastIndexOf(String item){
        int result = 99;
        for (int i = size-1; i > 0; i--) {
            if (list[i].equals(item)) {
                result = i;
                break;
            } else {
                result = -1;
            }
        }
        return result;
    }
    @Override
    public String get(int index){
        final int s = list.length;
        if (index >= s)
            throw new StringArrayOutOfBoundsExeption();
        else if (index >= size) {
            throw new StringListOutOfSizeExeption();
        } else {
            return list[index];
        }
    }

    @Override
    public void clear(){
        if (size != 0) {
            for (int i = 0; i < size; i++) {
                list[i] = null;
            }
        }

    }
    @Override
    public String[] toArray() {
        return Arrays.copyOf(list, size);
    }
    private String[] grow() {
        return grow(list.length + 1);
    }
    private String[] grow(int minCapacity) {
        return list = Arrays.copyOf(list,
                newCapacity(minCapacity));
    }
    private int newCapacity(int minCapacity) {
        // overflow-conscious code
        int oldCapacity = list.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity <= 0) {
            if (list == DEFAULTCAPACITY_EMPTY_ELEMENTDATA)
                return Math.max(DEFAULT_CAPACITY, minCapacity);
            if (minCapacity < 0) // overflow
                throw new OutOfMemoryError();
            return minCapacity;
        }
        return (newCapacity - MAX_ARRAY_SIZE <= 0)
                ? newCapacity
                : hugeCapacity(minCapacity);
    }
    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) // overflow
            throw new OutOfMemoryError();
        return (minCapacity > MAX_ARRAY_SIZE)
                ? Integer.MAX_VALUE
                : MAX_ARRAY_SIZE;
    }

}
