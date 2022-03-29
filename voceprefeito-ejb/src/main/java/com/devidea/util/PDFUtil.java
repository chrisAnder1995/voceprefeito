package com.devidea.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;

public class PDFUtil {
	public static final String JRXML_PATH = File.separator + "report" + File.separator + "jrxml";
	public static final String JASPER_PATH = File.separator + "report" + File.separator + "jasper";
	public static final String SOFTWARE_LOGO_PATH = File.separator + "report" + File.separator + "image" + File.separator + "logo_complete.png";
	public static final String DEVIDEA_LOGO_PATH = File.separator + "report" + File.separator + "image" + File.separator + "devidea.png";
	public static final String ATENDIMENTO_NAME = "atendimento";
	public static final String AUXILIO_LEGAL_NAME = "auxiliolegal";
	public static final String LISTAGEM_NAME = "listagem";
	
	public static byte[] generateJasperReportPDF(
			String reportLocation, String reportName, Map <String, Object> parameters, JRBeanCollectionDataSource dataSource) throws FileNotFoundException, JRException {
		
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		InputStream jrxmlInput = new FileInputStream(new File(reportLocation + File.separator + reportName + ".jrxml"));
		
		JasperDesign jasperDesign = JRXmlLoader.load(jrxmlInput);
		JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
		
		JRPdfExporter exporter = new JRPdfExporter();
		
		SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
		configuration.setCompressed(true);
		
		exporter.setConfiguration(configuration);
		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(byteArrayOutputStream));
		
		exporter.exportReport();
		
		return byteArrayOutputStream.toByteArray();
	}
}
