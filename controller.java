package Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.DAO;
import Model.DTO;
@WebServlet(urlPatterns = {"/register","/check","/deposit","/withdraw"})
public class controller extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url=req.getServletPath();
		
				
		if(url.equals("/check")) {
		DAO d1=new DAO();
		String id=req.getParameter("id");
		String pass=req.getParameter("password");
         if(!(d1.verify(id, pass))) {
        	 PrintWriter pw=resp.getWriter();
     		pw.write("Enter Correct Id Or Password"); 
         }else {
		double amt=d1.checkBalance(id,pass);
		PrintWriter pw=resp.getWriter();
		pw.write("Your Balance is"+ amt);
         }	
		}
		if(url.equals("/deposit")) {
			String id=req.getParameter("id");
			String pass=req.getParameter("password");
			DAO d4=new DAO();
			if(!(d4.verify(id, pass))) {
	        	 PrintWriter pw=resp.getWriter();
	     		pw.write("Enter Correct Id Or Password"); 
	         }else {
			double amt=Double.parseDouble(req.getParameter("amount"));
			if(amt<0) {
				 PrintWriter pw=resp.getWriter();
		     		pw.write("Enter Valid Amount"); 
			}else {
			
			 int u=d4.depositAmount(id,pass,amt);
			 if(u==1) {
				 PrintWriter pw=resp.getWriter();
					pw.write(amt+" Amount Deposited Successfully");
						 
			 }}}
		}
		if(url.equals("/withdraw")) {
			String id=req.getParameter("id");
			String pass=req.getParameter("password");
			
			DAO d4=new DAO();
			if(!(d4.verify(id, pass))) {
	        	 PrintWriter pw=resp.getWriter();
	     		pw.write("Enter Correct Id Or Password"); 
	         }else {
	        	 double amt=Double.parseDouble(req.getParameter("amount"));
	        	 if(amt<0) {
	        		 PrintWriter pw=resp.getWriter();
	 	     		pw.write("Enter Valid Amount");  
	        	 }
	        	 else {
			int a=d4.withDraw(id,pass,amt);
			if(a==1) {
				 PrintWriter pw=resp.getWriter();
					pw.write(amt+" Amount Withdraw Successfully");
					pw.write(d4.checkBalance(id, pass)+" Available ");
			}
			else if(a==2) {
				 PrintWriter pw=resp.getWriter();
					pw.write("Amount is not sufficient");
			}
	         }}
			
		}
		if(url.equals("/register")) {
		DAO d1=new DAO();
		String name=req.getParameter("uname");
		String contact_no=req.getParameter("contact");
		String email=req.getParameter("email");
		String password=req.getParameter("password");
		String conpassword=req.getParameter("cpassword");
		double amount=Double.parseDouble(req.getParameter("amount"));
		
		if(password.equals(conpassword)) {
			if(amount<0) {
				PrintWriter pw=resp.getWriter();
	     		pw.write("Enter Valid amount "); 
			}else {
			
		DTO d2=new DTO();
		d2.setUser_name(name);
		d2.setContact_no(contact_no);
		d2.setEmail(email);
		d2.setPassword(password);
		d2.setAmount(amount);
		int b=d1.register(d2);
		if(b==1) {
		RequestDispatcher rd=req.getRequestDispatcher("Index.html");
		rd.forward(req, resp);
		}else if(b==2) {
			PrintWriter pw=resp.getWriter();
			pw.write("You Are Already Registered ! Please Login");
		}}
		}else {
			PrintWriter pw=resp.getWriter();
			pw.write("Password and Confirmed Password Not Equal");
			
		}
		}
		}
	
	}


