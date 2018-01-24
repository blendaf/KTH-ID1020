import se.kth.id1020.Graph;
import se.kth.id1020.DataSource;
public class Paths {
    public static void main(String[] args) {
        Graph g = DataSource.load();

        Connectivity c = new Connectivity(g);
        boolean[] check = c.getVisited();

         c.isFullyConected();
        if(!c.isFullyConected()) {
            System.out.println("The graph is not fully connected \nNumber of threes: " + c.numberOfTress());
        } else {System.out.println("The graph is fully connected");}

        ShortestPath.weighted = true;
        System.out.println("Weighted: " + ShortestPath.weighted);
        ShortestPath.Dijkstras(g, "Renyn", "Parses");

        System.out.println("Weighted: " + ShortestPath.weighted);
        ShortestPath.weighted = false;
        ShortestPath.Dijkstras(g, "Renyn", "Parses");
    }
}