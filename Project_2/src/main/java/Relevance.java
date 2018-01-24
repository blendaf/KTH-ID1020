import java.util.LinkedList;
import se.kth.id1020.util.Attributes;
import se.kth.id1020.util.Document;

public class Relevance {


    public static LinkedList<DocRelevance> docRelevanceList(String currentWord, TinySearchEngine tinySearchEngine) {
        LinkedList<Document> docList = new LinkedList<>();
        LinkedList<DocRelevance> docRelevanceList = new LinkedList<>();
        LinkedList<Attributes> attList = tinySearchEngine.wordAttrList.get(currentWord);

        if(attList != null) {
            //turns attributelist into documentlist and removes duplicates
            for (Attributes attributes : attList) {
                if (!docList.contains(attributes.document)) {
                    docList.add(attributes.document);
                }
            }
            //turns documentlist into docrelevancelist and calculates relevance
            for (Document document : docList) {
                double relevance = relevance(currentWord, document, tinySearchEngine);
                docRelevanceList.add(new DocRelevance(document, relevance));
            }
        }

        return docRelevanceList;
    }


    public static double relevance(String currentWord, Document currentDocument, TinySearchEngine tinySearchEngine ) {
        double tf = termFrequency(currentWord, currentDocument, tinySearchEngine);
        double idf = inverseDocumentFrequency(currentWord, tinySearchEngine);
        double tfidf = tf*idf;
        return tfidf;
    }


    public static double termFrequency(String currentWord, Document currentDocument, TinySearchEngine tinySearchEngine) {
        double T_d = tinySearchEngine.wordsInDocs.get(currentDocument);
        double n_q_d = 0;
        LinkedList<Attributes> attrList = tinySearchEngine.wordAttrList.get(currentWord);

        for(Attributes attributes : attrList) {
            if(attributes.document.equals(currentDocument)) {
                n_q_d += 1;
            }
        }
        double tf = n_q_d/T_d;
        return tf;
    }



    public static double inverseDocumentFrequency(String currentWord, TinySearchEngine tinySearchEngine ) {
        double N_d = tinySearchEngine.setOfDoc.size();
        double n_d = n_d(tinySearchEngine.wordAttrList.get(currentWord));
        double idf = Math.log10(N_d/n_d);
        
        return idf;
    }


    public static double n_d(LinkedList<Attributes> attrList) {
        LinkedList<Document> docList = new LinkedList<>();

        for(Attributes attributes : attrList) {
            if(!docList.contains(attributes.document)) {
                docList.add(attributes.document);
            }
        }

        return docList.size();
    }
}
