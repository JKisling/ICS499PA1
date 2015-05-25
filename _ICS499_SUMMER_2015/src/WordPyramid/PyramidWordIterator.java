package WordPyramid;

import core.BigWord;
import core.BigWordCollection;

public class PyramidWordIterator {
	private PyramidWordCollection pwc;
	private int currentIndex = 0;
	private int manyItems = 0;
	
	/**
	 * Constructor for holding the reference to a BigWordCollection.
	 * This collection will be navigated. 
	 * 
	 * @param some_pwc
	 */
	public PyramidWordIterator(PyramidWordCollection some_pwc)
	{
		pwc = some_pwc;
		currentIndex = 0;
		manyItems = some_pwc.size();
	}
	
	/**
	 * For getting the current element
	 * @return
	 */
	public PyramidWord getCurrent()
	{
		return pwc.getPyramidWord(currentIndex);
	}

	/**
	 * For getting the previous element
	 * @return
	 */
	public PyramidWord getPrevious()
	{
		if (currentIndex == 0)
		{
			System.out.println("There is no previous");
			return getCurrent();
		}
		currentIndex--;
		return getCurrent();
		
	}
	
	/**
	 * For getting the next element
	 * @return
	 */
	public PyramidWord getNext()
	{
		if (currentIndex == manyItems-1)
		{
			System.out.println("There is no next");
			return getCurrent();
		}
		currentIndex++;
		return getCurrent();
		
	}
	
	/**
	 * For moving the pointer to a previous element.
	 * If we are already at the first element, then do nothing
	 * @return
	 */
	public void previous()
	{
		if (currentIndex == 0)
		{
			System.out.println("There is no previous");
			return;
		}
		currentIndex--;		
	}
	
	/**
	 * For moving the pointer to a next element.
	 * If we are already at the last element, then do nothing
	 * @return
	 */
	public void next()
	{
		if (currentIndex == manyItems-1)
		{
			System.out.println("There is no next");
			return;
		}
		currentIndex++;	
	}
	
	/**
	 * For checking to see if there is a previous BigWord in the list
	 * @return false if currentIndex is the first index
	 */
	public boolean hasPrevious(){
		if (currentIndex == 0)
		{
			return false;
		}
		return true;
	}
	
	/**
	 * For checking to see if there is a next BigWord in the list
	 * @return false if currentIndex is the last index
	 */
	public boolean hasNext(){
		if (currentIndex == manyItems-1)
		{
			return false;
		}
		return true;
	}
	/**
	 * For moving the pointer to the start of the collection.
	 * @return
	 */
	public void start()
	{
		currentIndex = 0;
	}
	
	/**
	 * For moving the pointer to the end of the collection.
	 * @return
	 */
	public void end()
	{
		currentIndex = manyItems-1;
	}
	
}
