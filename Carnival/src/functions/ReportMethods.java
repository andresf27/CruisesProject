package functions;

import java.io.File;
import java.io.IOException;

public class ReportMethods {
	
	String evidencePath = "";
	String currentTime = "";
	String rutaOutput = "";

	
	public ReportMethods() throws IOException {
			
			File miDir = new File (".");
			rutaOutput= miDir.getCanonicalPath().replace("\\","\\\\");
			File exeptionFolder = new File(rutaOutput+"\\test-output\\");
			exeptionFolder.mkdirs();	
			evidencePath = rutaOutput+"\\test-output\\";
	}
	
	
	/**
	 * Retorna la ruta de Evidencias
	 * @return
	 */
	public String getevidencePath() {
		
		return evidencePath;
	}

	
	


	/**
	 * Crea un archivo HTML en blanco para que el reporte se pueda llenar, retorna la ruta de este archivo
	 * @param currentTime
	 * @return
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public String createReport() throws IOException, InterruptedException {
			
		
		//Creación de Reporte en Blanco
		File reportBlank = new File (rutaOutput+"\\test-output\\myExtentReport.html");				
		reportBlank.createNewFile();		
		return rutaOutput+"\\test-output\\myExtentReport.html";
		
		
	}

}
