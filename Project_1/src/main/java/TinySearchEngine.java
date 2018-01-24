import se.kth.id1020.Driver;
import se.kth.id1020.TinySearchEngineBase;

import java.util.ArrayList;
import java.util.List;
import java.lang.*;

import se.kth.id1020.util.Attributes;
import se.kth.id1020.util.Document;
import se.kth.id1020.util.Word;


public class TinySearchEngine implements TinySearchEngineBase {
    int R = 26+1;
    ArrayList<WordAttr> [][] words = new ArrayList[R][R];

    public TinySearchEngine() {
        for(int i= 0; i < R; i++){
            for(int j = 0; j< R; j++) {
                words[i][j] = new ArrayList<WordAttr>();
            }
        }
    }

    public void insert(Word word, Attributes attr) {

        ArrayList<WordAttr> list = words[charToIndex(word.word.charAt(0))][secondIndex(word.word)];

        //search if word exists and get index where word is supposed to be.
        int[] exists = binarySearch(word.word, 0, list.size()); //TODO: words.length-1 or words.length
        int index = exists[1];

        list.add(index,new WordAttr(word,attr));

    }



    public List<Document> search(String input) {

        ArrayList<WordAttr> wordAt = new ArrayList<WordAttr>();
        ArrayList<WordAttr> list = new ArrayList<WordAttr>();
        ArrayList<Document> documents = new ArrayList<Document>();
        Query queries = new Query(input);


            for (int i = 0; i < queries.queries.size(); i++) {
                wordAt.addAll(searchQuery(queries.queries.get(i)));
            }

            if (queries.orderby) {
                System.out.println("Property is: " + queries.prop);
                ArrayList<DocumentAttributes> docAttr = new ArrayList<>();
                docAttr = DocumentAttributes.toDocumentAttributes(wordAt);
                BubbleSort.bubblesort(docAttr, queries.prop, queries.dir);

                for (int i = 0; i < docAttr.size(); i++) {
                    documents.add(docAttr.get(i).document);
                    //System.out.println("count: " + docAttr.get(i).count);
                    System.out.println("popularity: " + docAttr.get(i).maxPopularity);
                    //System.out.println("occurrence: " + docAttr.get(i).minOccurance);
                }

            } else {
                for (int i = 0; i < wordAt.size(); i++) {
                    documents.add(wordAt.get(i).attribute.document);
                }
                documents = deleteDuplicates(documents);
            }

    

        return documents;
    }


    public ArrayList<WordAttr> searchQuery(String query) {

        ArrayList<WordAttr> Documents = new ArrayList<WordAttr>();
        ArrayList<WordAttr> list = words[charToIndex(query.charAt(0))][secondIndex(query)];
        int [] exists;

        exists = binarySearch(query, 0, list.size());
        int index = exists[1];
        System.out.println("size of arralist: " + list.size());


        if(exists[0] != -1) {

            while( (index-1) >= 0 && list.get(index-1).word.word.equals(query) ) {
                index--;
            }

            while(list.get(index).word.word.equals(query) && index < list.size()) {
                Documents.add(list.get(index++));
            }
        }

        return Documents;
    }

    /****    code        ****/


    public int[] binarySearch(String key, int lo, int hi) {

        int[] exists = new int[2];
        if (hi <= lo) {
            exists[0] = -1;
            exists[1] =  hi;
            return exists;
        }

        int mid = lo + (hi - lo) / 2;
        if(mid == 0) {
            exists[1] = 0;
            return  exists;
        }

        int cmp = words[charToIndex(key.charAt(0))][secondIndex(key)].get(mid).word.word.compareTo(key);

        if      (cmp > 0) {return binarySearch(key, lo, mid);}
        else if (cmp < 0) {return binarySearch(key, mid+1, hi);}
        else              {
            exists[1] = mid;
            return exists;
        }
    }



    public int secondIndex(String s) {
        if(s.length() < 2) {
            return R-1;
        } else {return charToIndex(s.charAt(1));}
    }


    public int charToIndex(char c) {

        if ( 'A' < c &&  'Z' > c){ return c- 'A'; }
        else if('a' < c && 'z' > c ) {return c - 'a'; }
        else { return R-1; }
    }


    public ArrayList<Document> deleteDuplicates(ArrayList<Document> documents) {
        ArrayList<Document> newDocuments = new ArrayList<>();

        for(int i = 0; i < documents.size(); i++) {
            if(!newDocuments.contains(documents.get(i))) {
                newDocuments.add(documents.get(i));
            }
        }

        return newDocuments;
    }



    public static void main(String [] args) throws Exception {
        TinySearchEngineBase searchEngine = new TinySearchEngine();
        Driver.run(searchEngine);

    }

}

