package main;

public class Node<T extends Object> {
	private T data;
	private Node<T> next;
	private int index;
	
	public Node(int i) {
		this.data = null;
		this.next = null;
		this.index = i;
	}
	
	public void add(T t) {
		if(this.data == null) {
			this.data = t;
		} else {
			if(this.next == null) {
				this.next = new Node<T>((this.index+1));
			}
			this.next.add(t);
		}
	}
	
	public T get(int i) {
		if(this.index == i) {
			if(this.data != null) {
				return this.data;
			} else {
				throw new Error("Index Out Of Bounds Error");
			}
		} else {
			if(this.next != null) {
				return this.next.get(i);
			} else {
				throw new Error("Index Out Of Bounds Error");
			}
		}
	}
	
	public void delete(int i) {
		if(this.index == i) {
			this.next = null;
		} else {
			if(this.next != null) {
				this.next.delete(i);
			} else {
				throw new Error("Index Out Of Bounds Error");
			}
		}
	}
	
	public void replace(int i, T t) {
		if(this.index == i) {
			if(this.data != null) {
				this.data = t;
			} else {
				throw new Error("Index Out Of Bounds Error");
			}
		} else {
			if(this.next != null) {
				this.next.replace(i, t);
			} else {
				throw new Error("Index Out Of Bounds Error");
			}
		}
	}
	
}