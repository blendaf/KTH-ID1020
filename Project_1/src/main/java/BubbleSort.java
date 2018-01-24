import java.util.ArrayList;
import java.util.Collections;


public class BubbleSort {

    public static void bubblesort(ArrayList<DocumentAttributes> docAttr, Property prop, Direction dir) {

        boolean swapped = true;

        while(swapped) {
            swapped = false;
            for(int i = 0; i < (docAttr.size() - 1); i++) {
                if(docAttr.get(i).compareTo(docAttr.get(i+1), prop, dir ) > 0){
                    Collections.swap(docAttr, i, (i+1));
                    swapped = true;
                }
            }
        }
    }

}
