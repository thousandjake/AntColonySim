package dataStructures;

/**
 *	Class OrderedLinkedList.
 *
 *  This class functions as a linked list, but ensures items are stored in ascending order.
 *  Modified by Jake Thousand
 *  Last Modified on 10-31-2017
 */
public class OrderedLinkedList
{

	/**************************************************************************
	 * Constants
	 *************************************************************************/

	/** return value for unsuccessful searches */
	private static final OrderedListNode NOT_FOUND = null;


	/**************************************************************************
	 * Attributes
	 *************************************************************************/

	/** current number of items in list */
	private int theSize;

	/** reference to list header node */
	private OrderedListNode head;

	/** reference to list tail node */
	private OrderedListNode tail;

	/** current number of modifications to list */
	private int modCount;


	/**************************************************************************
	 * Constructors
	 *************************************************************************/


	/**
	 *	Create an instance of OrderedLinkedList.
	 *
	 */
	public OrderedLinkedList()
	{
		// empty this OrderedLinkedList
		clear();
	}


	/**************************************************************************
	 * Methods
	 *************************************************************************/	

	/*
	 *	Add the specified item to this OrderedLinkedList.
	 *
	 *	@param	obj		the item to be added
	 */
	public boolean add(Comparable obj)
	{
    //if the OrderedLinkedList is empty, add the new OrderedListNode between the head and tail nodes
    if(theSize == 0)
    {
      //create a new OrderedListNode containing the item to be added and references to the head and tail nodes
      OrderedListNode newItem = new OrderedListNode(obj, head, tail);
      //set head.next to the new OrderedListNode newItem
      head.next = newItem;
      //set tail.previous to the new OrderedListNode newItem
      tail.previous = newItem;
      //increase the size of the OrderedLinkedList
      theSize++;
      //increase the modCount of the OrderedLinkedList as a Node has been added
      modCount++;
      //return true as OrderedListNode was successfully added to OrderedLinkedList
      return true;
    }
    //if the OrderedLinkedList is not empty, the method must iterate through the list to find the correct position
    else
    {
      //iterate from the head node to the tail node of the OrderedLinkedList
      for(OrderedListNode p = head.next; p != tail; p = p.next)
      {
        //if obj <= OrderedListNode.theItem, insert new OrderedListNode at this position
        if(obj.compareTo(p.theItem) <= 0)
        {
          //create a new OrderedListNode containing the item to be added and references to the OrderedListNode p.previous and p
          OrderedListNode newItem = new OrderedListNode(obj, p.previous, p);
          //set next attribute of OrderedListNode p.previous to the new OrderedListNode newItem
          p.previous.next = newItem;
          //se previous attribute of OrderedListNode p to the new OrderedListNode newItem
          p.previous = newItem;
          //increase the size of the OrderedLinkedList
          theSize++;
          //increase the modCount of the OrderedLinkedList as a Node has been added
          modCount++;
          //return true as OrderedListNode was successfully added to OrderedLinkedList
          return true;
        }
      }
      //if no OrderedListNode.theItem > obj, insert obj at end of list, just before tail node
      //create a new OrderedListNode containing the item to be added and references to OrderedListNode tail.previous and tail
      OrderedListNode newItem = new OrderedListNode(obj, tail.previous, tail);
      //set next attribute of OrderedListNode tail.previous to the new OrderedListNode newItem
			tail.previous.next = newItem;
      //set previous attribute of OrderedListNode tail to the new OrderedListNode newItem
			tail.previous = newItem;
      //increase the size of the OrderedLinkedList
			theSize++;
      //increase the modCount of the OrderedLinkedList as a Node has been added
			modCount++;
      //return true as OrderedListNode was successfully added to OrderedLinkedList
      return true;
    }
  }

	/*
	 *	Remove the first occurrence of the specified item from this OrderedLinkedList.
	 *
	 *	@param	obj		the item to be removed
	 */
	public boolean remove(Comparable obj)
	{
    //throw exception if the OrderedLinkedList is empty
    if(theSize == 0)
    {
      throw new NoSuchElementException();
    }
    //if OrderedLinkedList is not empty, iterate through OrderedListNode until matching obj is found
    else
    {
      //iterate from head Node to tail Node of OrderedLinkedList
      for(OrderedListNode p = head.next; p != tail; p = p.next)
      {
        //if matching item is found, set Nodes to skip over it i.e. remove from OrderedLinkedList
        if(obj.compareTo(p.theItem) == 0)
        {
          //set next attribute of OrderedListNode p.previous to the OrderedListNode p.next
          p.previous.next = p.next;
          //set next attribute of OrderedListNode p to the OrderedListNode p.previous
          p.next = p.previous;
          //reduce the size of the OrderedLinkedList by one
          theSize--;
          //increase the modCount of the OrderedLinkedList as we have removed an OrderedListNode
          modCount++;
          //return true as OrderedListNode was successfully removed from OrderedLinkedList
          return true;
        }
      }
    }
    return false;
  }

	/**
	 *	Empty this OrderedLinkedList.
	 */
	public void clear()
	{
		// reset header node
		head = new OrderedListNode("HEAD", null, null);

		// reset tail node
    tail = new OrderedListNode("TAIL", head, null);

    // header references tail in an empty LinkedList
    head.next = tail;

    // reset size to 0
		theSize = 0;

		// emptying list counts as a modification
		modCount++;
	}

	/**
	 *	Return true if this OrderedLinkedList contains 0 items.
	 */
	public boolean isEmpty()
	{
		return theSize == 0;
	}

	/**
	 *	Return the number of items in this OrderedLinkedList.
	 */
	public int size()
	{
		return theSize;
	}

	/*
	 *	Return a String representation of this OrderedLinkedList.
	 *
	 *	(non-Javadoc)
   *	@see java.lang.Object#toString()
   */
    @Override
    public String toString()
    {
    	String s = "";

    	OrderedListNode currentNode = head.next;

    	while (currentNode != tail)
    	{
    		s += currentNode.theItem.toString();

    		if (currentNode.next != tail)
    		{
    			s += ", ";
    		}

    		currentNode = currentNode.next;
    	}

    	return s;
    }

	/**************************************************************************
	 * Inner Classes
	 *************************************************************************/

	/**
	 *	Nested class OrderedListNode.
	 *
	 *	Encapsulates the fundamental building block of an OrderedLinkedList
	 *	contains a data item, and references to both the next and previous nodes
	 *	in the list
	 */
    private static class OrderedListNode
    {

    /*************
     *	attributes
     ************/

      //the data item
      Object theItem;

      //reference to the next node in the list
      OrderedListNode next;

      //reference to the previous node in the list
      OrderedListNode previous;

     /**************
      *	constructor
      *************/

      public OrderedListNode(Comparable item, OrderedListNode previousNode, OrderedListNode nextNode)
      {
        theItem = item;
        previous = previousNode;
        next = nextNode;
      }
    
    }
}
