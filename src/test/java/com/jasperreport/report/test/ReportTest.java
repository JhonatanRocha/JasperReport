package com.jasperreport.report.test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.jasperreport.report.ConnectionFactory;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;


public class ReportTest {
	public static void main(String[] args) throws SQLException, JRException, FileNotFoundException {
		
		Connection connection = new ConnectionFactory().getConnection();
		
		//JasperCompileManager.compileReportToFile("gasto_por_mes.jrxml");
		//JasperCompileManager.compileReportToFile("gasto_por_mes_subreport1.jrxml");
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		
		JasperPrint jasperPrint = JasperFillManager.fillReport("gasto_por_mes.jasper", parameters, connection);
		
		JRPdfExporter jrPdfExporter = new JRPdfExporter();
		
		jrPdfExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		
		jrPdfExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(new FileOutputStream("gasto_por_mes.pdf")));

        jrPdfExporter.exportReport();
		
		
		connection.close();	
	}
}
