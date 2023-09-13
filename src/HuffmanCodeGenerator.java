import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
public class HuffmanCodeGenerator {
	
	private HashMap<Character, Integer> map = new HashMap<Character, Integer> ();
	private HashMap<Character, String> codes = new HashMap<Character, String> ();
	private PriorityQueue<hNode> hTree;
	String inputFileName;
	
	public HuffmanCodeGenerator(String inputFile) throws IOException
	{
		inputFileName = inputFile;
		for(int i = 0; i < 128; i++)
		{
			map.put((char)i, 0);
		}
		BufferedReader reader = new BufferedReader(new FileReader(inputFile));
		while(reader.ready())
		{
			char nextLetter = (char)reader.read();
			if(map.containsKey(nextLetter))
			{
				map.put(nextLetter, map.get(nextLetter) + 1);
			}
			else
			{
				map.put(nextLetter, 1);
			}
		}
		reader.close();
	}
	
	public int getFrequency(char c)
	{
		return map.get(c);
	}
	
	public void generateTree()
	{
		hTree = new PriorityQueue<hNode> (map.size(), new compNode());
		for(char c : map.keySet())
		{
			if(getFrequency(c) != 0)
			{
				hNode hn = new hNode();
				hn.c = c;
				hn.val = getFrequency(c);
				hn.left = null;
				hn.right = null;
				hTree.add(hn);
			}
		}
		
		hNode head = null;
		
		while(hTree.size() > 1)
		{
			hNode m1 = hTree.poll();
			hNode m2 = hTree.poll();
			hNode node = new hNode();
			node.val = m1.val + m2.val;
			node.left = m1;
			node.right = m2;
			head = node;
			hTree.add(node);
		}
		
		generateCode(head, "");
	}
	
	public void generateCode(hNode head, String str)
	{
		if(head.left == null && head.right == null && head != null)
		{
			codes.put(head.c, str);
		}
		else
		{
			generateCode(head.left, str + "0");
			generateCode(head.right, str + "1");
		}
	}
	
	public String getCode(char c)
	{
		generateTree();
		return codes.get(c);
	}
	
	public void makeCodeFile(String codeFile) throws IOException
	{
		PrintWriter out = new PrintWriter(new FileWriter(codeFile));
		
		for (int i = 0; i < 128; i++)
		{
			if(getFrequency((char)i) != 0)
			{
				
				out.println(getCode((char)i));
			}
			else
			{
				out.println();
			}
		}
		out.close();
	}
}