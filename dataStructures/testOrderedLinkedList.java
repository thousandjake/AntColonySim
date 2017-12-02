import dataStructures.OrderedLinkedList;

public class testOrderedLinkedList {
  public static void main(String[] args) {
    OrderedLinkedList testList = new OrderedLinkedList();
    testList.add("eleven");
    System.out.println("first add - "+testList.toString());
    testList.add("mike");
    System.out.println("second add - "+testList.toString());
    testList.add("dustin");
    System.out.println("third add - "+testList.toString());
    testList.add("lucas");
    System.out.println("fourth add - "+testList.toString());
    testList.add("will");
    System.out.println("last add - "+testList.toString());
    testList.remove("will");
    System.out.println("first remove - "+testList.toString());
    testList.remove("eleven");
    System.out.println("second remove - "+testList.toString());
    testList.add("max");
    System.out.println("last add - "+testList.toString());
    OrderedLinkedList testList2 = new OrderedLinkedList();
    testList2.remove("eleven");
    System.out.println("remove no element - "+testList2.toString());
  }
}
