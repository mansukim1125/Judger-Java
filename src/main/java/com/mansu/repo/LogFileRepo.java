package com.mansu.repo;

import java.io.File;

public class LogFileRepo {
	private static File logFile = new File(System.getProperty("user.home") + "/JudgerLog", "judgmentLog.json");

	public static File getInstance() {
		return logFile;
	}
}
