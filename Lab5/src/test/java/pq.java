import se.kth.id1020.DataSource;
import se.kth.id1020.Vertex;
import se.kth.id1020.Graph;
import java.util.PriorityQueue;

public class pq {

public static void main(String[] args) {

    Graph g = DataSource.load();
    Iterable<Vertex> verIt = g.vertices();
    PriorityQueue<Vertex> pq = new PriorityQueue<>(g.numberOfVertices());

    for(int i = 0; i < 6 ; i++) pq.add(verIt.iterator().next());

    for(int i = 0; i < 6; i++){
        System.out.println(pq.poll().id);
    }

}


}
