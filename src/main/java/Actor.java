public class Actor extends Person{

    public Actor(String n) {
        name = n;
        role = "actor";
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
}
