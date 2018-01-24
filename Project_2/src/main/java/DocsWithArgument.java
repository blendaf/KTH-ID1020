import java.util.LinkedList;
import java.util.*;
import java.lang.*;
import se.kth.id1020.util.Attributes;
import se.kth.id1020.util.Document;
import se.kth.id1020.util.Word;
import se.kth.id1020.util.*;

public class DocsWithArgument {
    public String argument;
    public LinkedList<DocRelevance> docRelevanceList;
    public String argumentOrdered;

    public DocsWithArgument(LinkedList<DocRelevance> docRelevanceList, String argument, String argumentOrdered) {
        this.docRelevanceList = docRelevanceList;
        this.argument = argument;
        this.argumentOrdered = argumentOrdered;
    }
}
