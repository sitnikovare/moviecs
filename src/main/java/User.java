public class User extends Person {

    public User() {}
    public User(String n) {
        name = n;
        role = "user";
    }

    //Создание связи от User к Movie
    public void likes(Movie movie) {
        try ( Connector connector = new Connector( "bolt://localhost:7687", "neo4j", "root" ) )
        {
            connector.CreateRelation( this, movie, "likes" );
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }

    //Создание связи от User к Person
    public void likes(Person person) {
        try ( Connector connector = new Connector( "bolt://localhost:7687", "neo4j", "root" ) )
        {
            connector.CreateRelation( this, person, "likes" );
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }

    //Создание связи от User к Genre
    public void likes(Genre genre) {
        try ( Connector connector = new Connector( "bolt://localhost:7687", "neo4j", "root" ) )
        {
            connector.CreateRelation( this, genre, "likes" );
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void findFilm() {}
    public void findActor() {}

}
