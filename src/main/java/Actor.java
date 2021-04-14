public class Actor extends Person{

    public Actor(String n) {
        name = n;
        role = "Actor";
        rate = "0";
    }

    public String getRate() {
        try ( Connector connector = new Connector( "bolt://localhost:7687", "neo4j", "root" ) )
        {
            String rate = connector.recalculateRating(this.name, true, false, false, false);
            Double drate = Double.valueOf(rate);
            rate = String.format("%.3f", drate);
            setRate(rate);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }

        return rate;
    }
    //Создание связи актер играл в таком-то фильме
    public void playsIn(Movie movie, boolean orNot) {
        try ( Connector connector = new Connector( "bolt://localhost:7687", "neo4j", "root" ) )
        {
            String mName = movie.getName();
            if (orNot)
                connector.CreateRelation( this,  movie, "playsIn");
            else
                connector.DeleteRelation( this,  movie, "playsIn");
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }

    //Найти фильмы, в которых играл актер
    public String findMovies() {
        String[] resultStr = {"retult is null"};

        try ( Connector connector = new Connector( "bolt://localhost:7687", "neo4j", "root" ) )
        {
                resultStr[0] = connector.FindNode( this, "playsIn", false, true);
        }
        catch (Exception ex) {
            System.out.println(ex);
    }
    return resultStr[0];
    }

    //Найти юзеров, которым нравится актер
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
