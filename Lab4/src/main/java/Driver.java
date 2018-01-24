import java.util.Map;
import java.util.Iterator;

public class Driver {

    public static void main(String [] args) {


        Trie t1 = new Trie();

        t1.Put("cat");
        t1.Put("cart");
        t1.Put("car");
        t1.Put("carten");
        t1.Put("cats");
        t1.Put("catys");
        t1.PrintNode(t1.GetNode("c"));
        t1.Get("c");
        t1.Get("g");
        t1.Count("ca");
        t1.Put("apa");
        t1.Put("apan");
        t1.Put("ap");
        t1.Put("apa");
        t1.Distinct("a");
        t1.Put("aporna");
        t1.Put("apornas");
        t1.Put("lol");
        t1.Put("loa");
        t1.Distinct("a");
        t1.GetNode("hee");
        t1.Put("b");
        Iterator<Map.Entry<String, Integer>> it = t1.GetIterator("c");
        Map.Entry<String, Integer> entry;

        while(it.hasNext()) {
            entry = it.next();
            System.out.println("ord: " + entry.getKey());
        }

        it = t1.GetIterator("b");
        entry = it.next();
        System.out.println (entry.getKey());
    }
}
