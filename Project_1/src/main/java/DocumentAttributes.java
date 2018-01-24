import se.kth.id1020.util.Document;

import java.util.ArrayList;

public class DocumentAttributes {

    public Document document;
    public int count = 1;
    public int minOccurance;
    public int maxPopularity;

    public DocumentAttributes(WordAttr wordAttr ) {
        this.document = wordAttr.attribute.document;
        this.minOccurance = wordAttr.attribute.occurrence;
        this.maxPopularity = this.document.popularity;
    }

    public static void merge (WordAttr wordAttr, DocumentAttributes docAttr){

        docAttr.count++;

        if(wordAttr.attribute.occurrence < docAttr.minOccurance){
            docAttr.minOccurance = wordAttr.attribute.occurrence;
        }

        if(wordAttr.attribute.document.popularity > docAttr.maxPopularity) {
            docAttr.maxPopularity = wordAttr.attribute.document.popularity;
        }
    }

    public static ArrayList<DocumentAttributes> toDocumentAttributes(ArrayList<WordAttr> wordAttr){
        ArrayList<DocumentAttributes> docAttr = new ArrayList<>();

        int index = 0;
        for(int i = 0; i < wordAttr.size(); i++) {
            boolean documentExists = false;
            for(int j = 0; j < docAttr.size(); j++){
                if(wordAttr.get(i).attribute.document.equals(docAttr.get(j).document)) {
                    documentExists = true;
                    index = j;
                    break;
                }
            }
            if(!documentExists) {
                docAttr.add(new DocumentAttributes(wordAttr.get(i)));
                documentExists = false;
            } else {
                merge(wordAttr.get(i), docAttr.get(index));
            }
        }
        return docAttr;
    }


    public int compareTo(DocumentAttributes docAttr, Property prop, Direction dir) {

        int compare;

        switch(prop){
            case count:
                if(dir == Direction.ascending){
                    compare = this.count - docAttr.count;
                } else {
                    compare = docAttr.count - this.count;
                }
                break;
            case occurrence:
                if(dir == Direction.ascending){
                    compare = this.minOccurance - docAttr.minOccurance;
                } else {
                    compare = docAttr.minOccurance - this.minOccurance;
                }
                break;
            case popularity:
                if(dir == Direction.ascending){
                    compare = this.maxPopularity - docAttr.maxPopularity;
                } else {
                    compare = docAttr.maxPopularity - this.maxPopularity;
                }
                break;
            default:
                compare = 0;

        }

        return compare;
    }
}
