import se.kth.id1020.Graph;
import se.kth.id1020.Vertex;


public class VertexDistance implements Comparable<VertexDistance>{
    public Vertex vertex;
    public double distance;

    public VertexDistance(Vertex vertex, double distance) {
        this.vertex = vertex;
        this.distance = distance;
    }

    public int compareTo(VertexDistance vd) {
        double result = this.distance - vd.distance;
        return (int)(result < 0 ? Math.floor(result) : Math.ceil(result));
    }
}
