import se.kth.id1020.Graph;
import se.kth.id1020.Vertex;
import java.util.*;
import se.kth.id1020.Edge;

public class ShortestPath {

public static boolean weighted = true;

    public static Vertex getVertex(Graph g, String label) {
        Iterable<Vertex> verIt = g.vertices();
        Vertex v = verIt.iterator().next();
        boolean vertexExists = false;

        while(verIt.iterator().hasNext()){
            if(v.label.equals(label)) {
                vertexExists = true;
                break;
            }
            v = verIt.iterator().next();
        }
        if(vertexExists){return v;}
        else{return null;}
    }

    public static LinkedList<Vertex> Dijkstras(Graph g, String from, String to ) {
        //initialization
        double [] distance = new double[g.numberOfVertices()];
        Vertex[] previous = new Vertex[g.numberOfVertices()];
        Vertex source = getVertex(g, from);
        Vertex target = getVertex(g, to);
        Iterable<Vertex> verIt = g.vertices();
        PriorityQueue<VertexDistance> pq = new PriorityQueue<>(g.numberOfVertices());


        distance[source.id] = 0;

        for(Vertex vertex: verIt) {
            if(vertex != source) {
                distance[vertex.id] = Double.MAX_VALUE;
                previous[vertex.id] = null;
            }
            pq.add(new VertexDistance(vertex, distance[vertex.id]));
        }


        while(pq.size() != 0) {
            VertexDistance u = pq.poll();

            if(u.vertex == target) { break; }
            double alt;

            for(Edge edge : g.adj(u.vertex.id)){
                if(weighted){ alt = distance[u.vertex.id] + edge.weight;}
                else{  alt = distance[u.vertex.id] + 1  ;}
                if(alt < distance[edge.to]) {
                    distance[edge.to] = alt;
                    previous[edge.to] = u.vertex;
                    pq.add(new VertexDistance(g.vertex(edge.to), distance[edge.to]));
                }
            }
        }
        return getPath(previous, target);
    }


    public static LinkedList<Vertex>  getPath(Vertex[] previous, Vertex target) {
        LinkedList<Vertex> list = new LinkedList<>();
        Vertex u = target;
        int count = 0;

        
        while(previous[u.id] != null ) {
            list.addFirst(u);
            System.out.println(u.toString());
            count++;
            u = previous[u.id];
        }

        list.addFirst(u);
        count++;
        System.out.println(u.toString());
        System.out.println("number of vertices in path: " + count + "\n");
        return list;
    }
}
