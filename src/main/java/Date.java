public class Date {
    private String year;
    private String rate = "0";

    public Date(String y, String r) {
        year = y;
        rate = r;
    }

    public Date(String y) {
        year = y;
    }

    String getYear() { return year;}
    String getRate() { return rate;}
    void setRate(String r) { rate = r;}

    //Создание узла Date в базе
    public void initInDB() {
        try ( Connector connector = new Connector( "bolt://localhost:7687", "neo4j", "root" ) )
        {
            connector.CreateNode(this);
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

    //Найти фильмы данного года
    public void findMovies() {
        try ( Connector connector = new Connector( "bolt://localhost:7687", "neo4j", "root" ) )
        {
            connector.FindNode( this, "releasedIn");
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
