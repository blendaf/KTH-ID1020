import java.lang.String;
import java.util.Map;

public class Trie {

    private int size;
    private Node root;
    private int R = 26+1;


    public class Node {
        public Node[] next = new Node[R];
        public Node parent;
        String key;
        public int value;
        public int instances;
    }


    public void PrintNode(Node n) {
        System.out.println("Value of node is: " + n.value);
        System.out.println("key is: " + n.key);

    }

    public int charToIndex(char c) {
        if(c < 'a' || c > 'z'){
            return R - 1;
        }
        else {return c - 'a';}
    }



    public void Put(String key) {

        for (int i = 0; i < key.length(); i++) {
            if( 'a' > key.charAt(i) && key.charAt(i) > 'z')  {return;}
        }
        root = Put(root, key, 0, root);
    }

    private Node Put(Node n, String key, int level, Node parent) {

        if(n == null) {
            n = new Node();
            if (level != 0) {n.value = 0;}
            n.parent = parent;
            n.key = key.substring(0, level);
        }

        n.instances += 1;
        if( level == key.length()) {
            n.value += 1;
            return n;
        }
        char c = key.charAt(level);
        n.next[charToIndex(c)] = Put(n.next[charToIndex(c)], key, level+1, n);
        return n;
    }



    public int Get(String key) {
        int get = Get(root, key, 0);
        System.out.println(get);
        return get;
    }

    private int Get(Node n, String key, int level) {
        int value = 0;
        if(n == null) { return 0;}
        if(level == key.length()) { return n.value;}
        else {
            char c = key.charAt(level);
            value = Get(n.next[charToIndex(c)], key, ++level);
            return value;
        }
    }



    public Node GetNode(String key) {
        Node n = GetNode(root, key, 0);
        //if(n != null){PrintNodeValue(n);}
        //else{System.out.println("Node does not exist!");}
        return n;
    }

    private Node GetNode(Node n, String key, int level) {

        if(n == null) { return null;}
        if(level == key.length()) {return n;}
        char c = key.charAt(level);
        return GetNode(n.next[charToIndex(c)], key, ++level);
    }



    public int Count(String key) {
        int count = Count(GetNode(key));
        return count;
    }

    private int Count(Node n) {

        int count = 0;
        if (n == null) {return 0;}
        count += n.value;
        for (char c = 0; c < R; c++) {
            count += Count(n.next[c]);
        }
        return count;
    }


    public int Distinct(String key) {
        int distinct = Distinct(GetNode(key));
        return distinct;
    }

    private int Distinct(Node n) {
        int distinct = 0;
        if (n == null) {return 0;}
        if (n.value != 0){
            distinct += 1;
        }
        for(char c = 0; c < R; c++) {
            distinct += Distinct(n.next[c]);
        }
        return distinct;
    }





    private class TrieEntry implements Map.Entry<String, Integer> {

        private final String key;
        private Integer value;

        public TrieEntry(String key, Integer value ) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return this.key;
        }
        public Integer getValue() {
            return this.value;
        }

        public Integer setValue(Integer value) {
            int old = this.value;
            this.value = value;
            return old;
        }
    }





    public java.util.Iterator<java.util.Map.Entry<String, Integer>> GetIterator(String key) {

        return new TrieIterator(key);
    }

    class TrieIterator implements java.util.Iterator<java.util.Map.Entry<String, Integer>> {

        String key;
        Node nextNode;
        Node currentRoot;

        public TrieIterator(String key) {
            currentRoot = GetNode(key);
            this.key = key;
            this.nextNode = this.currentRoot;


            while(this.nextNode.value == 0) {
                for(int i = 0; i < R; i++) {
                    if (this.nextNode.next[i] != null) {
                        this.nextNode = this.nextNode.next[i];
                        break;
                    }
                }
            }
        }

        public void remove() { }

        public boolean hasNext() {
            return nextNode != this.currentRoot;
        }

        public Map.Entry<String, Integer> next() {

            Node oldNode = this.nextNode;

            // Get index of oldNode
            int currentIndex = charToIndex(this.nextNode.key.charAt(this.nextNode.key.length()-1));
            boolean rising = true;
            boolean first = true;

            // Check if it is possible to go down
            while(nextNode.value == 0 || first) {
                first = false;

                for (int i = 0; i < R; i++) {
                    if(this.nextNode.next[i] != null) {
                        this.nextNode = this.nextNode.next[i];
                        rising = false;
                        break;
                    }
                }
            }


            // Only do if it is NOT possible to go down without going up
            while(rising && hasNext()) {
                currentIndex = charToIndex(this.nextNode.key.charAt(this.nextNode.key.length()-1));
                this.nextNode = this.nextNode.parent;

                for (int i = currentIndex + 1; i < R; i++) {
                    if (this.nextNode.next[i] != null) {
                        rising = false;
                        this.nextNode = this.nextNode.next[i];
                        if(this.nextNode.value != 0) {break;}
                        i = -1;
                    }
                }
            }
            // Return
            return new TrieEntry(oldNode.key, oldNode.value);
        }
    }




}


