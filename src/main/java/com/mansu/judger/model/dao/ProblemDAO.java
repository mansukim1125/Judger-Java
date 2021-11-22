package com.mansu.judger.model.dao;

import com.mansu.judger.model.dto.ProblemDTO;
import com.mansu.judger.model.dto.TestCaseDTO;
import com.mansu.judger.util.db.Connector;
import com.mansu.repo.GsonRepo;
import com.mansu.repo.TypeRepo;

import java.lang.reflect.Type;
import java.sql.*;
import java.util.Vector;

public class ProblemDAO {
    public static Vector<ProblemDTO> getProblems() {
    	Vector<ProblemDTO> problemList = new Vector<ProblemDTO>();
        try (Connection conn = Connector.getConnection(false)) {
            try (PreparedStatement stmt = conn.prepareStatement("SELECT id, title, description, author_id, created_at FROM problems")) {
                try (ResultSet result = stmt.executeQuery()) {
                    ProblemDTO problem = null;
                    while (result.next()) {
                        int id = result.getInt("id");
                        String title = result.getString("title");
                        String description = result.getString("description");
                        int authorId = result.getInt("author_id");
                        Timestamp createdAt = result.getTimestamp("created_at");

                        problem = new ProblemDTO(id, title, description, authorId, createdAt);
                        problemList.add(problem);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return problemList;
    }

    public static ProblemDTO getProblemById(int id) {
    	ProblemDTO problem = null;
    	Type t = TypeRepo.getVectorOfTestCaseDTO();
        try (Connection conn = Connector.getConnection(false)) {
            try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM problems WHERE id = ?")) {
                stmt.setInt(1, id);
            	try (ResultSet result = stmt.executeQuery()) {
                    while (result.next()) {
                        id = result.getInt("id");
                        String title = result.getString("title");
                        String description = result.getString("description");
                        int authorId = result.getInt("author_id");
                        int maxCpuTime = result.getInt("max_cpu_time");
                        int maxRealTime = result.getInt("max_real_time");
                        int maxMemory = result.getInt("max_memory");
                        String testcasesString = result.getString("testcases");
                        Timestamp createdAt = result.getTimestamp("created_at");
                        Vector<TestCaseDTO> testCases = GsonRepo.getInstance().fromJson(testcasesString, t);
                        
                        problem = new ProblemDTO(id, title, description, authorId, maxCpuTime, maxRealTime, maxMemory, testCases, createdAt);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return problem;
    }
    
    public static void createProblem(ProblemDTO problem) {
//    	TODO: 문제 DB에 추가.
    	try (Connection conn = Connector.getConnection(false)) {
            try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO problems (title, description, max_cpu_time, max_real_time, max_memory, testcases) VALUES (?, ?, ?, ?, ?, ?)")) {
                stmt.setString(1, problem.getTitle());
                stmt.setString(2, problem.getDescription());
                stmt.setInt(3, problem.getMaxCpuTime());
                stmt.setInt(4, problem.getMaxRealTime());
                stmt.setLong(5, problem.getMaxMemory());
                
                String testcasesString = GsonRepo.getInstance().toJson(problem.getTestcases());
                stmt.setString(6, testcasesString);
                
            	stmt.executeUpdate();
            	
            	conn.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
