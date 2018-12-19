package com.softserve.edu;

import com.softserve.edu.one.Work;

public class ApplWorkEdu extends Work {
	
	public static void main(String[] args) {
		Work work = new Work();
		work.work1();
		//work.work2(); // Error
		//work.work3(); // Error
		//
		ApplWorkEdu workEdu = new ApplWorkEdu();
		workEdu.work1();
		workEdu.work2();
	}

}
