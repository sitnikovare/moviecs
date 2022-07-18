public class Genre {
    private String name;
    private String rate = "0";

    public Genre(String g) {
        name = g;
    }
    public Genre(String g, String r) {
        name = g;
        rate = r;
    }
    public String getName() {
        return name;
    }
    public String getRate() {
        try ( Connector connector = new Connector( "bolt://localhost:7687", "neo4j", "root" ) )
        {
            String rate = connector.recalculateRating(this.name, false, false,  true, false, false);
//            Double drate = Double.valueOf(rate);
//            rate = String.format("%.3f", drate);
//            setRate(rate);
            this.rate = rate;
        }
        catch (Exception ex) {
            System.out.println(ex);
        }

        return rate;
    }
    public void setRate(String r) {
        rate = r;
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
