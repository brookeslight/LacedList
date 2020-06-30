package main;

import java.io.Serializable;
import java.util.Iterator;

public class LacedList<T extends Object> implements Iterable<T>, Iterator<T>, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 719743289336677749L;
	private Node<T> even = new Node<T>(0);
	private Node<T> odd = new Node<T>(0);
	private int size = 0; //size-1 == # of indices
	private int count = -1;
	
	public void add(T t) {
		synchronized(this) {
			if(this.size % 2 == 0) {
				this.even.add(t);
			} else {
				this.odd.add(t);
			}
			this.size++;
		}
	}
	
	public T get(int i) {
		synchronized(this) {
			if(i % 2 == 0) {
				return this.even.get((i/2));
			} else {
				return this.odd.get((i-((i+1)/2)));
			}
		}
	}
	
//	public void remove(int i) {
//		//swap
//		for(int n = 0; n < (this.size-1-i); n++) {
//			this.replace((i+n), this.get((i+n+1)));
//		}
//		//remove last
//		synchronized(this) {
//			if(this.size == 1) {
//				this.even = new Node<T>(0);
//			} else if(this.size == 2) {
//				this.odd = new Node<T>(0);
//			} else if(this.size % 2 == 0) {
//				this.odd.delete(((this.size-3)-((this.size-2)/2)));
//			} else {
//				this.even.delete(((this.size-3)/2));
//			}
//			this.size--;
//		}
//	}
	
	public void replace(int i, T t) {
		synchronized(this) {
			if(i % 2 == 0) {
				this.even.replace((i/2), t);
			} else {
				this.odd.replace((i-((i+1)/2)), t);
			}
		}
	}
	
	public int size() {
		return this.size;
	}
	
	public void remove(int i) {
		//save data
		@SuppressWarnings("unchecked")
		T[] data = (T[]) new Object[(this.size-1-i)];
		for(int n = 1; n <= (data.length); n++) {
			data[(n-1)] = this.get((n+i));
		}
		//remove nodes
		synchronized(this) {
			if(i == 0) {
				this.odd = new Node<T>(0);
				this.even = new Node<T>(0);
			} else if(i == 1) {
				this.odd = new Node<T>(0);
				this.even.delete(0);
			} else if(i % 2 == 0) {
				this.even.delete(((i-2)/2));
				this.odd.delete(((i-1)-(i/2)));
			} else {
				this.even.delete(((i-1)/2));
				this.odd.delete(((i-2)-((i-1)/2)));
			}
			this.size = i;
		}
		//add back
		for(T t: data) {
			this.add(t);
		}
	}
	
//////////////////////Iterator Code////////////////////////////////////////////

	@Override
	public boolean hasNext() {
		if(this.count < (this.size-1)) {
			synchronized(this) {
				this.count++;
			}
			return true;
		} else {
			synchronized(this) {
				this.count = -1;
			}
			return false;
		}
	}

	@Override
	public T next() {
		return this.get(this.count);
	}
	
	@Override
	public void remove() {
		this.remove(this.count);
	}

	@Override
	public Iterator<T> iterator() {
		return this;
	}
	
}