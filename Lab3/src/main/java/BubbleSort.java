public class BubbleSort {


    public static LinkedList sort(LinkedList list){

        Node current;
        Node previous;

        boolean swapped = true;
        int R = list.getSize()-2;

        while( R>= 0 && swapped){

            current = list.getFirst();
            previous = new Node(null, current);

            swapped = false;

            for(int i = 0; i <= R; i++){
                if(current.compareTo(current.getNext()) > 0){
                    swapped = true;

                    list.swap(current, previous);

                    if(!list.checkIfFirst(previous.getNext()) && previous.getValue()==null){
                        previous = list.getFirst();
                    } else {
                        previous = previous.getNext();
                    }
                } else {
                    previous = current;
                    current = current.getNext();
                }
            }
            R--;
        }
        return list;
    }




    public static int numberOfInversions (LinkedList list){

        int inversions = 0;
        Node start = list.getFirst();
        Node current = start;

        while (start.getNext() != null) {
            while(current != null) {
                if (start.compareTo(current) > 0) {
                    inversions++;
                }
                current = current.getNext();
            }
            start = start.getNext();
            current = start;
        }

        return inversions;
    }








    public static void main (String[] args){



        /******************* Character list *****************/

        LinkedList<Character> b = new LinkedList<Character>();
        Character[] array_b = {'g', 'f', 'h', 'k', 'j', 'i'};
        b.listFromArray(array_b);
        System.out.println("Unsorted list: ");
        b.printList();
        System.out.println("Number of inversions: " + numberOfInversions(b));
        sort(b);
        System.out.println("Sorted list: ");
        b.printList();



        /****************** Integer list ********************/

        Integer[] array_a = {3,4,6,5,9,1};
        LinkedList<Integer> a = new LinkedList<Integer>();
        a.listFromArray(array_a);
        System.out.println("Unsorted list");
        a.printList();
        System.out.println("Number of inversions: " + numberOfInversions(a));



        /******************* Create array from list ************/

        Node<Integer>current = a.getFirst();
        int[] array = new int[a.length];
        for (int i = 0; i < array.length; i++) {
            array[i] = current.getValue();
            current = current.getNext();
        }

        //print array
        for(int i = 0; i < a.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println(" ");

        /*******************************************************/


        //Print number of inversions from MergeSort
        System.out.println("Number of inversions(merge): " + MergeSort.sort(array));

        sort(a);
        System.out.println("Sorted list: ");
        a.printList();

        System.out.println("Sorted array: ");
        for(int i = 0; i < a.length; i++) {
            System.out.print(array[i] + "  ");
        }
    }


}
