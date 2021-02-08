package dev.knittle.controllers;

//import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import dev.knittle.entities.DataOutput;
import dev.knittle.entities.Employee;
import dev.knittle.entities.Event;
import dev.knittle.entities.FormInputs;
import dev.knittle.entities.Grade;
import dev.knittle.entities.TRForm;
import dev.knittle.services.EmployeeService;
import dev.knittle.services.EmployeeServiceImpl;
import dev.knittle.services.EventService;
import dev.knittle.services.EventServiceImpl;
import dev.knittle.services.TRFormService;
import dev.knittle.services.TRFormServiceImpl;

public class EmployeeControllerImpl {
	
	public static Gson gson = new Gson();
	//public static Gson gson2 = new Gson();
	public static EmployeeService es = new EmployeeServiceImpl();
	public static EventService evServ = new EventServiceImpl();
	public static TRFormService formServ = new TRFormServiceImpl();

	public static void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		//String username = request.getParameter("username");
		//String password = request.getParameter("password");
		
		Employee empl = gson.fromJson(request.getReader(), Employee.class);
		
		String username = empl.getUsername();
		String password = empl.getPassword();
		
		System.out.println("Username= " + username + " Password= " + password);
		
		empl = es.login(username, password);
		
		//May make a session object and see if that can pass the employee to the next page
		//Could also possibly set as a Context or Config Parameter
		//Maybe a forward?
		HttpSession sess = request.getSession();
		sess.setAttribute("loggedInEmployee", empl);
		System.out.println("Session ID (login): " + sess.getId());
		
		response.getWriter().append((empl != null) ? gson.toJson(empl) : gson.toJson(null)); //WORKS! "{}"
		
		//Testing
		if(empl == null) {
			sess.invalidate();
		}
		
//		RequestDispatcher rd = request.getRequestDispatcher("/ReimbursementApp/employeemenu.html");
//		try {
//			rd.forward(request, response);
//		} catch (ServletException | IOException e) {
//			e.printStackTrace();
//		}
		
	}
	
	public static void getEmployee (HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		HttpSession sess = request.getSession();
		System.out.println("Session ID (getEmployee): " + sess.getId());
		Employee empl = (Employee) sess.getAttribute("loggedInEmployee");
		
		response.getWriter().append((empl != null) ? gson.toJson(empl) : gson.toJson(null));
		if(empl == null) {
			sess.invalidate();
		}
		
	}
	
	public static void logout (HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		HttpSession sess = request.getSession();
		System.out.println("Session ID (logout): " + sess.getId());
		//sess.setAttribute("loggedInEmployee", null); //Not really necessary
		//empl = es.logout(empl) //Don't need
		sess.invalidate();
		
		//response.getWriter().append((empl != null) ? gson.toJson(empl) : "{}");		
	}
	
	public static void submitForm (HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		//System.out.println(request.getReader().readLine());
		//String input = request.getReader().readLine();
		//System.out.println(input);
		
		//Wants ALL inputs for process to succeed
		//BUT, allows null values, set input default values to null
		FormInputs formData = gson.fromJson(request.getReader(), FormInputs.class);
		
		System.out.println("getReader Results: " + formData);
		
		HttpSession sess = request.getSession();		
		Employee empl = (Employee) sess.getAttribute("loggedInEmployee");
		
		Event tempEvent = new Event();
		TRForm tempForm = new TRForm();
		Grade tempGrade = new Grade();
		
		//Event Info		
		tempEvent.setTypeID(formData.getEventType());
		tempEvent.setEventName(formData.getEventName());
		tempEvent.setEventDate(formData.getEventDate());
		tempEvent.setEventTime(formData.getEventTime());
		tempEvent.setLocation(formData.getLocation());
		tempEvent.setDescription(formData.getDescription());
		tempEvent.setCost(formData.getEventCost());
		tempEvent = evServ.createEvent(tempEvent); //Adds to Database
		
		//TR Form Info
		tempForm.setEmplID(empl.getEmplID());
		tempForm.setEventID(tempEvent.getEventID());
		tempForm.setJustification(formData.getJustification());
		tempForm.setStatusCode(10);
		tempForm = formServ.updateNextApprover(tempForm);
		tempForm = formServ.createNewForm(tempForm); //Adds to Database
		
		//Grade Info
		tempGrade.setFormatID(formData.getGradeType());
		tempGrade = formServ.getDefaultPassGrade(tempGrade);
		tempGrade = formServ.submitGrade(tempGrade); //Adds to Database	
		
	}
	
	public static void getActionableForms (HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		HttpSession sess = request.getSession();		
		Employee empl = (Employee) sess.getAttribute("loggedInEmployee");
		
		List<TRForm> pendingForms = formServ.viewActionableForms(empl);
		List<DataOutput> output = new ArrayList<DataOutput>();
		
		for(int i = 0; i < pendingForms.size(); i++) {
			
			DataOutput tempdo = new DataOutput();
			TRForm form = pendingForms.get(i);
			Employee tempEmpl = es.getEmplByID(form.getEmplID());
			Event event = evServ.getEventByID(form.getEventID());
			
			tempdo.setRequestor(tempEmpl.getFirstName() + " " + tempEmpl.getLastName());
			tempdo.setEventName(event.getEventName());
			tempdo.setEventDate(event.getEventDate());
			tempdo.setCost(event.getCost());
			tempdo.setId(form.getFormID());
			
			output.add(tempdo);		
			
		}
		
		response.getWriter().append((output != null) ? gson.toJson(output) : gson.toJson(null));
		
	}
	
	public static void approveForm (HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		
		TRForm tempForm = gson.fromJson(request.getReader(), TRForm.class);
		System.out.println("At approveForm (just parsed): " + tempForm);
				
		tempForm = formServ.getFormByID(tempForm.getFormID());
		tempForm = formServ.approve(tempForm);
			
		
		
	}
	
	public static void denyForm (HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		TRForm tempForm = gson.fromJson(request.getReader(), TRForm.class);
		System.out.println("At denyForm" + tempForm);
		
		tempForm = formServ.getFormByID(tempForm.getFormID());
		tempForm = formServ.deny(tempForm);
		
	}
	
}
