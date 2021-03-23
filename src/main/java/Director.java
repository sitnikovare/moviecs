public class Director extends Person {

    public Director(String n) {
        name = n;
        role = "Director";
    }

    //Найти фильмы режиссера
    public void findMovies() {
        try ( Connector connector = new Connector( "bolt://localhost:11008", "neo4j", "root" ) )
        {
            connector.FindNode( this, "directedBy", true, true);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }

    //Найти юзеров, которым нравится режиссер
    public void findLikers() {
        try ( Connector connector = new Connector( "bolt://localhost:11008", "neo4j", "root" ) )
        {
            connector.FindNode( this, "likes", false, false);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }

}
