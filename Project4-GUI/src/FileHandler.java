import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileHandler {
	private String surveyFile;
	private FileWriter fileOutput;
	private PrintWriter printWriter;
	
	public FileHandler() {
		File userFile = new File("survey_results.csv");
		try {
		userFile.createNewFile();
		fileOutput = new FileWriter(userFile);
		printWriter = new PrintWriter(fileOutput);
		printWriter.println("DateTime,FirstName,LastName,PhoneNumber,Email,Sex,Water,Meals,Wheat,Sugar,Dairy,Miles,Weight");
		} catch (IOException e) {
			}
				
	}
	
	public void writeResults(String surveyData) {
		printWriter.println(surveyData);
	}
}
