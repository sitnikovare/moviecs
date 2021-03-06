public class Actor extends Person{

    public Actor(String n) {
        name = n;
        role = "Actor";
    }

    //Создание связи актер играл в таком-то фильме
    public void playsIn(Movie movie, boolean orNot) {
        try ( Connector connector = new Connector( "bolt://localhost:7687", "neo4j", "root" ) )
        {
            String mTitle = movie.getTitle();
            if (orNot)
                connector.CreateRelation( this,  movie, "playsIn");
            else
                connector.DeleteRelation( this,  movie, "playsIn");
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }

    //Найти фильмы, в которых играл актер
    public void findMovies() {
        try ( Connector connector = new Connector( "bolt://localhost:7687", "neo4j", "root" ) )
        {
                connector.FindNode( this, "playsIn", false, true);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }

    //Найти юзеров, которым нравится актер
    public void findLikers() {
        try ( Connector connector = new Connector( "bolt://localhost:7687", "neo4j", "root" ) )
        {
            connector.FindNode( this, "likes", false, false);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
