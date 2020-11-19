package net.questions.solutions;

import java.util.Arrays;

public class IntHeap {

    private int capacity = 10;
    private int size = 0;
    int[] items = new int[capacity];

    private MinMaxType heapType;

    public enum MinMaxType {MIN_HEAP,MAX_HEAP};

    public IntHeap(MinMaxType heapType) {
        this.heapType = heapType;
    }

    private int getLeftChildIndex(int parentIndex) {
        return 2 * parentIndex + 1;
    }

    private int getRightChildIndex(int parentIndex) {
        return 2 * parentIndex + 2;
    }

    private int getParentIndex(int childIndex) {
        return (childIndex - 1) / 2;
    }

    private boolean hasLeftChild(int index) {
        return getLeftChildIndex(index) < size;
    }

    private boolean hasRightChild(int index) {
        return getRightChildIndex(index) < size;
    }

    private boolean hasParent(int index) {
        return getParentIndex(index) >= 0;
    }

    private int leftChild(int index) {
        return items[getLeftChildIndex(index)];
    }

    private int rightChild(int index) {
        return items[getRightChildIndex(index)];
    }

    private int parent(int index) {
        return items[getParentIndex(index)];
    }

    private void swap(int[] c, int i, int l) {
        int temp = c[i];
        c[i] = c[l];
        c[l] = temp;
    }

    private void ensureCapacity() {
        if (size == capacity) {
            capacity *= 2;
            items = Arrays.copyOf(items, capacity);
        }
    }

    public int peek() {
        return items[0];
    }

    public int poll() {
        int temp = items[0];
        items[0] = items[size - 1];

        size--;
        if(heapType == MinMaxType.MIN_HEAP)
            heapifyDownMin();
        else
            heapifyDownMax();

        return temp;
    }

//    public void add(int[] arr){
//        Arrays.stream(arr).forEach(this::add);
//    }

    public void add(int item) {
        ensureCapacity();
        items[size] = item;

        size++;

        if(heapType == MinMaxType.MIN_HEAP)
            heapifyUpMin();
        else
            heapifyUpMax();
    }

    public void heapifyUpMin() {
        int index = size - 1;
        while (hasParent(index) && parent(index) > items[index]) {
            swap(items, getParentIndex(index), index);
            index = getParentIndex(index);
        }
    }

    public void heapifyUpMax() {
        int index = size - 1;
        while (hasParent(index) && parent(index) < items[index]) {
            swap(items, getParentIndex(index), index);
            index = getParentIndex(index);
        }
    }

    public void heapifyDownMin() {
        int index = 0;
        while (hasLeftChild(index)) {
            int smallerChildIndex = getLeftChildIndex(index);
            if (hasRightChild(index) &&
                    rightChild(index) < leftChild(index)) {
                smallerChildIndex = getRightChildIndex(index);
            }

            if (items[index] < items[smallerChildIndex]) {
                break;
            } else {
                swap(items, index, smallerChildIndex);
            }

            index = smallerChildIndex;
        }
    }

    public void heapifyDownMax() {
        int index = 0;
        while (hasLeftChild(index)) {
            int biggerChildIndex = getLeftChildIndex(index);
            if (hasRightChild(index) &&
                    rightChild(index) > leftChild(index)) {
                biggerChildIndex = getRightChildIndex(index);
            }

            if (items[index] > items[biggerChildIndex]) {
                break;
            } else {
                swap(items, index, biggerChildIndex);
            }

            index = biggerChildIndex;
        }
    }

    public int size(){
        return size;
    }
}
