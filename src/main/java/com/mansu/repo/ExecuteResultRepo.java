package com.mansu.repo;

public class ExecuteResultRepo {
	public static String getResultStringById(int id) {
		String resultString = "";
		switch (id) {
			case 0: resultString = "실행 완료"; break;
			case 1: resultString = "CPU 시간 제한 초과"; break;
			case 2: resultString = "실제 시간 제한 초과"; break;
			case 3: resultString = "메모리 제한 초과"; break;
			case 4: resultString = "런타임 에러"; break;
			case 5: resultString = "시스템 에러"; break;
			case 6: resultString = "컴파일 에러"; break;
		}
		return resultString;
	}
}
