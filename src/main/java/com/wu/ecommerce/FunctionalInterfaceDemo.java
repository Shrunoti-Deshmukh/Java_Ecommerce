package com.wu.ecommerce;

@FunctionalInterface
public interface FunctionalInterfaceDemo {
	public int add(int a, int b);
	
	default void test1() {
		System.out.println("test1");
	}
	default void test2() {
		System.out.println("test2");
	}
	default void test3() {
		System.out.println("test3");
	}
}
class Test{
	public static void resultfun(FunctionalInterfaceDemo demo) {
		int result = demo.add(10, 20);
		System.out.println(result);
	}
	public static void main(String[] args) {
		resultfun((a,b)->a+b);
	}
}