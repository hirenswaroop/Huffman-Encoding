import java.util.ArrayList;

/**
 * The heap or priority queue
 * datastructure that takes a 
 * generic type
 * 
 * @author uscart
 *
 * @param <T>
 */
public class Heap <T extends Comparable<T>> {	
	private ArrayList<T> heap;
	
	/**
	 * Constructor for the heap which
	 * makes a new ArrayList
	 */
	public Heap () {
		heap = new ArrayList<T>();
	}
	
	private int getParent (int childIndex) {
		return ((childIndex - 1) / 2);
	}
	
	private int getChild (int parentIndex, int childNum) {
		return ((parentIndex * 2) + childNum);
	}
	
	/**
	 * Adds an element to the heap
	 * and heapifies that element
	 * 
	 * @param element
	 */
	public void add(T element) {
		heap.add(element);
		if (heap.size() != 1) {
			heapifyUp(heap.size() - 1);
		}
	}
	
	/**
	 * Removes the root node which 
	 * is the smallest element
	 * 
	 * @return removed element
	 */
	public T remove() {
		T removed = heap.get(0);
		if (heap.size() - 1 == 0) {
			heap.remove(heap.get(0));
		} else {
			heap.set(0, heap.remove(heap.size() - 1));
			heapifyDown(0);
		}
		return removed;
	}
	
	/**
	 * Gets root node
	 * 
	 * @return root node
	 */
	public T peek() {
		return heap.get(0);
	}
	
	/**
	 * Checks if heap is empty
	 * 
	 * @return boolean
	 */
	public boolean isEmpty() {
		if (heap.size() == 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * Gets size of the heap
	 * 
	 * @return size
	 */
	public int size() {
		return heap.size();
	}
	
	/**
	 * Gets the desired index
	 * 
	 * @param index
	 * @return heap element at index index
	 */
	public T get(int index) {
		return heap.get(index);
	}
	
	private void heapifyUp (int childIndex) {
		T tmp = heap.get(childIndex);
		int parent = getParent(childIndex);
		
		if (heap.get(parent) != null) {
			if (tmp.compareTo(heap.get(parent)) < 0) {
				heap.set(childIndex, heap.get(parent));
				heap.set(parent, tmp);
				heapifyUp(parent);
			}
		}
	}
	
	private void heapifyDown (int index) {
		T tmp = heap.get(index);
		int childInd = getChild(index, 1);
		int child2Ind = getChild(index, 2);
		T child = null;
		T child2 = null;
		if (childInd < heap.size() && heap.get(childInd) != null) {
			child = heap.get(childInd);
		}
		if (child2Ind < heap.size() && heap.get(child2Ind) != null) {
			child2 = heap.get(child2Ind);
		}

		if ((child == null && child2 == null) || heap.size() == 1) {
			return;
		}
		
		if (child == null) {
			if (child2.compareTo(tmp) < 0) {
				heap.set(index, child2);
				heap.set(child2Ind, tmp);
				heapifyDown(child2Ind);
			}
		} else if (child2 == null) {
			if (child.compareTo(tmp) < 0) {
				heap.set(index, child);
				heap.set(childInd, tmp);
				heapifyDown(childInd);
			}
		}
		
		if (child != null && child2 != null) {
			if ((child.compareTo(child2) <= 0) && (child.compareTo(tmp) < 0)) {
				heap.set(index, child);
				heap.set(childInd, tmp);
				heapifyDown(childInd);
			}
			
			if ((child2.compareTo(child) < 0) && (child2.compareTo(tmp) < 0)) {
				heap.set(index, child2);
				heap.set(child2Ind, tmp);
				heapifyDown(child2Ind);
			}
		}
	}
}