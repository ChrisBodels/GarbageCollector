
public class SmallObject extends AnyObject {
	
	private SmallObject smallReference;
	private String name;
	private boolean marked;
	
	public SmallObject()
	{
		smallReference = null;
		marked = false;
	}
	
	public void setMarked(boolean marked)
	{
		this.marked = marked;
	}
	
	public boolean getMarked()
	{
		return marked;
	}
	
	public void setSmallReference(SmallObject reference)
	{
		smallReference = reference;
	}
	
	public SmallObject getSmallReference()
	{
		return smallReference;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String toString()
	{
		String info = "";
		if(smallReference != null)
		{
			info = "Name: " + name + ". Small Reference: " + smallReference.getName() + ".";
		}
		else
		{
			info = "Name: " + name + ". Small Reference: N/A.";
		}
		return info;
	}
	
	public void setLargeReference(LargeObject l)
	{
		
	}
	
	public LargeObject getLargeReference()
	{
		return null;
	}
	
	public void setMediumReference(MediumObject m)
	{
		
	}
	
	public MediumObject getMediumReference()
	{
		return null;
	}

}
