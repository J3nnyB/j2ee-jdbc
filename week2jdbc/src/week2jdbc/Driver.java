/*
	Name: Jennifer Byrne
	Date: February 16, 2020
	Notes: ENTD481 Week 2 Assignment - JDBC Program which connects to mySQL database, which holds
		   the Employees sample database provided by mySQL. This program retrieves the first name,
		   last name, current salary, and current job title of each employee. 
*/
// Package statement
package week2jdbc;

// Imports the Java SQL library
import java.sql.*;

// Class declaration
public class Driver {
	
	// Main method
	public static void main(String[] args) {
		
		// Try statement
		try {
			// Establishes connection to my database
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/employees", "root", "Rhys1116!!");
			
			// Creates a statement object
			Statement myStmt = myConn.createStatement();
			
			// Executes a query from the connected database - this one takes the respective columns from 3 tables in the Employees
			// database and joins them using emp_no as a key and to_date as a parameter to retrieve current data
			ResultSet myRS = myStmt.executeQuery("select employees.first_name, employees.last_name, salaries.salary, titles.title " + 
					"from salaries " + 
					"join employees " + 
					"on salaries.emp_no = employees.emp_no " + 
					"join titles " + 
					"on employees.emp_no = titles.emp_no and salaries.to_date = titles.to_date " + 
					"where titles.to_date = '9999-01-01'");
			
			// Loop to print results of query
			while (myRS.next()) {
				System.out.printf("%-20s %-20s %9s %-20s%n", myRS.getString("employees.first_name"), myRS.getString("employees.last_name"), 
					myRS.getString("salaries.salary"), myRS.getString("titles.title")); 
			}
		}
		// Catch block for exceptions
		catch (Exception e){
			
			e.printStackTrace();

		}	
		
	}

}
