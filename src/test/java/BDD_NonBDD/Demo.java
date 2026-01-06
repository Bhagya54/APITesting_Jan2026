package BDD_NonBDD;

public class Demo {

	public Demo step1() {
		System.out.println("step1");
		return new Demo();
	}
	
	public Demo step2() {
		System.out.println("step2");
		return new Demo();
	}
	
	public Demo step3() {
		System.out.println("step3");
		return new Demo();
	}
	public static void main(String[] args) {
		Demo d1 = new Demo();
		d1.step1();
		d1.step2();
		d1.step3();
		
		d1.step1().step2().step3();

	}

}
