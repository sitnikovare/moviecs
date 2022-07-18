import org.jsoup.nodes.Document;


public class Movie {
    private String name;
    private String rate = "0";

    private Document document;

    public Movie() { };

    public Movie(String t) {
        name = t;
    }

    public String getName() {
        return name;
    }
    public String getRate() {
        try ( Connector connector = new Connector( "bolt://localhost:7687", "neo4j", "root" ) )
        {
            String rate = connector.recalculateRating(this.name,  false, true, false, false, false);
            //Double drate = Double.valueOf(rate);
            //this.rate = String.format("%.3f", drate);
            //setRate(this.rate);
            this.rate = rate;
        }
        catch (Exception ex) {
            System.out.println(ex);
        }

        return rate;
    }

    public String getCountry() {
        try ( Connector connector = new Connector( "bolt://localhost:7687", "neo4j", "root" ) )
        {
            String rate = connector.getСountry(this.name);
            //Double drate = Double.valueOf(rate);
            //this.rate = String.format("%.3f", drate);
            //setRate(this.rate);
            this.rate = rate;
        }
        catch (Exception ex) {
            System.out.println(ex);
        }

        return rate;
    }

    public String getDirector() {
        try ( Connector connector = new Connector( "bolt://localhost:7687", "neo4j", "root" ) )
        {
            String rate = connector.getDirector(this.name);
            //Double drate = Double.valueOf(rate);
            //this.rate = String.format("%.3f", drate);
            //setRate(this.rate);
            this.rate = rate;
        }
        catch (Exception ex) {
            System.out.println(ex);
        }

        return rate;
    }
    public String getWriter() {
        try ( Connector connector = new Connector( "bolt://localhost:7687", "neo4j", "root" ) )
        {
            String rate = connector.getWriter(this.name);
            //Double drate = Double.valueOf(rate);
            //this.rate = String.format("%.3f", drate);
            //setRate(this.rate);
            this.rate = rate;
        }
        catch (Exception ex) {
            System.out.println(ex);
        }

        return rate;
    }
    public String getComposer() {
        try ( Connector connector = new Connector( "bolt://localhost:7687", "neo4j", "root" ) )
        {
            String rate = connector.getComposer(this.name);
            //Double drate = Double.valueOf(rate);
            //this.rate = String.format("%.3f", drate);
            //setRate(this.rate);
            this.rate = rate;
        }
        catch (Exception ex) {
            System.out.println(ex);
        }

        return rate;
    }
    public String getYear() {
        try ( Connector connector = new Connector( "bolt://localhost:7687", "neo4j", "root" ) )
        {
            String rate = connector.getYear(this.name);
            //Double drate = Double.valueOf(rate);
            //this.rate = String.format("%.3f", drate);
            //setRate(this.rate);
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


    //Создание узла Movie в базе
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

    //Установка режиссера
    public void DirectedBy(People person, boolean orNot) {
        try ( Connector connector = new Connector( "bolt://localhost:7687", "neo4j", "root" ) )
        {
            if (orNot)
                connector.CreateRelation( this, person, "DIRECTED_BY" );
            else
                connector.DeleteRelation( this, person, "DIRECTED_BY" );
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }

    //Установка жанра
    public void isGenre(Genre genre, boolean orNot) {
        try ( Connector connector = new Connector( "bolt://localhost:7687", "neo4j", "root" ) )
        {
            if (orNot)
                connector.CreateRelation( this, genre, "IS_GENRE" );
            else
                connector.DeleteRelation( this, genre, "IS_GENRE" );
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }

    //Выпущен в прокат
    public void releasedIn(Date date, boolean orNot) {
        try ( Connector connector = new Connector( "bolt://localhost:7687", "neo4j", "root" ) )
        {
            if (orNot)
                connector.CreateRelation( this, date, "releasedIn" );
            else
                connector.DeleteRelation( this, date, "releasedIn" );
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void findDirector() {
        try ( Connector connector = new Connector( "bolt://localhost:7687", "neo4j", "root" ) )
        {
            connector.FindNode(this, "DIRECTED_BY", false, false);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void findActor() {
        try ( Connector connector = new Connector( "bolt://localhost:7687", "neo4j", "root" ) )
        {
            connector.FindNode(this, "playsIn", false, false);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void findLikers() {
        try ( Connector connector = new Connector( "bolt://localhost:7687", "neo4j", "root" ) )
        {
            connector.FindNode(this, "likes", false, false);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void findDate() {
        try ( Connector connector = new Connector( "bolt://localhost:7687", "neo4j", "root" ) )
        {
            connector.FindNode(this, "releasedIn", true, false);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void findGenre() {
        try ( Connector connector = new Connector( "bolt://localhost:7687", "neo4j", "root" ) )
        {
            connector.FindNode(this, "IS_GENRE", false, true);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    //director
}
