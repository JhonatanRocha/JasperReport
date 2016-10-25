package com.jasperreport.report.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jasperreport.report.ConnectionFactory;
import com.jasperreport.report.generator.ReportGenerator;


public class ReportServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String fileName = request.getSession().getServletContext().getRealPath("/jasper/gasto_por_mes.jasper");
			Map<String, Object> parameters = new HashMap<String, Object>();
			Connection connection = new ConnectionFactory().getConnection();
		
			String data_ini = request.getParameter("data_ini");
			String data_fim = request.getParameter("data_fim");
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			
			Date initialDate = dateFormat.parse(data_ini);
			Date EndDate = dateFormat.parse(data_fim);
			
			parameters.put("DATA_INI", initialDate);
			parameters.put("DATA_FIM", EndDate);
			
			ReportGenerator generator = new ReportGenerator(fileName, parameters, connection);
			
			generator.generatePDFForOutputStream(response.getOutputStream());
			
			connection.close();

		} catch (SQLException | ParseException e ) {
			throw new ServletException(e);
		}
	}

}
