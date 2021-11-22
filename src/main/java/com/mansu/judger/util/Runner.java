package com.mansu.judger.util;

import com.mansu.judger.listener.SubmissionResultEventHandler;
import com.mansu.judger.model.dto.*;

import java.io.ByteArrayOutputStream;
import java.util.LinkedList;
import java.util.Vector;

public class Runner {
    private final ExecutableDTO executable;
    private final ProblemDTO problem;
    private final LanguageDTO language;

    private Vector<SubmissionResultDTO> results = new Vector<>();

    private ByteArrayOutputStream testcaseArchive;
	private SubmissionResultEventHandler handler;

    public Runner(ExecutableDTO executable, ProblemDTO problem, LanguageDTO language) {
        this.executable = executable;
        this.problem = problem;
        this.language = language;
    }

    public Runner(ExecutableDTO executable, ProblemDTO problem, LanguageDTO language, SubmissionResultEventHandler handler) {
        this.executable = executable;
        this.problem = problem;
        this.language = language;
        this.handler = handler;
    }

    public ExecutableDTO getExecutable() {
        return executable;
    }

    public ProblemDTO getProblem() {
        return problem;
    }

    public SubmissionResultEventHandler getHandler() {
        return handler;
    }

    public Vector<SubmissionResultDTO> getResults() {
        return results;
    }

    public ByteArrayOutputStream getTestcaseArchive() {
        return testcaseArchive;
    }

    public void setHandler(SubmissionResultEventHandler handler) {
        this.handler = handler;
    }

    public void execute() {
        LinkedList<Thread> runnerThreads = new LinkedList<>();
        LinkedList<TestCaseRunner> testCaseRunners = new LinkedList<>();

        int size = this.problem.getTestcases().size();
        for (int i = 0; i < size; ++i) {
            TestCaseDTO testcase = this.problem.getTestcases().get(i);
            testcase.setTcNum(i + 1);
            TestCaseRunner runner = new TestCaseRunner(getExecutable(), getProblem(), testcase, language);
            runner.setHandler(handler);

            Thread runnerThread = new Thread(runner);
            runnerThread.start();

            runnerThreads.add(runnerThread);
            testCaseRunners.add(runner);
        }

        for (int i = 0; i < size; ++i) {
            Thread runnerThread = runnerThreads.get(i);
            try {
                runnerThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            TestCaseRunner testCaseRunner = testCaseRunners.get(i);
            SubmissionResultDTO result = testCaseRunner.getResult();

            this.results.add(result);
        }
    }
}
