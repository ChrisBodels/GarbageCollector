
public abstract class AnyObject {
	
	
	public AnyObject()
	{
		
	}
	
	public abstract boolean getMarked();
	
	public abstract void setMarked(boolean marked);
	
	public abstract SmallObject getSmallReference();
	
	public abstract void setSmallReference(SmallObject reference);
	
	public abstract void setMediumReference(MediumObject reference);
	
	public abstract MediumObject getMediumReference();
	
	public abstract void setLargeReference(LargeObject reference);

	public abstract LargeObject getLargeReference();

}
