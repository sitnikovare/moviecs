public class Movie {
    private String title;

    public Movie() {};

    public Movie(String t) {
        title = t;
    }

    public String getTitle() {
        return title;
    }

    //Установка режиссера
    public void DirectedBy(Person person) {
        try ( Connector connector = new Connector( "bolt://localhost:7687", "neo4j", "root" ) )
        {
            String pName = person.getName();
            connector.CreateMPRelation( this.title, pName, "directedBy" );
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
