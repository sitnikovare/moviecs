public class User extends Person {

    public User(String n) {
        name = n;
        role = "user";
    }

    //Создание связи от User к Movie
    public void likes(Movie movie) {
        try ( Connector connector = new Connector( "bolt://localhost:7687", "neo4j", "root" ) )
        {
            String mTitle = movie.getTitle();
            connector.CreatePMRelation( this.name, mTitle, "likes" );
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }

    //Создание связи от User к Person
    public void likes(Person person) {
        try ( Connector connector = new Connector( "bolt://localhost:7687", "neo4j", "root" ) )
        {
            String pName = person.getName();
            connector.CreatePPRelation( this.name, pName, "likes" );
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }

    //Создание связи от User к Genre
    public void likes(Genre genre) {
        try ( Connector connector = new Connector( "bolt://localhost:7687", "neo4j", "root" ) )
        {
            String gTitle = genre.getTitle();
            connector.CreatePGRelation( this.name, gTitle, "likes" );
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void findFilm() {}
    public void findActor() {}

}
