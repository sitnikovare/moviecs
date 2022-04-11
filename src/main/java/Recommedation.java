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
}
