package netdb.course.ss.lab.iodecorator;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;

public class LowerCaseReader extends FilterReader
{
	protected LowerCaseReader(Reader in) 
	{
		super(in);
	}

	@Override
	public int read(char[] cbuf, int off, int len) throws IOException 
	{
		
		// TODO implement your component here
		// hint: 1. cbuf is a buffer array that is empty at the begining, 
		//       and will be passed to the root Reader and buffer some input characters.
		//       2. You can lower a char by calling Character.toLowerCase(char c)
		int results = in.read(cbuf, off, len);
		
		for(int i=0; i<cbuf.length ; i++)
			cbuf[i] = Character.toLowerCase(cbuf[i]);
		
		// TODO remember to return the correct result
		// hint: 1. you don't need to generate it by your self
		//       2. you cannot just return 0
		
		return results;
	}
	
}
