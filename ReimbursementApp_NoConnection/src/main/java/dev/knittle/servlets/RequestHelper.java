package dev.knittle.servlets;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dev.knittle.controllers.EmployeeControllerImpl;

//For Reimbursement App
public class RequestHelper {

	public static void process(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String uri = request.getRequestURI();
		
		System.out.println(uri);
		
		switch(uri) {
		case "/ReimbursementApp/login.do": {
			System.out.println("Attempting Login");
			EmployeeControllerImpl.login(request, response);
			break;
		}
		case "/ReimbursementApp/getEmployee.do": {
			System.out.println("\nRetrieving Employee Info");
			EmployeeControllerImpl.getEmployee(request, response);
			break;
		}
		case "/ReimbursementApp/logout.do": {
			System.out.println("\nLogging Out...");
			EmployeeControllerImpl.logout(request, response);
			break;
		}
		case "/ReimbursementApp/submitForm.do": {
			System.out.println("\nSubmitting Form...");
			EmployeeControllerImpl.submitForm(request, response);
			break;
		}
		case "/ReimbursementApp/getPendingForms.do": {
			System.out.println("\nRetrieving forms needing action");
			EmployeeControllerImpl.getActionableForms(request, response);
			break;
		}
		case "/ReimbursementApp/approve.do": {
			System.out.println("\nForm Approved!");
			EmployeeControllerImpl.approveForm(request, response);
			break;
		}
		case "/ReimbursementApp/deny.do": {
			System.out.println("\nForm Denied.");
			EmployeeControllerImpl.denyForm(request, response);
		}
		default: {
			System.out.println("Something went wrong...");
			response.sendError(404, "Custom - Cannot find resource.");
			break;
		}
		
		}
	}
}
