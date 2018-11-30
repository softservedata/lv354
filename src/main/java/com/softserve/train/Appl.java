package com.softserve.train;

public class Appl {
	
//	public static void my(ClassB b) {
//		System.out.println("my(ClassB b)");
//	}
//
//	public static void my(ClassA a) {
//		System.out.println("my(ClassA a)");
//	}

	public void my(Object b) {
		System.out.println("my(Object b)");
	}

	public void my(IA b) {
		System.out.println("my(IA b)");
	}

	public void my(IB b) {
		System.out.println("my(IB b)");
	}

//	public void my(ClassB b) {
//		System.out.println("my(ClassB b)");
//	}
//
//	public void my(ClassA a) {
//		System.out.println("my(ClassA a)");
//	}

//	public void my(RuntimeException e) { // Chield
//		System.out.println("my(RuntimeException e)");
//	}

//	public void my(Throwable e) {
//		System.out.println("my(Throwable e)");
//	}
//
//	public void my(Exception e) {
//		System.out.println("my(Exception e)");
//	}
//
//	public void my(RuntimeException e) { // Chield
//		System.out.println("my(RuntimeException e)");
//	}
//
//	public void my(CustomException e) { // Chield
//		System.out.println("my(CustomException e)");
//	}
	
	public static void main(String[] args) {
		//ClassA a = new ClassA(121);
		//a.method1();
		//a.m0();
		//ABase.m0();
		//
		//ClassB b = new ClassB();
		//ClassA b = new ClassA(121);
		//ABase b = new ClassA(121);
		//b.method0();
		//b.m0();
		//ABase.m0();
		//ClassA.m0();
		//
		//my(b); // Error for ABase b
		//my(null); // my(ClassB b)
		//
		Appl appl = new Appl();
		//appl.my(1); // my(Object b)
		//appl.my(null); // my(ClassB b) // my(RuntimeException e)
		//appl.my(b); // my(Object b)
		appl.my(new A());
		//
//		ClassC c = new ClassC();
//		ClassC.ClassD d = c.new ClassD();
//		d.md();
		//
//		ClassC.ClassD d = new ClassC.ClassD();
//		d.md();
	}
}
