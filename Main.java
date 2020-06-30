package main;

public class Main {

	public static void main(String[] args) {
		LacedList<Integer> list = new LacedList<Integer>();
		for(int i = 0; i < 10; i++) {
			list.add((int) (10*Math.random()));
		}
		for(int i: list) {
			System.out.println(i);
		}
	}

}
