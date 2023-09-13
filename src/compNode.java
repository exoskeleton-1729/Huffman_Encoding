import java.util.Comparator;

public class compNode implements Comparator<hNode> {
	
	public int compare(hNode n1, hNode n2)
	{
		return (n1.val - n2.val);
	}

}
