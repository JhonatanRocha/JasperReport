package com.jasperreport.report.generator;

import java.io.OutputStream;
import java.sql.Connection;
import java.util.Map;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

public class ReportGenerator {

	private String fileName;
	private Map<String, Object> params;
	private Connection connection;
	
	public ReportGenerator(String fileName, Map<String, Object> params, Connection connection) {
		this.fileName = fileName;
		this.params = params;
		this.connection = connection;
	}

	public void generatePDFForOutputStream(OutputStream outputStream) {
		try {
			JasperPrint jasperPrint = JasperFillManager.fillReport(this.fileName, this.params, this.connection);
			
			JRPdfExporter jrPdfExporter = new JRPdfExporter();
			
			jrPdfExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			
			jrPdfExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));

	        jrPdfExporter.exportReport();
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}