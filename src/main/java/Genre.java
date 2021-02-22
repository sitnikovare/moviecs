public class Genre {
    private String title;

    public Genre(String g) {
        title = g;
    }
    public String getTitle() {
        return title;
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
}
