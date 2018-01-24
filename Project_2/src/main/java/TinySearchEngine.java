import se.kth.id1020.TinySearchEngineBase;
import se.kth.id1020.Driver;
import java.util.*;
import java.lang.*;
import se.kth.id1020.util.Attributes;
import se.kth.id1020.util.Document;
import se.kth.id1020.util.Word;
import se.kth.id1020.util.*;

public class TinySearchEngine implements TinySearchEngineBase {

    HashMap<String, LinkedList<Attributes>> wordAttrList = new HashMap<>();
    HashMap<Document, Integer> wordsInDocs = new HashMap<>();
    HashSet<Document> setOfDoc = new HashSet<Document>();
    HashMap<String, DocsWithArgument> cache = new HashMap<>();

    public void preInserts() {
    }

    public void insert(Sentence sentence, Attributes attributes) {

        for (Word word : sentence.getWords()) {

            if (wordAttrList.get(word.word) == null) {
                wordAttrList.put(word.word, new LinkedList<Attributes>());
            }
            wordAttrList.get(word.word).add(attributes);
        }
        setOfDoc.add(attributes.document);
        // add document in set, no duplicates
        wordsInDocs.put(attributes.document, sentence.getWords().size());
        //add the length of the sentence(number of words) to the corresponding document.
    }

    public void postInserts() {

    }

    public List<Document> search(String s) {
        Scanner sc;
        Query input = new Query(s);

        if (input.orderby) {
            String substring = s.substring(0, s.indexOf("orderby"));
            sc = new Scanner(substring);
            System.out.println(substring);
        } else {
            sc = new Scanner(s);
        }

        DocsWithArgument docsWithArgument = searchWithPrefix(sc);
        LinkedList<DocRelevance> docRelevanceList = docsWithArgument.docRelevanceList;
        LinkedList<Document> doneDocList = new LinkedList<>();


        if (input.orderby) {
            LinkedList<DocRelevance> sortedList = sort(docRelevanceList, input.prop, input.dir);
            for(DocRelevance docRelevance : sortedList) {
                doneDocList.add(docRelevance.document);
                //System.out.println(docRelevance.document.popularity);
                //System.out.println(docRelevance.relevance);

            }
        } else {
            for (DocRelevance docRelevance : docRelevanceList) {
                doneDocList.add(docRelevance.document);
            }
        }
        return doneDocList;
    }


    public DocsWithArgument searchWithPrefix (Scanner sc) {

        String current = sc.next();

        if (current.equals("-")) {
            return ListOperations.difference(searchWithPrefix(sc), searchWithPrefix(sc), cache);
        }
        else if (current.equals("+")) {
            return ListOperations.intersection(searchWithPrefix(sc), searchWithPrefix(sc), cache);
         }
        else if (current.equals("|")) {
            return ListOperations.union(searchWithPrefix(sc), searchWithPrefix(sc), cache);
         }
        else {
            if(cache.containsKey(current)) {
                return cache.get(current);
            }

            LinkedList<DocRelevance> docRelevancesList = Relevance.docRelevanceList(current, this);
            DocsWithArgument doneDocsWithArgument = new DocsWithArgument(docRelevancesList, current, current);
            cache.put(current, doneDocsWithArgument);
            return doneDocsWithArgument;
        }
    }


    public static LinkedList<DocRelevance> sort(LinkedList<DocRelevance> docRelevanceList, Property prop, Direction dir) {

        if(prop == Property.relevance ) {
            Collections.sort(docRelevanceList, (a, b) -> relSort(a, b));
        } else {
            Collections.sort(docRelevanceList, (a,b) -> popSort(a, b));
        }

        if(dir == Direction.ascending) {
            return docRelevanceList;
        } else {
            Collections.reverse(docRelevanceList);
            return docRelevanceList;
        }
    }

    public static int relSort(DocRelevance a, DocRelevance b) {
        if(a.relevance < b.relevance) {
            return -1;
        } else if (a.relevance > b.relevance) {
            return 1;
        } else {
            return 0;
        }
    }

    public static int popSort(DocRelevance a, DocRelevance b) {
        if(a.document.popularity < b.document.popularity) {
            return -1;
        } else if (a.document.popularity > b.document.popularity) {
            return 1;
        } else {
            return 0;
        }
    }
    public String infix(String s) {
        Scanner sc = new Scanner(s);
        return searchWithPrefix(sc).argument;
    }

    public static void main(String [] args) throws Exception {
        TinySearchEngineBase searchEngine = new TinySearchEngine();
        Driver.run(searchEngine);

    }

}


