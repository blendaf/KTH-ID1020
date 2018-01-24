import edu.princeton.cs.introcs.In;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

public class Driver_2 {


    public static void insertionSortMax(Map.Entry<String, Integer>[] array, Map.Entry<String, Integer> entry) {
        for(int i = 0; i < array.length; i++ ) {
            if(array[i] == null){
                array[i] = entry;
                break;
            }

            if(entry.getValue() > array[i].getValue()) {
                Map.Entry<String, Integer> temp = array[i];
                array[i] = entry;
                entry = temp;
            }
        }
    }

    public static void insertionSortMin(Map.Entry<String, Integer>[] array, Map.Entry<String, Integer> entry) {
        for(int i = 0; i < array.length; i++ ) {
            if(array[i] == null){
                array[i] = entry;
                break;
            }


            if(entry.getValue() < array[i].getValue()) {
                Map.Entry<String, Integer> temp = array[i];
                array[i] = entry;
                entry = temp;
            }
        }
    }




    public static void main(String[] args) {
        Trie t1 = new Trie();
        URL url = ClassLoader.getSystemResource("kap1.txt");

        if (url != null) {
            System.out.println("Reading from: " + url);
        } else {
            System.out.println("Couldn't find file: kap1.txt");
        }

        In input = new In(url);

        while (!input.isEmpty()) {
            String line = input.readLine().trim();
            String[] words = line.split("(\\. )|:|,|;|!|\\?|( - )|--|(\' )| ");
            String lastOfLine = words[words.length - 1];

            if (lastOfLine.endsWith(".")) {
                words[words.length - 1] = lastOfLine.substring(0, lastOfLine.length() - 1);
            }

            for (String word : words) {
                String word2 = word.replaceAll("”|“|\"|\\(|\\)", "");

                if (word2.isEmpty()) {
                    continue;
                }

                //System.out.println(word2);
                t1.Put(word2.toLowerCase());


            }
        }

        Map.Entry<String, Integer> entry;

 /******************** 1) ********************/

        Iterator<Map.Entry<String, Integer>> iterator = t1.GetIterator("");
        Map.Entry<String, Integer>[] maxEntries =  new Map.Entry[10];

        for(int i = 0; i < 10; i++) {
            insertionSortMax(maxEntries, iterator.next()); }

        while(iterator.hasNext()) {
            entry = iterator.next();
            if(entry.getValue() > maxEntries[9].getValue()) {
                insertionSortMax(maxEntries, entry);
            }
        }

        for(int i = 0; i < maxEntries.length; i++) {

            System.out.println((i+1) + ": '"+  maxEntries[i].getKey()
                   + "'"+ "\nFrequncy is : " + maxEntries[i].getValue());
        }



        System.out.println(" ");

/******************** 2) ********************/

        iterator = t1.GetIterator("");
        Map.Entry<String, Integer>[] minEntries =  new Map.Entry[10];

        for(int i = 0; i < 10; i++) {
            insertionSortMin(minEntries, iterator.next()); }


        while(iterator.hasNext()) {
            entry = iterator.next();
            if(entry.getValue() < minEntries[9].getValue()) {
                insertionSortMin(minEntries, entry);
            }
        }

        for(int i = 0; i < minEntries.length; i++) {
            System.out.println((i+1) + ": '" + minEntries[i].getKey() + "'" + "\nFrequency is: " + minEntries[i].getValue());
        }


/******************** 3) ********************/

        int highestFrequency = t1.Count("aa");
        int temp;
        String prefixWithHighestFrequency = "";


        for(char c = 'a'; c <= ('z'+1); c++) {
            for(char d = 'a'; d <= ('z'+1); d++) {
                temp = t1.Count("" + c + d);
                if(temp > highestFrequency) {
                    highestFrequency = temp;
                    prefixWithHighestFrequency = ("" + c + d);
                }
            }
        }

        System.out.println("The prefix that has the highest frequency is: " + prefixWithHighestFrequency);


/******************** 4) ********************/

        char mostWordsStartWith = 'a';
        int numberOfDistinctWords = t1.Distinct("a");

        for(char c = 'a'; c <= ('z' +1); c++) {
            temp = t1.Distinct("" + c);
            if (temp > numberOfDistinctWords) {
                numberOfDistinctWords = temp;
                mostWordsStartWith = c;
            }
        }

        System.out.println("Most words start with the letter: " + mostWordsStartWith);

    }
}


