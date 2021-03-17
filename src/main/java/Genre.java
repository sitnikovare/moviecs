public class Genre {
    private String name;

    public Genre(String g) {
        name = g;
    }
    public String getName() {
        return name;
    }

    //Создание узла Genre в базе
    public void initInDB() {
        try ( Connector connector = new Connector( "bolt://localhost:7687", "neo4j", "root" ) )
        {
            connector.CreateNode( this);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void deleteFromDB() {
        try ( Connector connector = new Connector( "bolt://localhost:7687", "neo4j", "root" ) )
        {
            connector.DeleteNode(this);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }

    //Найти фильмы данного жанра
    public void findMovies() {
        try ( Connector connector = new Connector( "bolt://localhost:7687", "neo4j", "root" ) )
        {
            connector.FindNode( this, "isGenre", true);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }

    //Найти юзеров, которым нравится жанр
    public void findLikers() {
        try ( Connector connector = new Connector( "bolt://localhost:7687", "neo4j", "root" ) )
        {
            connector.FindNode( this, "likes", false);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
