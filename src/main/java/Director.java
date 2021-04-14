public class Director extends Person {

    public Director(String n) {
        name = n;
        role = "Director";
    }

    public String getRate() {
        try ( Connector connector = new Connector( "bolt://localhost:7687", "neo4j", "root" ) )
        {
            String rate = connector.recalculateRating(this.name, false, true, false, false);
            Double drate = Double.valueOf(rate);
            rate = String.format("%.3f", drate);
            setRate(rate);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }

        return rate;
    }

    //Найти фильмы режиссера
    public void findMovies() {
        try ( Connector connector = new Connector( "bolt://localhost:7687", "neo4j", "root" ) )
        {
            connector.FindNode( this, "directedBy", true, true);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }

    //Найти юзеров, которым нравится режиссер
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
