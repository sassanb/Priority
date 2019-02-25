import java.util.ArrayList;

public class PriorityHeap{
	private int[] heap;
	private int size;

	public PriorityHeap(ArrayList<Integer> keys) {
		size = 0;
		heap = new int[keys.size()];
		buildHeap(keys);
	}

	private void buildHeap(ArrayList<Integer> keys) {
		for(int key :keys) insert(key);
		printHeap();
	}

	public void insert(int key){
		if(size == heap.length) doubleHeapSize();
		size++;
		int index = size - 1;
		heap[index] = key;
		heapifyUp(index);
	}

	public void heapifyUp(int index) {
		if(index == 0) return;
		if(isLessThanParent(index)) {
			int swapIndex = swap(index, getParent(index),true);
			heapifyUp(swapIndex);
		}
	}

	public void heapifyDown(int index) {
		int minChild = getMinChild(index);
		if(minChild >= 0 && isLessThanParent(minChild)){
			int swapIndex = swap(minChild,index,false);
			heapifyDown(swapIndex);
		}
	}

	public void delete(int index){
		if(isEmpty() || index < 0 || index >= size) System.out.println("Key not found!");
		else deleteAt(index);
	}

	public void deleteAt(int index){
		heap[index] = heap[size - 1];
		size--;
		if(!isEmpty() && isLessThanParent(index)) heapifyUp(index);
		else if(!isEmpty()) heapifyDown(index);
	}

	public void deleteMin(){
		if(isEmpty()) System.out.println("Heap is empty");
		else{
			int min = heap[0];
			heap[0] = heap[size-1];
			size--;
			if(!isEmpty()) heapifyDown(0);
		}
	}

	public void increaseKey(int index, int increaseBy){
		if(index > -1 && index < size){
			heap[index] += increaseBy;
			if(largerThanChildren(index)) heapifyDown(index);
		}else System.out.println("Key not found!");
	}

	public void decreaseKey(int index, int decreaseBy){
		if(index> -1 && index < size){
			heap[index] -= decreaseBy;
			if(isLessThanParent(index)) heapifyUp(index);
		}else System.out.println("Key not found!");
	}

	public int swap(int index, int parentIndex,boolean heapUp){
		int tmp = heap[parentIndex];
		heap[parentIndex] = heap[index];
		heap[index] = tmp;
		if(heapUp) return parentIndex;
		return index;
	}

	public int getMinChild(int index){
		int leftChild = getLeftChild(index);
		int rightChild = getRightChild(index);
		if(leftChild<size && rightChild<size) return heap[leftChild] <= heap[rightChild] ? leftChild : rightChild;
		else if(leftChild<size) return leftChild;
		else if(rightChild<size) return rightChild;
		else return -1;
	}

	public int findKeyIndex(int key){
		for(int i = 0;i<size;i++){
			if(key == heap[i]) return i;
		}
		return -1;
	}

	public void doubleHeapSize(){
		int[] newHeap = new int[heap.length*2];
		System.arraycopy(heap,0,newHeap,0,heap.length);
		heap = newHeap;
	}

	public int getLeftChild(int index){
		return ((2 * index ) + 1);
	}

	public int getRightChild(int index){
		return ((2 * index ) + 2);
	}

	public int getParent(int i){
		return (i - 1) / 2;
	}

	public boolean isEmpty() {
		return size <= 0;
	}

	public boolean largerThanChildren(int index){
		int leftChild = getLeftChild(index);
		int rightChild = getRightChild(index);
		if(leftChild<size && rightChild<size) return ((heap[index] > heap[leftChild]) || (heap[index]>heap[rightChild]));
		else if(leftChild<size) return (heap[index] > heap[leftChild]);
		else if(rightChild<size) return (heap[index]>heap[rightChild]);
		return false;
	}

	public boolean isLessThanParent(int index){
		int parent = getParent(index);
		return ((parent >= 0) && (heap[parent] > heap[index]));
	}

	public void printHeap(){
		if(isEmpty()) System.out.println("Empty Heap");
		else for(int i = 0; i<size; i++) System.out.print(heap[i] + " ");
		System.out.println();
	}
}
