public class Query {
    public boolean orderby = false;
    public Property prop;
    public Direction dir;

    public Query(String query) {
        String[] queryArray = query.split(" ");

            if(queryArray.length > 3 && queryArray[queryArray.length-3].equals("orderby")){
                orderby = true;

            if(orderby) {
                switch (queryArray[queryArray.length - 2]) {
                    case "relevance":
                        prop = Property.relevance;
                        break;
                    case "popularity":
                        prop = Property.popularity;
                        break;
                    default:
                        orderby = false;
                        System.out.println("Invalid input");
                        break;
                }
                if (queryArray[queryArray.length - 1].equals("asc")) {
                    dir = Direction.ascending;
                } else {
                    dir = Direction.descending;
                }
            }
        }
    }
}
