package com.mansu.judger.util;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Vector;

import com.mansu.judger.model.dto.CompletedSubmissionDTO;
import com.mansu.repo.GsonRepo;
import com.mansu.repo.TypeRepo;

public class JudgmentLog {
	Type t = TypeRepo.getVectorOfCompletedSubmissionDTO();
	
	private Vector<CompletedSubmissionDTO> judgmentLogs;
	private File logPath;
	public JudgmentLog(File logPath) {
		// TODO Auto-generated constructor stub
		try {
			this.logPath = logPath;
			String path = logPath.getParent();
			Path ad = Paths.get(path);
			try {
				Files.createDirectory(ad);
			} catch (FileAlreadyExistsException e) {
			}
			if (logPath.exists()) {
//				TODO: 파일 읽어 Vector<CompletedSubmissionDTO>로 변환 후 this.judgmentLogs에 저장.
				FileReader reader = new FileReader(logPath);

				int c;
				StringBuffer buf = new StringBuffer();
				while ((c = reader.read()) != -1) {	
					buf.append((char) c);
				}
				reader.close();

				this.judgmentLogs = GsonRepo.getInstance().fromJson(new String(buf), t);
			} else {
//				TODO: this.judgmentLogs에 [] 저장 후 파일에 쓰기.
				judgmentLogs = new Vector<>();
				String jsonString = GsonRepo.getInstance().toJson(judgmentLogs);
				
				FileWriter writer = new FileWriter(logPath);
				for (int i = 0; i < jsonString.length(); ++i) {
					writer.write(jsonString.charAt(i));
				}
				writer.close();
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void add(CompletedSubmissionDTO completedSubmission) {
		// TODO Auto-generated method stub
//		System.out.println("add실행");
		this.judgmentLogs.add(completedSubmission);
//		this.judgmentLogs.add(completedSubmission);
//		System.out.println("add종료");
	}

	public void save() {
		// TODO Auto-generated method stub
//		TODO: completedSubmission를 파일에 덮어쓰기.
		try
		{
//			System.out.println("save");
			String jsonString = GsonRepo.getInstance().toJson(judgmentLogs);
			FileWriter writer = new FileWriter(this.logPath);
			for (int i = 0; i < jsonString.length(); ++i) {
				writer.write(jsonString.charAt(i));
			}
			writer.close();
		}
		catch(IOException e)
		{
			System.out.println(e);
		}
	}

	public Vector<CompletedSubmissionDTO> getInstance() {
		// TODO Auto-generated method stub
		return judgmentLogs;
	}
}
