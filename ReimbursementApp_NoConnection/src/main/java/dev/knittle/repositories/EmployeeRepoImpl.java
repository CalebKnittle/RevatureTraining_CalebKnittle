package dev.knittle.repositories;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dev.knittle.entities.Employee;
import dev.knittle.utilities.JDBCConnection;

public class EmployeeRepoImpl implements EmployeeRepo {
	
	public static Connection conn = JDBCConnection.getConnection();

	@Override
	public Employee createEmployee(Employee empl) {

		try {

			String sql = "CALL add_employee(?, ?, ?, ?, ?, ?, ?, ?)";
			CallableStatement cs = conn.prepareCall(sql);

			cs.setString(1, Integer.toString(empl.getSupervisorID()));
			cs.setString(2, Integer.toString(empl.getDeptID()));
			cs.setString(3, empl.getUsername());
			cs.setString(4, empl.getPassword());
			cs.setString(5, empl.getEmail());
			cs.setString(6, empl.getFirstName());
			cs.setString(7, empl.getLastName());			
			cs.setString(8, empl.getTitle());

			cs.execute();
			return empl; //Replace with the getEmplByUsername method? (to update the emplID)

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null; //or empl
	}

	@Override
	public Employee getEmplByID(int emplID) {

		try {

			String sql = "SELECT * FROM employee WHERE empl_id = ?";

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, Integer.toString(emplID));

			ResultSet rs = ps.executeQuery();

			// Test if anything in the return
			if (rs.next()) {

				Employee tempEmployee = new Employee();
				tempEmployee.setEmplID(rs.getInt("EMPL_ID"));
				tempEmployee.setSupervisorID(rs.getInt("SUPER_ID"));
				tempEmployee.setDeptID(rs.getInt("DEPT_ID"));
				tempEmployee.setUsername(rs.getString("USERNAME"));
				tempEmployee.setPassword(rs.getString("PASSWORD"));
				tempEmployee.setEmail(rs.getString("EMAIL"));
				tempEmployee.setFirstName(rs.getString("FIRST_NAME"));
				tempEmployee.setLastName(rs.getString("LAST_NAME"));
				tempEmployee.setTitle(rs.getString("TITLE"));
				
				return tempEmployee;
			}

		} catch (SQLException e) { // will complain "unreachable catch block"
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Employee getEmplByUsername(String username) {

		try {

			String sql = "SELECT * FROM employee WHERE username = ?";

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, username);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				Employee tempEmployee = new Employee();
				tempEmployee.setEmplID(rs.getInt("EMPL_ID"));
				tempEmployee.setSupervisorID(rs.getInt("SUPER_ID"));
				tempEmployee.setDeptID(rs.getInt("DEPT_ID"));
				tempEmployee.setUsername(rs.getString("USERNAME"));
				tempEmployee.setPassword(rs.getString("PASSWORD"));
				tempEmployee.setEmail(rs.getString("EMAIL"));
				tempEmployee.setFirstName(rs.getString("FIRST_NAME"));
				tempEmployee.setLastName(rs.getString("LAST_NAME"));
				tempEmployee.setTitle(rs.getString("TITLE"));
				
				return tempEmployee;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public Employee checkDeptHead(Employee empl) {
		
		try {

			String sql = "SELECT * FROM department WHERE dept_head_id = ?";

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, Integer.toString(empl.getEmplID()));

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				
				Employee tempEmployee = this.getEmplByID(empl.getEmplID());
				
				return tempEmployee;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public Employee getDeptHead(Employee empl) { //For a particular employee
		
		try {

			String sql = "SELECT * FROM department WHERE dept_id = ?";

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, Integer.toString(empl.getDeptID()));

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				int deptHeadID = rs.getInt("DEPT_HEAD_ID");
				Employee tempEmployee = this.getEmplByID(deptHeadID); //Could put in one line with return
				
				return tempEmployee;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee updateEmployee(Employee empl) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee deleteEmployee(int emplID) {
		// TODO Auto-generated method stub
		return null;
	}

	

	

}
