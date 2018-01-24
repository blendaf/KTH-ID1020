import java.util.HashMap;
import java.util.LinkedList;

public class ListOperations {

    public static DocsWithArgument union(DocsWithArgument docsWithArgument_1, DocsWithArgument docsWithArgument_2, HashMap<String, DocsWithArgument> cache) {
        String newArgument = "(" + docsWithArgument_1.argument + " | " + docsWithArgument_2.argument +")";
        String newArgumentOrdered;
        if(docsWithArgument_1.argumentOrdered.compareTo(docsWithArgument_2.argumentOrdered) < 0) {
            newArgumentOrdered = "(" + docsWithArgument_1.argumentOrdered + " | " + docsWithArgument_2.argumentOrdered + ")";
        } else {
            newArgumentOrdered = "(" + docsWithArgument_2.argumentOrdered + " | " + docsWithArgument_1.argumentOrdered + ")";
        }

        //check cache
        if(cache.containsKey(newArgumentOrdered)) {
            return cache.get(newArgumentOrdered);

        } else {

            LinkedList<DocRelevance> newDocRelList = new LinkedList<>();
            LinkedList<DocRelevance> list_1 = docsWithArgument_1.docRelevanceList;
            LinkedList<DocRelevance> list_2 = docsWithArgument_2.docRelevanceList;

            // Copy list_1 to newDocRelList
            for (DocRelevance docRelevance : list_1) {
                newDocRelList.add(docRelevance);
            }

            for (DocRelevance docRelevance_2 : list_2) {
                boolean inBoth = false;
                for (DocRelevance newDocRelevance : newDocRelList) {
                    if (newDocRelevance.document.equals(docRelevance_2.document)) {
                        inBoth = true;
                        newDocRelevance.relevance = newDocRelevance.relevance + docRelevance_2.relevance;
                        break;
                    }
                }
                if (!inBoth) {
                    newDocRelList.add(docRelevance_2);
                }
            }

            DocsWithArgument doneDocsWithArgument = new DocsWithArgument(newDocRelList, newArgument, newArgumentOrdered);
            cache.put(newArgumentOrdered, doneDocsWithArgument);

            return doneDocsWithArgument;
        }
    }

    public static DocsWithArgument intersection(DocsWithArgument docsWithArgument_1, DocsWithArgument docsWithArgument_2, HashMap<String, DocsWithArgument> cache) {
        String newArgument = "(" + docsWithArgument_1.argument + " + " + docsWithArgument_2.argument +")";
        String newArgumentOrdered;
        if(docsWithArgument_1.argumentOrdered.compareTo(docsWithArgument_2.argumentOrdered) < 0) {
            newArgumentOrdered = "(" + docsWithArgument_1.argumentOrdered + " | " + docsWithArgument_2.argumentOrdered + ")";
        } else {
            newArgumentOrdered = "(" + docsWithArgument_2.argumentOrdered + " | " + docsWithArgument_1.argumentOrdered + ")";
        }

        if(cache.containsKey(newArgumentOrdered)) {
            return cache.get(newArgumentOrdered);
        }

        LinkedList<DocRelevance> newDocRelList = new LinkedList<>();
        LinkedList<DocRelevance> list_1 = docsWithArgument_1.docRelevanceList;
        LinkedList<DocRelevance> list_2 = docsWithArgument_2.docRelevanceList;

        for (DocRelevance docRelevance_1 : list_1) {
            boolean inBoth = false;
            double newRelevance = docRelevance_1.relevance;
            for (DocRelevance docRelevance_2 : list_2) {
                if (docRelevance_1.document.equals(docRelevance_2.document)) {
                    inBoth = true;
                    newRelevance += docRelevance_2.relevance;
                    break;
                }
            }
            if (inBoth) {
                DocRelevance newEntry = new DocRelevance(docRelevance_1.document, newRelevance);
                newDocRelList.add(newEntry);
            }
        }
        DocsWithArgument doneDocsWithArgument = new DocsWithArgument(newDocRelList, newArgument, newArgumentOrdered);
        cache.put(newArgumentOrdered, doneDocsWithArgument);

        return doneDocsWithArgument;
    }


    public static DocsWithArgument difference(DocsWithArgument docsWithArgument_1, DocsWithArgument docsWithArgument_2, HashMap<String, DocsWithArgument> cache) {
        String newArgument = "(" + docsWithArgument_1.argument + " - " + docsWithArgument_2.argument +")";


        if(cache.containsKey(newArgument)) {
            return cache.get(newArgument);
        } else {

            LinkedList<DocRelevance> newDocRelList = new LinkedList<>();
            LinkedList<DocRelevance> list_1 = docsWithArgument_1.docRelevanceList;
            LinkedList<DocRelevance> list_2 = docsWithArgument_2.docRelevanceList;


            for (DocRelevance docRelevance_1 : list_1) {
                boolean inBoth = false;
                for (DocRelevance docRelevance_2 : list_2) {
                    if (docRelevance_1.document.equals(docRelevance_2.document)) {
                        inBoth = true;
                        break;
                    }
                }
                if (!inBoth) {
                    newDocRelList.add(docRelevance_1);
                }
            }

            DocsWithArgument doneDocsWithArgument = new DocsWithArgument(newDocRelList, newArgument, newArgument);
            // Save list in cache, chache.put newdocrelist with key: newArgument
            cache.put(newArgument, doneDocsWithArgument);

            return doneDocsWithArgument;
        }
    }
}
