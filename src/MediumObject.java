
public class MediumObject extends AnyObject{

	private SmallObject smallReference; 
	private MediumObject mediumReference;
	private String name;
	private boolean marked;
	
	public MediumObject()
	{
		smallReference = null;
		mediumReference = null;
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
	
	public void setMediumReference(MediumObject reference)
	{
		mediumReference = reference;
	}
	
	public MediumObject getMediumReference()
	{
		return mediumReference;
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
		if(smallReference != null && mediumReference != null)
		{
			info = "Name: " + name + ". Medium Reference: "
					+ mediumReference.getName() + ". Small Reference: " + smallReference.getName() + ".";
		}
		else if(smallReference == null && mediumReference != null)
		{
			info = "Name: " + name + ". Medium Reference: "
					+ mediumReference.getName() + ". Small Reference: N/A.";
		}
		else if(smallReference != null && mediumReference == null)
		{
			info = "Name: " + name + ". Medium Reference: N/A. Small Reference: " + smallReference.getName() + ".";
		}
		else
		{
			info = "Name: " + name + ". Medium Reference: N/A. Small Reference: N/A.";
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

}
