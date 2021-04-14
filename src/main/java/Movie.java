import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;


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
            String rate = connector.recalculateRating(this.name, false, false, true, false);
            Double drate = Double.valueOf(rate);
            rate = String.format("%.3f", drate);
            setRate(rate);
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
    public void DirectedBy(Person person, boolean orNot) {
        try ( Connector connector = new Connector( "bolt://localhost:7687", "neo4j", "root" ) )
        {
            if (orNot)
                connector.CreateRelation( this, person, "directedBy" );
            else
                connector.DeleteRelation( this, person, "directedBy" );
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
                connector.CreateRelation( this, genre, "isGenre" );
            else
                connector.DeleteRelation( this, genre, "isGenre" );
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
            connector.FindNode(this, "directedBy", false, false);
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
            connector.FindNode(this, "isGenre", false, true);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    //director
}
