package netdb.course.ss.lab.iodecorator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilterReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class Main 
{

	public static void main(String[] args) throws IOException 
	{
	
		// Enter file names
		BufferedReader keyReader = new BufferedReader(new InputStreamReader(System.in));
		//								decorator.            nuclear.
		String inputFile, outputFile;
		
		System.out.println("Please enter input file name:");
		inputFile = keyReader.readLine();
		
		System.out.println("Please enter output file name:");
		outputFile = keyReader.readLine();
		
		BufferedReader br;
		BufferedWriter bw;
		
		// TODO initialize the reader and writer here
		// remember to include your LowerCaseReader
		br = new BufferedReader(new LowerCaseReader(new FileReader(inputFile)));
		bw = new BufferedWriter(new FileWriter(outputFile));
		// TODO use a while loop to read the string from the input file
		// and then write to the output file
		
		String bufStr;
		while( (bufStr = br.readLine()) != null ) 
			{
				bw.write(bufStr + "\n");
			}
		
		// TODO remember to flush your writer
		// using flush() method
		bw.flush();
		// TODO close the reader and writer;
		if(br != null)
			br.close();
		if(bw != null)
			bw.close();
	}
	
}
