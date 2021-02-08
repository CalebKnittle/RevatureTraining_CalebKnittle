package dev.knittle.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

//import dev.knittle.entities.Event;
import dev.knittle.entities.Grade;
import dev.knittle.utilities.JDBCConnection;

public class GradeRepoImpl implements GradeRepo {
	
	public static Connection conn = JDBCConnection.getConnection();

	@Override
	public Grade createGrade(Grade grade) { //Probably won't use because already created in tr_form SQL procedure
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Grade getGradeByID(int gradeID) { //Should just input a formID
		
		try {

			String sql = "SELECT * FROM grade WHERE grade_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, Integer.toString(gradeID));
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				Grade tempGrade = new Grade();
				tempGrade.setFormatID(rs.getInt("FORMAT_ID"));
				tempGrade.setPassingGrade((rs.getInt("PASSING_GRADE")));
				tempGrade.setSubmittedWork((rs.getBytes("SUBMITTED_WORK")));
				tempGrade.setFinalGrade((rs.getInt("FINAL_GRADE")));
				
				return tempGrade;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return null;
	}
	
	@Override
	public Grade getDefaultPassGrade(Grade grade) {
		
		try {

			String sql = "SELECT * FROM grade_format WHERE format_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, Integer.toString(grade.getFormatID()));
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				//Grade tempGrade = new Grade();
				grade.setPassingGrade(rs.getInt("DEFAULT_PASS_GRADE"));
				
				return grade;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return null;
	}

	@Override
	public List<Grade> getAllGrades() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Grade updateGrade(Grade grade) {
		
		try {

			String sql = "UPDATE grade SET format_id = ?, passing_grade = ?, submitted_work = ?,"
					+ " final_grade = ? WHERE grade_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, Integer.toString(grade.getFormatID()));
			ps.setString(2, Integer.toString(grade.getPassingGrade()));
			ps.setBytes(3, grade.getSubmittedWork());
			ps.setString(4, Integer.toString(grade.getFinalGrade()));
			ps.setString(5, Integer.toString(grade.getGradeID()));

			ps.executeQuery();
			

			return this.getGradeByID(grade.getGradeID());

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Grade deleteGrade(int gradeID) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
