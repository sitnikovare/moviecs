import org.neo4j.driver.*;
import org.neo4j.driver.util.Pair;

import java.util.List;

import static org.neo4j.driver.Values.parameters;

public class Recommedation {
    private final Driver driver;

    public Recommedation( String uri, String user, String password ) {
        driver = GraphDatabase.driver( uri, AuthTokens.basic( user, password ) );
    }

    public String popular10() {
        String res = "";
        try ( Session session = driver.session() ) {
            res = session.writeTransaction( new TransactionWork<String>() {
                @Override
                public String execute( Transaction tx )
                {
                    Result result = tx.run( "MATCH (m:Movie)\n" +
                                    "RETURN m.name ORDER BY m.rate DESC LIMIT 10",
                            parameters() );
                    List<Record> res = result.list();
                    String res0 = "";
                    for( Record record: res) {
                        List<Pair<String, Value>> rec = record.fields(); //get record's fields
                        for (Pair<String, Value> recPair: rec) {
                            res0 += recPair.value().asString() + "\n";
                        }
                    }
                    return res0;
                }
            } );
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
        return res;
    }
    private String getCluster(String movie) {
        String res = "";
        try ( Session session = driver.session() ) {
            res = session.writeTransaction( new TransactionWork<String>() {
                @Override
                public String execute( Transaction tx )
                {
                    Result result = tx.run( "MATCH (m:Movie)\n" +
                                    "WHERE m.name = '" + movie +
                                    "' RETURN m.cluster",
                            parameters() );
                    List<Record> res = result.list();
                    String res0 = "";
                    for( Record record: res) {
                        List<Pair<String, Value>> rec = record.fields(); //get record's fields
                        for (Pair<String, Value> recPair: rec) {
                            res0 += recPair.value().asString();
                        }
                    }
                    return res0;
                }
            } );
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
        return res;
    }

    public String popular10Cluster(String movie) {
        String cluster = getCluster(movie);
        String res = "";
        try ( Session session = driver.session() ) {
            res = session.writeTransaction( new TransactionWork<String>() {
                @Override
                public String execute( Transaction tx )
                {
                    Result result = tx.run( "MATCH (m:Movie)\n" +
                                    "WHERE m.cluster = '" + cluster +
                                    "' RETURN m.name ORDER BY m.rate DESC LIMIT 10",
                            parameters() );
                    List<Record> res = result.list();
                    String res0 = "";
                    for( Record record: res) {
                        List<Pair<String, Value>> rec = record.fields(); //get record's fields
                        for (Pair<String, Value> recPair: rec) {
                            res0 += recPair.value().asString() + "\n";
                        }
                    }
                    return res0;
                }
            } );
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
        return res;
    }

    public String popular10Genre(String genre) {
        String res = "";
        try ( Session session = driver.session() ) {
            res = session.writeTransaction( new TransactionWork<String>() {
                @Override
                public String execute( Transaction tx )
                {
                    Result result = tx.run( "MATCH (m:Movie)-[]->(g:Genre)\n" +
                                    "WHERE g.name = '" + genre +
                                    "' RETURN m.name ORDER BY m.rate DESC LIMIT 10",
                            parameters() );
                    List<Record> res = result.list();
                    String res0 = "";
                    for( Record record: res) {
                        List<Pair<String, Value>> rec = record.fields(); //get record's fields
                        for (Pair<String, Value> recPair: rec) {
                            res0 += recPair.value().asString() + "\n";
                        }
                    }
                    return res0;
                }
            } );
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
        return res;
    }

    public String popular10Country(String country) {
        String res = "";
        try ( Session session = driver.session() ) {
            res = session.writeTransaction( new TransactionWork<String>() {
                @Override
                public String execute( Transaction tx )
                {
                    Result result = tx.run( "MATCH (m:Movie)-[]->(c:Country)\n" +
                                    "WHERE c.name = '" + country +
                                    "' RETURN m.name ORDER BY m.rate DESC LIMIT 10",
                            parameters() );
                    List<Record> res = result.list();
                    String res0 = "";
                    for( Record record: res) {
                        List<Pair<String, Value>> rec = record.fields(); //get record's fields
                        for (Pair<String, Value> recPair: rec) {
                            res0 += recPair.value().asString() + "\n";
                        }
                    }
                    return res0;
                }
            } );
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
        return res;
    }

    public String popular10Year(String year) {
        String res = "";
        try ( Session session = driver.session() ) {
            res = session.writeTransaction( new TransactionWork<String>() {
                @Override
                public String execute( Transaction tx )
                {
                    Result result = tx.run( "MATCH (m:Movie)-[]->(y:Year)\n" +
                                    "WHERE y.name = '" + year +
                                    "' RETURN m.name ORDER BY m.rate DESC LIMIT 10",
                            parameters() );
                    List<Record> res = result.list();
                    String res0 = "";
                    for( Record record: res) {
                        List<Pair<String, Value>> rec = record.fields(); //get record's fields
                        for (Pair<String, Value> recPair: rec) {
                            res0 += recPair.value().asString() + "\n";
                        }
                    }
                    return res0;
                }
            } );
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
        return res;
    }

    public String popular10Director(String director) {
        String res = "";
        try ( Session session = driver.session() ) {
            res = session.writeTransaction( new TransactionWork<String>() {
                @Override
                public String execute( Transaction tx )
                {
                    Result result = tx.run( "MATCH (m:Movie)-[r:DIRECTED_BY]->(d:People)\n" +
                                    "WHERE d.name = '" + director +
                                    "' RETURN m.name ORDER BY m.rate DESC LIMIT 10",
                            parameters() );
                    List<Record> res = result.list();
                    String res0 = "";
                    for( Record record: res) {
                        List<Pair<String, Value>> rec = record.fields(); //get record's fields
                        for (Pair<String, Value> recPair: rec) {
                            res0 += recPair.value().asString() + "\n";
                        }
                    }
                    return res0;
                }
            } );
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
        return res;
    }

    public String popular10Actor(String actor) {
        String res = "";
        try ( Session session = driver.session() ) {
            res = session.writeTransaction( new TransactionWork<String>() {
                @Override
                public String execute( Transaction tx )
                {
                    Result result = tx.run( "MATCH (a:People)-[r:ACTED_IN]->(m:Movie)\n" +
                                    "WHERE a.name = '" + actor +
                                    "' RETURN m.name ORDER BY m.rate DESC LIMIT 10",
                            parameters() );
                    List<Record> res = result.list();
                    String res0 = "";
                    for( Record record: res) {
                        List<Pair<String, Value>> rec = record.fields(); //get record's fields
                        for (Pair<String, Value> recPair: rec) {
                            res0 += recPair.value().asString() + "\n";
                        }
                    }
                    return res0;
                }
            } );
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
        return res;
    }

    public String popular10Writer(String writer) {
        String res = "";
        try ( Session session = driver.session() ) {
            res = session.writeTransaction( new TransactionWork<String>() {
                @Override
                public String execute( Transaction tx )
                {
                    Result result = tx.run( "MATCH (m:Movie)-[r:WRITED_BY]->(w:People)\n" +
                                    "WHERE w.name = '" + writer +
                                    "' RETURN m.name ORDER BY m.rate DESC LIMIT 10 ",
                            parameters() );
                    List<Record> res = result.list();
                    String res0 = "";
                    for( Record record: res) {
                        List<Pair<String, Value>> rec = record.fields(); //get record's fields
                        for (Pair<String, Value> recPair: rec) {
                            res0 += recPair.value().asString() + "\n";
                        }
                    }
                    return res0;
                }
            } );
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
        return res;
    }

    public String popular10Composer(String composer) {
        String res = "";
        try ( Session session = driver.session() ) {
            res = session.writeTransaction( new TransactionWork<String>() {
                @Override
                public String execute( Transaction tx )
                {
                    Result result = tx.run( "MATCH (m:Movie)-[r:COMPOSED_BY]->(c:People)\n" +
                                    "WHERE c.name = '" + composer +
                                    "' RETURN m.name ORDER BY m.rate DESC LIMIT 10",
                            parameters() );
                    List<Record> res = result.list();
                    String res0 = "";
                    for( Record record: res) {
                        List<Pair<String, Value>> rec = record.fields(); //get record's fields
                        for (Pair<String, Value> recPair: rec) {
                            res0 += recPair.value().asString() + "\n";
                        }
                    }
                    return res0;
                }
            } );
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
        return res;
    }

    public String actorsFromFilm(String movie) {
        String res = "";
        try ( Session session = driver.session() ) {
            res = session.writeTransaction( new TransactionWork<String>() {
                @Override
                public String execute( Transaction tx )
                {
                    Result result = tx.run( "MATCH (a:People)-[ACTED_IN]->(m:Movie)\n" +
                                    "WHERE m.name = '" + movie +
                                    "' RETURN a.name ORDER BY a.name",
                            parameters() );
                    List<Record> res = result.list();
                    String res0 = "";
                    for( Record record: res) {
                        List<Pair<String, Value>> rec = record.fields(); //get record's fields
                        for (Pair<String, Value> recPair: rec) {
                            res0 += recPair.value().asString() + "\n";
                        }
                    }
                    return res0;
                }
            } );
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
        return res;
    }

    public String directorsFromFilm(String movie) {
        String res = "";
        try ( Session session = driver.session() ) {
            res = session.writeTransaction( new TransactionWork<String>() {
                @Override
                public String execute( Transaction tx )
                {
                    Result result = tx.run( "MATCH (m:Movie)-[DIRECTED_BY]->(p:People)\n" +
                                    "WHERE m.name = '" + movie +
                                    "' RETURN p.name ORDER BY p.name",
                            parameters() );
                    List<Record> res = result.list();
                    String res0 = "";
                    for( Record record: res) {
                        List<Pair<String, Value>> rec = record.fields(); //get record's fields
                        for (Pair<String, Value> recPair: rec) {
                            res0 += recPair.value().asString() + "\n";
                        }
                    }
                    return res0;
                }
            } );
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
        return res;
    }

    public String writersFromFilm(String movie) {
        String res = "";
        try ( Session session = driver.session() ) {
            res = session.writeTransaction( new TransactionWork<String>() {
                @Override
                public String execute( Transaction tx )
                {
                    Result result = tx.run( "MATCH (m:Movie)-[WRITED_BY]->(p:People)\n" +
                                    "WHERE m.name = '" + movie +
                                    "' RETURN p.name ORDER BY p.name",
                            parameters() );
                    List<Record> res = result.list();
                    String res0 = "";
                    for( Record record: res) {
                        List<Pair<String, Value>> rec = record.fields(); //get record's fields
                        for (Pair<String, Value> recPair: rec) {
                            res0 += recPair.value().asString() + "\n";
                        }
                    }
                    return res0;
                }
            } );
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
        return res;
    }

    public String composersFromFilm(String movie) {
        String res = "";
        try ( Session session = driver.session() ) {
            res = session.writeTransaction( new TransactionWork<String>() {
                @Override
                public String execute( Transaction tx )
                {
                    Result result = tx.run( "MATCH (m:Movie)-[WRITED_BY]->(p:People)\n" +
                                    "WHERE m.name = '" + movie +
                                    "' RETURN p.name ORDER BY p.name",
                            parameters() );
                    List<Record> res = result.list();
                    String res0 = "";
                    for( Record record: res) {
                        List<Pair<String, Value>> rec = record.fields(); //get record's fields
                        for (Pair<String, Value> recPair: rec) {
                            res0 += recPair.value().asString() + "\n";
                        }
                    }
                    return res0;
                }
            } );
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
        return res;
    }

    public String filmsByActor(String actor) {
        String res = "";
        try ( Session session = driver.session() ) {
            res = session.writeTransaction( new TransactionWork<String>() {
                @Override
                public String execute( Transaction tx )
                {
                    Result result = tx.run( "MATCH (a:People)-[r:ACTED_IN]->(m:Movie)\n" +
                                    "WHERE a.name = '" + actor +
                                    "' RETURN m.name ORDER BY m.rate DESC",
                            parameters() );
                    List<Record> res = result.list();
                    String res0 = "";
                    for( Record record: res) {
                        List<Pair<String, Value>> rec = record.fields(); //get record's fields
                        for (Pair<String, Value> recPair: rec) {
                            res0 += recPair.value().asString() + "\n";
                        }
                    }
                    return res0;
                }
            } );
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
        return res;
    }

    public String filmsByDirector(String director) {
        String res = "";
        try ( Session session = driver.session() ) {
            res = session.writeTransaction( new TransactionWork<String>() {
                @Override
                public String execute( Transaction tx )
                {
                    Result result = tx.run( "MATCH (m:Movie)-[r:DIRECTED_BY]->(d:People)\n" +
                                    "WHERE d.name = '" + director +
                                    "' RETURN m.name ORDER BY m.rate DESC",
                            parameters() );
                    List<Record> res = result.list();
                    String res0 = "";
                    for( Record record: res) {
                        List<Pair<String, Value>> rec = record.fields(); //get record's fields
                        for (Pair<String, Value> recPair: rec) {
                            res0 += recPair.value().asString() + "\n";
                        }
                    }
                    return res0;
                }
            } );
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
        return res;
    }

    public String filmsByWriter(String writer) {
        String res = "";
        try ( Session session = driver.session() ) {
            res = session.writeTransaction( new TransactionWork<String>() {
                @Override
                public String execute( Transaction tx )
                {
                    Result result = tx.run( "MATCH (m:Movie)-[r:WRITED_BY]->(w:People)\n" +
                                    "WHERE w.name = '" + writer +
                                    "' RETURN m.name ORDER BY m.rate DESC",
                            parameters() );
                    List<Record> res = result.list();
                    String res0 = "";
                    for( Record record: res) {
                        List<Pair<String, Value>> rec = record.fields(); //get record's fields
                        for (Pair<String, Value> recPair: rec) {
                            res0 += recPair.value().asString() + "\n";
                        }
                    }
                    return res0;
                }
            } );
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
        return res;
    }

    public String filmsByComposer(String composer) {
        String res = "";
        try ( Session session = driver.session() ) {
            res = session.writeTransaction( new TransactionWork<String>() {
                @Override
                public String execute( Transaction tx )
                {
                    Result result = tx.run( "MATCH (m:Movie)-[r:COMPOSED_BY]->(c:People)\n" +
                                    "WHERE c.name = '" + composer +
                                    "' RETURN m.name ORDER BY m.rate DESC",
                            parameters() );
                    List<Record> res = result.list();
                    String res0 = "";
                    for( Record record: res) {
                        List<Pair<String, Value>> rec = record.fields(); //get record's fields
                        for (Pair<String, Value> recPair: rec) {
                            res0 += recPair.value().asString() + "\n";
                        }
                    }
                    return res0;
                }
            } );
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
        return res;
    }

    public String films25ByYear(String year) {
        String res = "";
        try ( Session session = driver.session() ) {
            res = session.writeTransaction( new TransactionWork<String>() {
                @Override
                public String execute( Transaction tx )
                {
                    Result result = tx.run( "MATCH (m:Movie)-[]->(y:Year)\n" +
                                    "WHERE y.name = '" + year +
                                    "' RETURN m.name ORDER BY m.rate DESC LIMIT 25",
                            parameters() );
                    List<Record> res = result.list();
                    String res0 = "";
                    for( Record record: res) {
                        List<Pair<String, Value>> rec = record.fields(); //get record's fields
                        for (Pair<String, Value> recPair: rec) {
                            res0 += recPair.value().asString() + "\n";
                        }
                    }
                    return res0;
                }
            } );
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
        return res;
    }

    public String films25ByCountry(String country) {
        String res = "";
        try ( Session session = driver.session() ) {
            res = session.writeTransaction( new TransactionWork<String>() {
                @Override
                public String execute( Transaction tx )
                {
                    Result result = tx.run( "MATCH (m:Movie)-[]->(c:Country)\n" +
                                    "WHERE c.name = '" + country +
                                    "' RETURN m.name ORDER BY m.rate DESC LIMIT 25",
                            parameters() );
                    List<Record> res = result.list();
                    String res0 = "";
                    for( Record record: res) {
                        List<Pair<String, Value>> rec = record.fields(); //get record's fields
                        for (Pair<String, Value> recPair: rec) {
                            res0 += recPair.value().asString() + "\n";
                        }
                    }
                    return res0;
                }
            } );
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
        return res;
    }

    public String films25ByGenre(String genre) {
        String res = "";
        try ( Session session = driver.session() ) {
            res = session.writeTransaction( new TransactionWork<String>() {
                @Override
                public String execute( Transaction tx )
                {
                    Result result = tx.run( "MATCH (m:Movie)-[]->(g:Genre)\n" +
                                    "WHERE g.name = '" + genre +
                                    "' RETURN m.name ORDER BY m.rate DESC LIMIT 25",
                            parameters() );
                    List<Record> res = result.list();
                    String res0 = "";
                    for( Record record: res) {
                        List<Pair<String, Value>> rec = record.fields(); //get record's fields
                        for (Pair<String, Value> recPair: rec) {
                            res0 += recPair.value().asString() + "\n";
                        }
                    }
                    return res0;
                }
            } );
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
        return res;
    }

    public String films10ByYearGenre(String year, String genre) {
        String res = "";
        try ( Session session = driver.session() ) {
            res = session.writeTransaction( new TransactionWork<String>() {
                @Override
                public String execute( Transaction tx )
                {
                    Result result = tx.run( "MATCH (m1:Movie)-[RELEASED_IN]->(y: Year {name: '"+ year +"'}),\n" +
                                    "(m2:Movie)-[IS_GENRE]->(g: Genre {name: '"+genre+"'})\n" +
                                    "WHERE m1.id = m2.id\n" +
                                    "RETURN m1.name ORDER BY m2.rate DESC LIMIT 10",
                            parameters() );
                    List<Record> res = result.list();
                    String res0 = "";
                    for( Record record: res) {
                        List<Pair<String, Value>> rec = record.fields(); //get record's fields
                        for (Pair<String, Value> recPair: rec) {
                            res0 += recPair.value().asString() + "\n";
                        }
                    }
                    return res0;
                }
            } );
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
        return res;
    }

    public String films10ByDirectorGenre(String dir, String genre) {
        String res = "";
        try ( Session session = driver.session() ) {
            res = session.writeTransaction( new TransactionWork<String>() {
                @Override
                public String execute( Transaction tx )
                {
                    Result result = tx.run( "MATCH (m1:Movie)-[DIRECTED_BY]->(d: People {name: '"+dir+"'}),\n" +
                                    "(m2:Movie)-[IS_GENRE]->(g: Genre {name: '"+genre+"'})\n" +
                                    "WHERE m1.id = m2.id\n" +
                                    "RETURN m1.name ORDER BY m2.rate DESC LIMIT 10",
                            parameters() );
                    List<Record> res = result.list();
                    String res0 = "";
                    for( Record record: res) {
                        List<Pair<String, Value>> rec = record.fields(); //get record's fields
                        for (Pair<String, Value> recPair: rec) {
                            res0 += recPair.value().asString() + "\n";
                        }
                    }
                    return res0;
                }
            } );
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
        return res;
    }

    public String films10ByDirectorCountry(String dir, String country) {
        String res = "";
        try ( Session session = driver.session() ) {
            res = session.writeTransaction( new TransactionWork<String>() {
                @Override
                public String execute( Transaction tx )
                {
                    Result result = tx.run( "MATCH (m1:Movie)-[DIRECTED_BY]->(d: People {name: '"+dir+"'}),\n" +
                                    "(m2:Movie)-[FROM_COUNTRY]->(c: Country {name: '"+country+"'})\n" +
                                    "WHERE m1.id = m2.id\n" +
                                    "RETURN m1.name ORDER BY m2.rate DESC LIMIT 10",
                            parameters() );
                    List<Record> res = result.list();
                    String res0 = "";
                    for( Record record: res) {
                        List<Pair<String, Value>> rec = record.fields(); //get record's fields
                        for (Pair<String, Value> recPair: rec) {
                            res0 += recPair.value().asString() + "\n";
                        }
                    }
                    return res0;
                }
            } );
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
        return res;
    }

    public String films10ByActorGenre(String act, String gen) {
        String res = "";
        try ( Session session = driver.session() ) {
            res = session.writeTransaction( new TransactionWork<String>() {
                @Override
                public String execute( Transaction tx )
                {
                    Result result = tx.run( "MATCH (a: People {name: '"+act+"'})-[ACTED_IN]->(m1: Movie),\n" +
                                    "(m2:Movie)-[FROM_COUNTRY]->(g: Genre {name: '"+gen+"'})\n" +
                                    "WHERE m1.id = m2.id\n" +
                                    "RETURN m1.name ORDER BY m2.rate DESC LIMIT 10",
                            parameters() );
                    List<Record> res = result.list();
                    String res0 = "";
                    for( Record record: res) {
                        List<Pair<String, Value>> rec = record.fields(); //get record's fields
                        for (Pair<String, Value> recPair: rec) {
                            res0 += recPair.value().asString() + "\n";
                        }
                    }
                    return res0;
                }
            } );
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
        return res;
    }

    public String films10ByFilmYear(String mov) {
        String res = "";
        try ( Session session = driver.session() ) {
            res = session.writeTransaction( new TransactionWork<String>() {
                @Override
                public String execute( Transaction tx )
                {
                    Result result = tx.run( "MATCH (m1: Movie {name: '"+mov+"'})-[]->(y1: Year), \n" +
                                    "(m2: Movie)-[]->(y2: Year)\n" +
                                    "WHERE m1.cluster = m2.cluster AND y1.name = y2.name\n" +
                                    "RETURN m2.name ORDER BY m2.rate DESC LIMIT 10",
                            parameters() );
                    List<Record> res = result.list();
                    String res0 = "";
                    for( Record record: res) {
                        List<Pair<String, Value>> rec = record.fields(); //get record's fields
                        for (Pair<String, Value> recPair: rec) {
                            res0 += recPair.value().asString() + "\n";
                        }
                    }
                    return res0;
                }
            } );
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
        return res;
    }

    public String films10ByFilmGenre(String mov) {
        String res = "";
        try ( Session session = driver.session() ) {
            res = session.writeTransaction( new TransactionWork<String>() {
                @Override
                public String execute( Transaction tx )
                {
                    Result result = tx.run( "MATCH (m1: Movie {name: '"+mov+"'})-[]->(g1: Genre), \n" +
                                    "(m2: Movie)-[]->(g2: Genre)\n" +
                                    "WHERE m1.cluster = m2.cluster AND g1.name = g2.name\n" +
                                    "RETURN m2.name ORDER BY m2.rate DESC LIMIT 10 ",
                            parameters() );
                    List<Record> res = result.list();
                    String res0 = "";
                    for( Record record: res) {
                        List<Pair<String, Value>> rec = record.fields(); //get record's fields
                        for (Pair<String, Value> recPair: rec) {
                            res0 += recPair.value().asString() + "\n";
                        }
                    }
                    return res0;
                }
            } );
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
        return res;
    }

    public String films10ByFilmCountry(String mov) {
        String res = "";
        try ( Session session = driver.session() ) {
            res = session.writeTransaction( new TransactionWork<String>() {
                @Override
                public String execute( Transaction tx )
                {
                    Result result = tx.run( "MATCH (m1: Movie {name: '"+mov+"'})-[]->(c1: Country), \n" +
                                    "(m2: Movie)-[]->(c2: Country)\n" +
                                    "WHERE m1.cluster = m2.cluster AND c1.name = c2.name\n" +
                                    "RETURN m2.name ORDER BY m2.rate DESC LIMIT 10",
                            parameters() );
                    List<Record> res = result.list();
                    String res0 = "";
                    for( Record record: res) {
                        List<Pair<String, Value>> rec = record.fields(); //get record's fields
                        for (Pair<String, Value> recPair: rec) {
                            res0 += recPair.value().asString() + "\n";
                        }
                    }
                    return res0;
                }
            } );
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
        return res;
    }
}
