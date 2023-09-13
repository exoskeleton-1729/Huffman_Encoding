import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class HuffmanEncoder {
	
	private HashMap<Character, String> encodes = new HashMap<Character, String> ();
	
	public HuffmanEncoder(String codeFile) throws FileNotFoundException
	{
		File file = new File(codeFile);
		Scanner s = new Scanner(file);
		int lineNumber = 0;
		while(s.hasNextLine())
		{
			encodes.put((char)lineNumber, s.nextLine());
			lineNumber++;
		}
		s.close();
	}
	
	public String encodeChar(char input)
	{
		return encodes.get(input);
	}
	
	public void encodeLong(String fileToCompress, String encodedFile) throws IOException
	{
		PrintWriter out = new PrintWriter(new FileWriter(encodedFile));
		BufferedReader reader = new BufferedReader(new FileReader(fileToCompress));
		
		while(reader.ready())
		{
			out.write(encodeChar((char)reader.read()));
		}
		
		reader.close();
		out.close();
	}
	
	public void encodeFile(String fileToCompress) throws IOException
	{
		PrintWriter out = new PrintWriter(new FileWriter(fileToCompress + ".huf"));
		encodeLong(fileToCompress, "encoded.txt");
		BufferedReader reader = new BufferedReader(new FileReader("encoded.txt"));
		StringBuilder sb = new StringBuilder("");
		int toAdd = 0;
		
		
		while(reader.ready())
		{
			if(sb.length() < 8)
			{
				sb.append((char)reader.read());
				System.out.println(sb.toString());
			}
			else
			{
				System.out.println(sb.toString());
				System.out.println(Integer.parseInt(sb.toString(), 2));
				System.out.println((char)Integer.parseInt(sb.toString(), 2));
				out.write((char)Integer.parseInt(sb.toString(), 2));
				sb.setLength(0);
			}
		}
		
		while(sb.length() < 8 && sb.length() != 0)
		{
			sb.append(0);
			toAdd++;
		}
		
		out.write((char)Integer.parseInt(sb.toString(), 2));
		out.write((char)toAdd);
		reader.close();
		out.close();
	}
	
}
