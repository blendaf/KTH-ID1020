public class LinkedList<E extends Comparable<E>>  {

    private Node<E> first;
    int length = 0;


    //constructor
    public LinkedList(){
        first = null;
     }


    //add node to first place in list
    public void add ( E value ) {
        Node<E> node = new Node (value);
        node.giveNext(first);
        first = node;
        length++;
    }

    //create list from array
    public void listFromArray (E []arr){
        for(int i = 0; i < arr.length; i++) {
            add(arr[i]);
        }
    }

    //remove first node in list
    public Node remove () {
        Node var = first;
        if(first == null){
            return null;
        }else{
        first = first.getNext();
        length--;
        return var;
        }
    }

    //return first node in linked list
    public Node<E> getFirst(){
        return this.first;
    }


    //return node at specific index
    public Node getNode(int index){

        if(index > length){

            return null;
        }else{
        Node currentNode = first;
        for (int i = 0; i <= index; i++){
              currentNode = currentNode.getNext();
        }
        return currentNode;
        }
    }

    //check if first node
    public boolean checkIfFirst(Node<E> node){
        return first == node;
    }


    //swap two nodes
    public void swap ( Node current, Node previous ) {

          Node temp = new Node(null, current);

          if (checkIfFirst(current))  {

              first = current.getNext();
              current.giveNext(first.getNext());
              first.giveNext(temp.getNext());

          } else {

              previous.giveNext(current.getNext());
              current.giveNext(previous.getNext().getNext());
              previous.getNext().giveNext(temp.getNext());
          }
    }



    //print entire list
    public void printList () {

        Node currentNode = first;

        while (currentNode != null) {
            currentNode.printNode();
            currentNode = currentNode.getNext();
        }
        System.out.println("");
    }


    //print node at specific index
    public void printNode(int index) {
        if(index > length){
            System.out.println("Index out of bounds");
        }else{
            Node currentNode = first;
            for(int i = 0; i <= index; i++){
                currentNode = currentNode.getNext();
            }
            currentNode.printNode();
        }
    }

    //print node
    public void printNode (Node n) {
        System.out.print("The node is: ");
        n.printNode();
    }


    //return size of list
    public int getSize (){
        return length;
    }



    /************create list obj. from array ******/
    public static <T extends Comparable<T>> LinkedList listFromArr (T []arr ) {

        LinkedList<T> list = new LinkedList<T>();

        for (int i = 0; i < arr.length ; i++) {
            list.add(arr[i]);
        }
        return list;
    }
    /**********************************************/
}
