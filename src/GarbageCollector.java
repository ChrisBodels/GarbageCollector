import java.util.Scanner;
import java.util.ArrayList;

/**
 * My demonstration of garbage collector using the mark and sweep algorithm.
 * A basic text-based user interface to simply demonstrate the mark and sweep algorithm used in garbage
 * collection by the Java virtual machine. 
 * A large object can have three references, one large, one medium and one small.
 * A medium object can have two references, one medium, and one small.
 *  A small object can have only one small reference.
 * In order for objects to not be collected by the garbage collector they must link back to the root object. 
 * There are three root objects, one for each size. The roots can only have one reference to an object of the same size as the root.
 * If an object cannot in some way link back to a root then it will be removed by the garbage collector when that method is called.
 * 
 * @author Chris Bodels
 * 
 * Last updated: 10th March 2016.
 *
 */
public class GarbageCollector {
	
	private Scanner input;
	private int largeObjects, mediumObjects, smallObjects;
	private ArrayList<LargeObject> large;
	private ArrayList<MediumObject> medium;
	private ArrayList<SmallObject> small;
	
	/**
	 * Constructor for objects of type GarbageCollector
	 */
	public GarbageCollector()
	{
		input = new Scanner(System.in);
		largeObjects = 1;
		mediumObjects = 1;
		smallObjects = 1;
		large = new ArrayList<LargeObject>();
		medium = new ArrayList<MediumObject>();
		small = new ArrayList<SmallObject>();
		large.add(new LargeObject());
		large.get(0).setName("LargeRoot");
		medium.add(new MediumObject());
		medium.get(0).setName("MediumRoot");
		small.add(new SmallObject());
		small.get(0).setName("SmallRoot");
	}
	
	public static void main(String args[])
	{
		GarbageCollector app = new GarbageCollector();
		app.runMenu();
	}
	
	/**
	 * This method displays the menu to the user and returns their choice as an int
	 * 
	 * @return
	 */
	public int displayMenu()
	{
		System.out.println("What would you like to do?");
		System.out.println("--------------------------------------------");
		System.out.println("1)	Make a large object");
		System.out.println("2)	Make a medium object");
		System.out.println("3)	Make a samll object");
		System.out.println("4)	Link objects");
		System.out.println("5)	Unlink objects");
		System.out.println("6)	Display objects and links");
		System.out.println("7)	Run garbage collection");
		System.out.println("0)	Exit");
		boolean notGoodInput = false;
		int option = 0;
		do
		{
			try {
			option = input.nextInt();
			notGoodInput = true;
			}
			catch(Exception e) {
				String throwOut = input.nextLine();
				System.out.println("Number expected, you entered text: " + throwOut);
			}
		}while(!notGoodInput);
		return option;
	}
	
	/**
	 * This method controls the menu. It calls the displayMenu method and uses the user's choice from that in a switch
	 * statement to call the relevant method based on their choice.
	 */
	public void runMenu()
	{
		int option = displayMenu();
		
		while(option != 0)
		{
			switch(option)
			{
				case 1:
					makeLargeObject();
					break;
				case 2:
					makeMediumObject();
					break;
				case 3:
					makeSmallObject();
					break;
				case 4:
					linkObjects();
					break;
				case 5:
					unlinkObjects();
					break;
				case 6:
					printAll();
					break;
				case 7:
					collectGarbage();
					break;
				default:
					System.out.println("Invalid option entered");
					break;
			}
			System.out.println("");
			System.out.println("");
			System.out.println("Press any key to continue...");
			input.next();
			System.out.println("");
			System.out.println("");
			System.out.println("");
			option = displayMenu();
		}
	}
	
	/**
	 * This method invokes the mark method on the three root objects so that all objects that can possibly
	 * link back to a root object will be marked. It then goes through the ArrayLists that hold the objects and
	 * removes any entries that are not marked. It then sets every object to unmarked again for the next time
	 * this method is called so that if a link is removed and an object is unreachable, it will not remain marked and
	 * never swept up by the garbage collector.
	 */
	public void collectGarbage()
	{
		mark(large.get(0));
		mark(medium.get(0));
		mark(small.get(0));
		
		for(int i = large.size()-1; i >= 0; i--)
		{
			if(!large.get(i).getMarked())
			{
				large.remove(i);	
			}
		}
		
		for(int i = medium.size()-1; i >= 0; i--)
		{
			if(!medium.get(i).getMarked())
			{
				medium.remove(i);
			}
		}
		
		for(int i = small.size()-1; i >= 0; i--)
		{
			if(!small.get(i).getMarked())
			{
				small.remove(i);
			}
		}
		
		for(int i = 0; i < large.size(); i++)
		{
			large.get(i).setMarked(false);
		}
		for(int i = 0; i < medium.size(); i++)
		{
			medium.get(i).setMarked(false);
		}
		for(int i = 0; i < small.size(); i++)
		{
			small.get(i).setMarked(false);
		}
	}
	
	/**
	 * This method marks all objects that it can reach so that they will not be removed by the garbage collector.
	 * It starts at the three root objects and then recursively calls itself on any references that they hold until
	 * no more can be found.
	 * 
	 * @param p - The object to be marked and any references it holds will be marked as well.
	 */
	public void mark(AnyObject p)
	{
		if(!p.getMarked())
		{
			p.setMarked(true);
			if(p.getLargeReference() != null)
			{
				AnyObject q = p.getLargeReference();
				mark(q);
			}
			if(p.getMediumReference() != null)
			{
				AnyObject q = p.getMediumReference();
				mark(q);
			}
			if(p.getSmallReference() != null)
			{
				AnyObject q = p.getSmallReference();
				mark(q);
			}
		}
	}
	
	public void makeLargeObject()
	{
		String largeName = "largeObject" + largeObjects;
		large.add(new LargeObject()); 
		large.get(large.size()-1).setName(largeName);
		largeObjects++;
	}
	
	public void makeMediumObject()
	{
		String mediumName = "mediumObject" + mediumObjects;
		medium.add(new MediumObject());
		medium.get(medium.size()-1).setName(mediumName);
		mediumObjects++;
	}
	
	public void makeSmallObject()
	{
		String smallName = "smallObject" + smallObjects;
		small.add(new SmallObject());
		small.get(small.size()-1).setName(smallName);
		smallObjects++;
	}
	
	public void linkObjects()
	{
		System.out.println("Choose which size of object you want to link another object to: ");
		System.out.println("1) Small");
		System.out.println("2) Medium");
		System.out.println("3) Large");
		int objectSize = input.nextInt();
		boolean correctInput = false;
		switch(objectSize)
		{
			case 1:
				
				correctInput = true;
				break;
			case 2:
				
				correctInput = true;
				break;
			case 3:
				
				correctInput = true;
				break;
			default:
				System.out.println("Invalid option chosen. Returning to main menu...");
				break;
			}
		if(correctInput)
		{
			System.out.println("Choose an object to link another object to (0 for root): ");
			if(objectSize == 1)
			{
				for(int i = 0; i < small.size(); i++)
				{
					System.out.println(i +") " + small.get(i).toString());
					
				}
				int objectNo1 = input.nextInt();
				if(objectNo1 == 0)
				{
					System.out.println("Choose a small object to link back to the root: ");
					for(int i = 0; i < small.size(); i++)
					{
						System.out.println(i +")" + small.get(i).toString());
					}
					int objectNo2 = input.nextInt();
					if(objectNo2 < small.size())
					{
						small.get(objectNo1).setSmallReference(small.get(objectNo2));
					}
					else
					{
						System.out.println("That object doesn't exist. Returning to main menu...");
					}
				}
				else if(objectNo1 < small.size())
				{
					System.out.println("Choose an object to link it to (must be of smaller or equal size)");
					for(int i =0; i < small.size(); i++)
					{
						System.out.println(i +")" + small.get(i).toString());
					}
					int objectNo2 = input.nextInt();
					if(objectNo2 < small.size())
					{
						small.get(objectNo1).setSmallReference(small.get(objectNo2));
					}
					else
					{
						System.out.println("That object doesn't exist. Returning to main menu...");
					}
				}
				else
				{
					System.out.println("That object doesn't exist. Returning to main menu...");
				}
			}
			if(objectSize == 2)
			{
				for(int i =0; i < medium.size(); i++)
				{
					System.out.println(i +")" + medium.get(i).toString());
				}
				int objectNo1 = input.nextInt();
				if(objectNo1 == 0)
				{
					System.out.println("Choose which medium object you want to link back to the root: ");
					for(int i =0; i < medium.size(); i++)
					{
						System.out.println(i +")" + medium.get(i).toString());
					}
					int objectNo2 = input.nextInt();
					if(objectNo2 < medium.size())
					{
						medium.get(objectNo1).setMediumReference(medium.get(objectNo2));
					}
					else
					{
						System.out.println("That object doesn't exist. Returning to main menu...");
					}
				}
				else if(objectNo1 < medium.size())
				{
					System.out.println("Choose which size of object you want to link this object to: ");
					System.out.println("1) Small");
					System.out.println("2) Medium");
					int objectSize2 = input.nextInt();
					switch(objectSize2)
					{
						case 1:
							System.out.println("Choose an object to link it to (must be of smaller or equal size)");
							for(int i =0; i < small.size(); i++)
							{
								System.out.println(i +")" + small.get(i).toString());
							}
							int objectNo2 = input.nextInt();
							if(objectNo2 < small.size())
							{
								medium.get(objectNo1).setSmallReference(small.get(objectNo2));
							}
							else
							{
								System.out.println("That object doesn't exist. Returning to main menu...");
							}
							break;
						case 2:
							System.out.println("Choose an object to link it to (must be of smaller or equal size)");
							for(int i =0; i < medium.size(); i++)
							{
								System.out.println(i +")" + medium.get(i).toString());
							}
							int objectNo2M = input.nextInt();
							if(objectNo2M < medium.size())
							{
								medium.get(objectNo1).setMediumReference(medium.get(objectNo2M));
							}
							else
							{
								System.out.println("That object doesn't exist. Returning to main menu...");
							}
							break;
						default:
							System.out.println("Invalid option chosen. Returning to main menu...");
							break;
					}
				}
				else
				{
					System.out.println("That object doesn't exist. Returning to main menu...");
				}
			}
			if(objectSize == 3)
			{
				for(int i = 0; i < large.size(); i++)
				{
					System.out.println(i +")" + large.get(i).toString());
				}
				int objectNo1 = input.nextInt();
				if(objectNo1 == 0)
				{
					System.out.println("Choose which large object you want to link back to the root: ");
					for(int i =0; i < large.size(); i++)
					{
						System.out.println(i +")" + large.get(i).toString());
					}
					int objectNo2 = input.nextInt();
					if(objectNo2 < large.size())
					{
						large.get(objectNo1).setLargeReference(large.get(objectNo2));
					}
					else
					{
						System.out.println("That object doesn't exist. Returning to main menu...");
					}
				}
				else if(objectNo1 < large.size())
				{
					System.out.println("Choose which size of object you want to link this object to: ");
					System.out.println("1) Small");
					System.out.println("2) Medium");
					System.out.println("3) Large");
					int objectSize2 = input.nextInt();
					switch(objectSize2)
					{
						case 1:
							System.out.println("Choose an object to link it to (must be of smaller or equal size)");
							for(int i = 0; i < small.size(); i++)
							{
								System.out.println(i +")" + small.get(i).toString());
							}
							int objectNo2 = input.nextInt();
							if(objectNo2 < small.size())
							{
								large.get(objectNo1).setSmallReference(small.get(objectNo2));
							}
							else
							{
								System.out.println("That object doesn't exist. Returning to main menu...");
							}
							break;
						case 2:
							System.out.println("Choose an object to link it to (must be of smaller or equal size)");
							for(int i =0; i < medium.size(); i++)
							{
								System.out.println(i +")" + medium.get(i).toString());
							}
							int objectNo2M = input.nextInt();
							if(objectNo2M < medium.size())
							{
								large.get(objectNo1).setMediumReference(medium.get(objectNo2M));
							}
							else
							{
								System.out.println("That object doesn't exist. Returning to main menu...");
							}
							break;
						case 3:
							System.out.println("Choose an object to link it to (must be of smaller or equal size)");
							for(int i =0; i < large.size(); i++)
							{
								System.out.println(i +")" + large.get(i).toString());
							}
							int objectNo2L = input.nextInt();
							if(objectNo2L < large.size())
							{
								large.get(objectNo1).setLargeReference(large.get(objectNo2L));
							}
							else
							{
								System.out.println("That object doesn't exist. Returning to main menu...");
							}
							break;
						default:
							System.out.println("Invalid option chosen. Returning to main menu...");
							break;
					}
				}
				else
				{
					System.out.println("That object doesn't exist. Returning to main menu...");
				}
			}
			
		}
	}
	
	public void unlinkObjects()
	{
		System.out.println("Choose which size object you would like to unlink a reference from (the one that holds the reference)");
		System.out.println("1) Small");
		System.out.println("2) Medium");
		System.out.println("3) Large");
		int objectSizeU = input.nextInt();
		switch(objectSizeU)
		{
			case 1:
				System.out.println("Choose which object you would like to remove the small reference from: ");
				for(int i = 0; i < small.size(); i++)
				{
					System.out.println(i +")" + small.get(i).toString());
				}
				int object = input.nextInt();
				if(object < small.size())
				{
					small.get(object).setSmallReference(null);
				}
				else
				{
					System.out.println("That object doesn't exist. Returning to main menu...");
				}
				break;
			case 2:
				System.out.println("Choose which object you would like to remove a reference from: ");
				for(int i = 0; i < medium.size(); i++)
				{
					System.out.println(i +")" + medium.get(i).toString());
				}
				int objectM = input.nextInt();
				if(objectM < medium.size())
				{
					System.out.println("Choose which reference you would like to remove: ");
					System.out.println("1) Small");
					System.out.println("2) Medium");
					int referenceSize = input.nextInt();
					if(referenceSize == 1)
					{
						medium.get(objectM).setSmallReference(null);
					}
					else if(referenceSize == 2)
					{
						medium.get(objectM).setMediumReference(null);
					}
					else
					{
						System.out.println("Invalid option entered. Returning to main menu...");
					}
				}
				else
				{
					System.out.println("That object doesn't exist. Returning to main menu...");
				}
				break;
			case 3:
				System.out.println("Choose which object you would like to remove a reference from: ");
				for(int i = 0; i < large.size(); i++)
				{
					System.out.println(i +")" + large.get(i).toString());
				}
				int objectL = input.nextInt();
				if(objectL < large.size())
				{
					System.out.println("Choose which reference you would like to remove: ");
					System.out.println("1) Small");
					System.out.println("2) Medium");
					System.out.println("3) Large");
					int referenceSize = input.nextInt();
					if(referenceSize == 1)
					{
						large.get(objectL).setSmallReference(null);
					}
					else if(referenceSize == 2)
					{
						large.get(objectL).setMediumReference(null);
					}
					else if(referenceSize == 3)
					{
						large.get(objectL).setLargeReference(null);
					}
					else
					{
						System.out.println("Invalid option entered. Returning to main menu...");
					}
				}
				else
				{
					System.out.println("That object doesn't exist. Returning to main menu...");
				}
				break;
			default:
				System.out.println("Invalid option chosen. Returning to main menu...");
				break;
		}
	}
	
	public void printAll()
	{
		System.out.println("Large objects:");
		for(int i = 0; i < large.size(); i++)
		{
			System.out.println("" + large.get(i).toString());
		}
		System.out.println("Medium objects:");
		for(int i = 0; i < medium.size(); i++)
		{
			System.out.println("" + medium.get(i).toString());
		}
		System.out.println("Small objects:");
		for(int i = 0; i < small.size(); i++)
		{
			System.out.println("" + small.get(i).toString());
		}
	}

}
