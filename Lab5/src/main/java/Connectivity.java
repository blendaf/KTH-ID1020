import se.kth.id1020.Graph;
import se.kth.id1020.Vertex;
import se.kth.id1020.DataSource;
import java.util.Iterator;
import se.kth.id1020.Edge;

public class Connectivity {

    public boolean visited[];
    public int[] id;
    public int count;

    public Connectivity(Graph g) {
        visited = new boolean[g.numberOfVertices()];
        id = new int[g.numberOfVertices()];
        Iterable<Vertex> verIt = g.vertices();

        while(verIt.iterator().hasNext()){
            Vertex v = verIt.iterator().next();
            if(!visited(v.id)) {
                count++;
                id[v.id] = v.id;
                depthFirstSearch(g, v.id);
            }
        }
    }


    public void depthFirstSearch(Graph g, int v) {
        visited[v] = true;

        for(Edge edge: g.adj(v)) {
            if(!visited[edge.to]) {
                id[edge.to] = id[v];
                depthFirstSearch(g,edge.to);
            }
        }
    }


    public boolean isFullyConected() {
        boolean fullyConnected = true;
        int root = id[0];

        for(int i = 0; i < id.length; i++) {
            if(id[i] != root) {
                fullyConnected = false;
                break;
            }
        }
        return fullyConnected ;
    }


    public int numberOfTress() {
        return this.count;
    }
    public boolean visited(int v) {return visited[v];}
    public boolean[] getVisited() {return this.visited;}
}