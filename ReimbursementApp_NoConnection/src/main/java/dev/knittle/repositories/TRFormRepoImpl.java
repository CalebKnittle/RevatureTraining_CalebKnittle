package dev.knittle.repositories;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.text.Normalizer.Form;
import java.util.ArrayList;
import java.util.List;

//import dev.knittle.entities.Event;
import dev.knittle.entities.TRForm;
import dev.knittle.utilities.JDBCConnection;

public class TRFormRepoImpl implements TRFormRepo {
	
	public static Connection conn = JDBCConnection.getConnection();

	@Override
	public TRForm createTRForm(TRForm form) {

		try {

			String sql = "CALL add_tr_form(?, ?, ?, ?)";
			CallableStatement cs = conn.prepareCall(sql);

			cs.setString(1, Integer.toString(form.getEmplID()));
			cs.setString(2, Integer.toString(form.getEventID()));
			cs.setString(3, form.getJustification());
			cs.setString(4, Integer.toString(form.getActionEmplID()));
					

			cs.execute();
			return form;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public TRForm getTRFormByID(int formID) {

		try {

			String sql = "SELECT * FROM tr_form WHERE form_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, Integer.toString(formID));
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				TRForm tempForm = new TRForm();
				tempForm.setFormID(formID);
				//Main Form
				tempForm.setEmplID((rs.getInt("EMPL_ID")));
				tempForm.setEventID(rs.getInt("EVENT_ID"));
				tempForm.setActionEmplID((rs.getInt("ACTION_EMPL_ID")));
				tempForm.setStatusCode((rs.getInt("STATUS_CODE")));
				tempForm.setJustification((rs.getString("JUSTIFICATION")));
				//Supplement
				//Call update function here tempForm = this.supplementMeth(tempForm), move these to that method
				//tempForm = this.getTRFormSupplement(tempForm);
				
				/*tempForm.setEventAttachments((rs.getBytes("EVENT_ATTACHMENTS")));
				tempForm.setPreApproval((rs.getBytes("DESCRIPTION")));
				tempForm.setApprovalType(rs.getString("PRIOR_APPROVAL_TYPE"));
				tempForm.setTimeMissed((rs.getString("WORK_TIME_MISSED")));*/
				
				return tempForm;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return null;
	}
	
	@Override
	public TRForm getTRFormSupplement(TRForm tempForm) {

		try {

			String sql = "SELECT * FROM tr_form_supplement WHERE form_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, Integer.toString(tempForm.getFormID()));
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				tempForm.setEventAttachments((rs.getBytes("EVENT_ATTACHMENTS")));
				tempForm.setPreApproval((rs.getBytes("PRIOR_APPROVAL")));
				tempForm.setApprovalType(rs.getString("PRIOR_APPROVAL_TYPE"));
				tempForm.setTimeMissed((rs.getString("WORK_TIME_MISSED")));
				
				return tempForm;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public List<TRForm> getTRFormsPendingAction(int emplID) {

		try {

			String sql = "SELECT * FROM tr_form WHERE action_empl_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, Integer.toString(emplID));
			ResultSet rs = ps.executeQuery();
			
			List<TRForm> pendingActionForms = new ArrayList<TRForm>();

			while (rs.next()) {

				TRForm tempForm = new TRForm();
				//Main Form
				tempForm.setFormID(rs.getInt("FORM_ID"));
				tempForm.setEmplID((rs.getInt("EMPL_ID")));
				tempForm.setEventID(rs.getInt("EVENT_ID"));
				tempForm.setActionEmplID((rs.getInt("ACTION_EMPL_ID")));
				tempForm.setStatusCode((rs.getInt("STATUS_CODE")));
				tempForm.setJustification((rs.getString("JUSTIFICATION")));
				//Supplement
				//tempForm = this.getTRFormSupplement(tempForm);
				
				pendingActionForms.add(tempForm);
				
			}
			return pendingActionForms;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<TRForm> getTRFormsForEmployee(int emplID) {
		
		try {

			String sql = "SELECT * FROM tr_form WHERE empl_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, Integer.toString(emplID));
			ResultSet rs = ps.executeQuery();
			
			List<TRForm> pendingActionForms = new ArrayList<TRForm>();

			while (rs.next()) {

				TRForm tempForm = new TRForm();
				//Main Form
				tempForm.setFormID(rs.getInt("FORM_ID"));
				tempForm.setEmplID((rs.getInt("EMPL_ID")));
				tempForm.setEventID(rs.getInt("EVENT_ID"));
				tempForm.setActionEmplID((rs.getInt("ACTION_EMPL_ID")));
				tempForm.setStatusCode((rs.getInt("STATUS_CODE")));
				tempForm.setJustification((rs.getString("JUSTIFICATION")));
				//Supplement
				//tempForm = this.getTRFormSupplement(tempForm);
				
				pendingActionForms.add(tempForm);
				
			}
			return pendingActionForms;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<TRForm> getAllTRForms() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TRForm updateTRForm(TRForm form) {

		try {

			String sql = "UPDATE tr_form SET empl_id = ?, event_id = ?, action_empl_id = ?,"
					+ " status_code = ?, justification = ? WHERE form_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);

			//Main
			ps.setString(1, Integer.toString(form.getEmplID()));
			ps.setString(2, Integer.toString(form.getEventID()));
			ps.setString(3, Integer.toString(form.getActionEmplID()));
			ps.setString(4, Integer.toString(form.getStatusCode()));
			ps.setString(5, form.getJustification());
			ps.setString(6, Integer.toString(form.getFormID()));

			ps.executeQuery();
			
			//Supplement
			//form = this.updateTRFormSupplement(form);

			return this.getTRFormByID(form.getFormID());

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public TRForm updateTRFormSupplement(TRForm form) {
		
		try {

			String sql = "UPDATE tr_form_supplement SET event_attachments = ?, prior_approval = ?, prior_approval_type = ?,"
					+ " work_time_missed = ? WHERE form_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);

			//Main
			ps.setBytes(1, form.getEventAttachments());
			ps.setBytes(2, form.getPreApproval());
			ps.setString(3, form.getApprovalType());
			ps.setString(4, form.getTimeMissed());
			ps.setString(5, Integer.toString(form.getFormID()));

			ps.executeQuery();
			
			//Supplement
			//form = this.updateTRFormSupplement(form); //Circular Reference kept maxing out my cursors

			return this.getTRFormByID(form.getFormID());

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		return null;
	}

	@Override
	public TRForm deleteTRForm(int formID) {
		// TODO Auto-generated method stub
		return null;
	}

	

	

	

}
