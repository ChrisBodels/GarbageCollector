
public class LargeObject extends AnyObject {
	
	private SmallObject smallReference;
	private MediumObject mediumReference;
	private LargeObject largeReference;
	private String name;
	private boolean marked;
	
	public LargeObject()
	{
		largeReference = null;
		mediumReference = null;
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
	
	public void setMediumReference(MediumObject reference)
	{
		mediumReference = reference;
	}
	
	public MediumObject getMediumReference()
	{
		return mediumReference;
	}
	
	public void setLargeReference(LargeObject reference)
	{
		largeReference = reference;
	}
	
	public LargeObject getLargeReference()
	{
		return largeReference;
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
		if(largeReference != null && mediumReference != null && smallReference != null)
		{
			info = "Name: " + name + ". Large Reference: " + largeReference.getName() + ". Medium Reference: "
				+ mediumReference.getName() + ". Small Reference: " + smallReference.getName() + ".";
		}
		else if(largeReference == null && mediumReference != null && smallReference != null)
		{
			info = "Name: " + name + ". Large Reference: N/A. Medium Reference: "
					+ mediumReference.getName() + ". Small Reference: " + smallReference.getName() + ".";
		}
		else if(largeReference == null && mediumReference == null && smallReference != null)
		{
			info = "Name: " + name + ". Large Reference: N/A. Medium Reference: N/A. Small Reference: " + smallReference.getName() + ".";
		}
		else if(largeReference == null && mediumReference != null && smallReference == null)
		{
			info = "Name: " + name + ". Large Reference: N/A. Medium Reference: "
					+ mediumReference.getName() + ". Small Reference: N/A.";
		}
		else if(largeReference != null && mediumReference == null && smallReference == null)
		{
			info = "Name: " + name + ". Large Reference: " + largeReference.getName() + ". Medium Reference: N/A. Small Reference: N/A.";
		}
		else if(largeReference != null && mediumReference == null && smallReference != null)
		{
			info = "Name: " + name + ". Large Reference: " + largeReference.getName() + ". Medium Reference: N/A"
					+ ". Small Reference: " + smallReference.getName() + ".";
		}
		else if(largeReference != null && mediumReference != null && smallReference == null)
		{
			info = "Name: " + name + ". Large Reference: " + largeReference.getName() + ". Medium Reference: "
					+ mediumReference.getName() + ". Small Reference: N/A.";
		}
		else
		{
			info = "Name: " + name + ". Large Reference: N/A. Medium Reference: N/A. Small Reference: N/A.";
		}
		return info;
	}

}
