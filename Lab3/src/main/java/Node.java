class Node <E extends Comparable <E>> implements Comparable <Node<E>>
{

    private E cord;
    private Node<E> next;


    //constructor
    public Node (E c){
      this.cord = c;
      this.next=null;
    }


    //constructor for one single node
    public Node (E c, Node<E> node ) {
        this.cord = c;
        this.next=node;
    }


    public E getValue(){
        return this.cord;
    }

    public Node getNext () { return this.next; }

    public void giveNext(Node n){
        this.next = n;
    }

    public void printNode (){
        System.out.print(cord + "  ");
    }

    public int compareTo(Node<E> node) {
        return getValue().compareTo(node.getValue());
    }
}
