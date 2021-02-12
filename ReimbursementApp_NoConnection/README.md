# Silver Ridge Farms Inc. - Tuition Reimbursement Management System (TRMS)
### Author: Caleb Knittle

The purpose of this application is to provide a streamlined workflow management system for employees of Silver Ridge Farms Inc to apply for and receive financial aid towards continuing education. An employee begins the process by submitting a reimbursment request form that includes the details of an event they would like to attend and for which they would like to be reimbursed. A justification for why the event is relevant to their work within the company should also be included.
	The form then follows a chain of approvals. First, the employee's supervisor must approve the reimbursement request, followed by the department head of the employee's
department. Then, company's Benefits Coordinator must approve the request, at which point the employee may submit a final grade for the event. If the grade is in the format of a presentation, their supervisor must approve the grade, and if it is in the form of a standard letter grade (A-F) on a 100-point scale, the Benefits Coordinator must approve the grade. Once the grade is approved, the company awards the employee the appropriate amount based on the cost of the event.
	Also of note, an approver may request more information from the employee or any previous approver in the chain regarding the event. Additionally, an employee is limited to receiving up to $1000 USD in total reimbursements within the span of one calendar year (twelve months), although the Benefits Coordinator may allow reimbursements totaling greater than this amount if deemed appropriate.

Package Structure:
* Entities
* Repositories
* Services
* Controllers
* Servlets
* Utilities

Features:
* Employee can login and logout
* Employee may submit initial reimbursement request form
* Supervisors, Department Heads, and the Benefits Coordinator (Approvers) may approve a reimbursement request (in order)
* Approvers may deny a reimbursement request
* A user may not navigate to employee pages if they are not logged in

Testing:
* A Cucumber test was performed to ensure that a user could successfully login to the system.
* Tests in Postman were performed to verify server-side functionality

Technologies Used:
* Maven Web App
* JDBC
* Servlets
* Selenium (Includes Selenium Chrome Driver)
* Cucumber
* HTML files
* JavaScript

Setup: Run the project on a server at the localhost. Then in a browser, navigate to: http://localhost:8080/ReimbursementApp/



