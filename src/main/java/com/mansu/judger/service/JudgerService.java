package com.mansu.judger.service;

import com.mansu.judger.listener.JudgmentCompletedListener;
import com.mansu.judger.listener.SubmissionResultEventHandler;
import com.mansu.judger.model.dto.*;
import com.mansu.judger.util.CompilationException;
import com.mansu.judger.util.Runner;
import com.mansu.judger.util.Compiler;
import java.util.Vector;

public class JudgerService implements Runnable {
	private Vector<SubmissionResultDTO> judgeResult = null;
	
//	public static void main(String[] args) {		
//		ProblemDTO problem = ProblemDAO.getProblemById(16);
//		LanguageDTO clang = new Python3LanguageDTO();
//		String code = "A, B = map(int, input().split())\r\n"
//				+ "print(A + B\r\n"
//				+ "";
//	
//		JudgerService judgerService = new JudgerService(problem, clang, code);
//		Thread judgerServiceThread = new Thread(judgerService);
//		judgerServiceThread.start();
//		
//		Vector<SubmissionResultDTO> results = null;
//		try {
//			judgerServiceThread.join();
//			results = judgerService.getResults();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}		
//		
//		if (results != null) {
//			for (SubmissionResultDTO result : results) {
//				System.out.println("{\"tc_num\":"+ result.getTcNum() +",\"cpu_time\":"+ result.getCpuTime() +",\"real_time\":" + result.getRealTime() +",\"memory\":" + result.getMemory() + ",\"signal\":"+ result.getSignal() +",\"exit_code\":"+ result.getExitCode() +",\"error\":"+ result.getError() +",\"result\":"+ result.getResult() +",\"output\":"+ result.getOutput() +",\"error_msg\":"+ result.getErrorMsg() +",\"is_correct\":"+ result.isCorrect() +"}");
//				System.out.println(result.getCpuTime());
//			}
//		}
//	}

	private ProblemDTO problem;
	private LanguageDTO lang;
	private String code;
	private Runner runner;
	private SubmissionResultEventHandler resultEventHandler = null;
	private JudgmentCompletedListener judgmentCompletedListener;

	public JudgerService(ProblemDTO problem, LanguageDTO lang, String code) {
		this.problem = problem;
		this.lang = lang;
		this.code = code;
	}
	
	@Override
	public void run() {
		judgment(problem, lang, code);
	}
	
	public void judgment(ProblemDTO problem, LanguageDTO lang, String code) {
        try {        
			Compiler compiler = new Compiler(lang, code);
	
	        compiler.execute();
	        
	        ExecutableDTO executable = compiler.getExecutable();
	
	        this.runner = new Runner(executable, problem, lang);
	
	        if (resultEventHandler != null) {
	        	runner.setHandler(resultEventHandler);	        	
	        }
	        
	        runner.execute();
	        
	        judgeResult = runner.getResults();
        } catch (CompilationException e) {
            //TODO: handle with COMPILE_ERROR and terminate process.
            judgeResult = new Vector<SubmissionResultDTO>();
            int testcaseLength = problem.getTestcases().size();
            for (int i = 0; i < testcaseLength; ++i) {
            	SubmissionResultDTO submissionResult = new SubmissionResultDTO(i + 1, 0, 0, 0, 0, (int) e.getExitCode(), 0, 6, e.getMessage(), "", false);
            	if (resultEventHandler != null) {
            		resultEventHandler.onResult(submissionResult);
            	}
            	judgeResult.add(submissionResult);
            }            
        } finally {
//        	TODO: Invoke judgmentCompletedListener.
        	judgmentCompletedListener.onResult(judgeResult);
        }
	}
	
	public Vector<SubmissionResultDTO> getResults() {
		return judgeResult;
	}

	public void setResultEventHandler(SubmissionResultEventHandler resultEventHandler) {
		// TODO Auto-generated method stub
		this.resultEventHandler = resultEventHandler;
	}

	public void setJudgmentCompletedEventHandler(JudgmentCompletedListener judgmentCompletedListener) {
		// TODO Auto-generated method stub
		this.judgmentCompletedListener = judgmentCompletedListener;
	}
}
