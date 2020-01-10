package com.revature.servlets;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.ApplyTemplate;

import com.revature.models.Reim;
import com.revature.models.ReimDTO;

import com.revature.services.ReimService;
//
//@WebServlet("/apply")
@MultipartConfig
public class ApplyServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(RegisterServlet.class);
	private static ObjectMapper om = new ObjectMapper();
	
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res ) throws ServletException, IOException{
		
		//System.out.println("this is req data =" + req);
		
		InputStream receiptToSend = null; // input stream of the upload file
    
   // obtains the upload file part in this multipart request
   Part filePart = req.getPart("receipt");
  // System.out.println("this is file that is send =" + filePart);
   if (filePart != null) {

	  // System.out.println("this says file part is not null");
       receiptToSend = filePart.getInputStream();
   }
   HttpSession session = req.getSession(false);
  
   byte[] receipt = IOUtils.toByteArray(receiptToSend);
		
		
		
		
		
		
//		
//		String file = req.getParameter("file");
//		System.out.println("file = " + file);
//		//System.out.println("just inside the post");
		
//		
//		System.out.println("this is request =" + req);
//		
//		BufferedReader reader = req.getReader();
//		
//		StringBuilder s = new StringBuilder();
//		String line = reader.readLine();
//		while(line != null) {
//			s.append(line);
//			line = reader.readLine();
//		}
//		//System.out.println("just before loop");
//		String body = s.toString();
//		System.out.println(body);
//		
//		
//		InputStream input = new FileInputStream(body);
		
		
		//Reader reader1 = new StringReader(body);
		
//		String newBody = body.replaceAll("\\s.{60}", "");
//		String sr ="------WebKitFormBoundarySForze5ITkJ2Q5S0Content-Disposition:";
//		System.out.println(sr.length());
//		
//		System.out.println("new bidy = "+newBody);
		//System.out.println("just reading the request");
		//om.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		//om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		//om.readValue( jsonString, Message.class );
		//om.configure(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		
		
		//ApplyTemplate registerAttempt = om.readValue(input, ApplyTemplate.class);
		
		
		//System.out.println(registerAttempt);
		
		
		double amount = Double.parseDouble(req.getParameter("amount"));
		String description = req.getParameter("description"); //registerAttempt.getDescription();
		
		
		//byte[] receipt1 = registerAttempt.getReceipt();
		
		int status_id = Integer.parseInt(req.getParameter("status_id"));//registerAttempt.getStatus_id();
		
		String type = req.getParameter("type_id");//registerAttempt.getType_id();
		
		int author_id = Integer.parseInt(req.getParameter("author_id"));//registerAttempt.getAuthor_id();
		//System.out.println(registerAttempt);
		int type_id = 0;
        switch(type){
        case "Housing": 
        type_id = 1;
        break;
        case "Travel":
            type_id = 2;
            break;
        case "Food":
            type_id = 3;
            break;
        case "Medicine":
            type_id = 5;
            break;
        case "Study":
            type_id = 4;
            break;
        case "Others":
            type_id = 6;
            break;
        default: 
        break;

        }

        
        
        
		
		// gets values of text fields
//        double amount = Double.parseDouble(req.getParameter("amount"));
//        String description = req.getParameter("description");
//         String type = req.getParameter("type");
        
        
//        
//
//        InputStream receiptToSend = null; // input stream of the upload file
////         
////        // obtains the upload file part in this multipart request
//        Part filePart = req.getPart("file");
//        if (filePart != null) {
//
//            receiptToSend = filePart.getInputStream();
//        }
//        HttpSession session = req.getSession(false);
//       
//        byte[] receipt = IOUtils.toByteArray(receiptToSend);
        
//        
//       
//        
        
        
        
        
       
        Reim r = new Reim(0, new Double(amount), new String(description), new Integer(author_id), 1, receipt, new Integer(status_id), new Integer(type_id));
        logger.info("User attempted to apply for reimbursement ");
		System.out.println("this is reim = " + r);
        Reim reim = ReimService.add(r);
		System.out.println(reim);
		if(reim != null) {

			PrintWriter out = res.getWriter();
			res.setContentType("application/json");
			ReimDTO rDTO = ReimService.convertToDTO(reim);
			
			out.println(om.writeValueAsString(rDTO));
			
			
			logger.info("successfully applied in");
		} else {
			System.out.println("reimbursement is not processed");
			res.setContentType("application/json");
			res.setStatus(204);
		}
        //res.sendRedirect("http://localhost:8080/rembursmentApp/welcome.html");
	}
}
