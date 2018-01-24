import java.util.ArrayList;

public class Query {
    public ArrayList<String> queries = new ArrayList<>();
    public boolean orderby = false;
    public Property prop;
    public Direction dir;

    public Query(String query) {

        int index = 0;

        String[] queryArray = query.split(" ");

            for (int i = 0; i < queryArray.length; i++) {
                if ( queryArray[i].equals("orderby" )) {
                    index = ++i;
                    orderby = true;
                    break;
                }
                queries.add(queryArray[i]);
            }


            if (orderby) {
                switch (queryArray[index]) {
                    case "count":
                        prop = Property.count;
                        break;
                    case "popularity":
                        prop = Property.popularity;
                        break;
                    case "occurrence":
                        prop = Property.occurrence;
                        break;
                    default:
                        prop = Property.count;
                        System.out.println("Invalid input \n do: count");
                        break;
                }
                if (queryArray.length > (index + 1) && queryArray[++index].equals("asc")) {
                    dir = Direction.ascending;
                } else {
                    dir = Direction.descending;
                }
            }



    }









}
