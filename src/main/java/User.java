public class User extends Person {

    public User() {}
    public User(String n) {
        name = n;
        role = "user";
    }

    //Создание связи от User к Movie
    public void likes(Movie movie, boolean orNot) {
        try ( Connector connector = new Connector( "bolt://localhost:7687", "neo4j", "root" ) )
        {
            if (orNot)
                connector.CreateRelation( this, movie, "likes" );
            else
                connector.DeleteRelation( this, movie, "likes" );
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }

    //Создание связи от User к Person
    public void likes(Person person, boolean orNot) {
        try ( Connector connector = new Connector( "bolt://localhost:7687", "neo4j", "root" ) )
        {
            if (orNot)
                connector.CreateRelation( this, person, "likes" );
            else
                connector.DeleteRelation( this, person, "likes" );
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }

    //Создание связи от User к Genre
    public void likes(Genre genre, boolean orNot) {
        try ( Connector connector = new Connector( "bolt://localhost:7687", "neo4j", "root" ) )
        {
            if (orNot)
                connector.CreateRelation( this, genre, "likes" );
            else
                connector.DeleteRelation( this, genre, "likes" );
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void findFilm() {}
    public void findActor() {}

}
