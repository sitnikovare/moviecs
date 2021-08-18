import com.sun.tools.internal.xjc.model.SymbolSpace;
import org.neo4j.cypher.internal.v3_5.expressions.Null;
import org.neo4j.driver.*;


import org.neo4j.driver.AuthTokens;
        import org.neo4j.driver.Driver;
        import org.neo4j.driver.GraphDatabase;
        import org.neo4j.driver.Result;
        import org.neo4j.driver.Session;
        import org.neo4j.driver.Transaction;
        import org.neo4j.driver.TransactionWork;

import java.util.List;

import static org.neo4j.driver.Values.NULL;
import static org.neo4j.driver.Values.parameters;

public class Connector implements AutoCloseable {
    private final Driver driver;

    public Connector( String uri, String user, String password ) {
        driver = GraphDatabase.driver( uri, AuthTokens.basic( user, password ) );
    }

    @Override
    public void close() throws Exception {
        driver.close();
    }

    //приветствие просто так
    public void printGreeting( final String message  ) {
        try ( Session session = driver.session() ) {
            String greeting = session.writeTransaction( new TransactionWork<String>() {
                @Override
                public String execute( Transaction tx )
                {
                    Result result = tx.run( "CREATE (a:Greeting) " +
                                    "SET a.message = $message " +
                                    "RETURN a.message + ', from node ' + id(a)",
                            parameters( "message", message ) );
                    return result.single().get( 0 ).asString();
                }
            } );
            System.out.println( greeting );
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
    }

    //Создание узла от класса Person в БД
    public void CreateNode(Person person) {
        try ( Session session = driver.session() ) {
            String createPNode = session.writeTransaction( new TransactionWork<String>() {
                @Override
                public String execute( Transaction tx )
                {
                    String role = person.getRole();
                    String name = person.getName();

                    Result result = tx.run( "CREATE (a:" + role +
                                    "{name: $name, rate: '0'}) " +
                                    "RETURN a.name + ', from node ' + id(a)",
                            parameters( "name", name) );
                    return result.single().get( 0 ).asString();
                }
            } );
            System.out.println( createPNode );
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
    }

    //Создание узла Movie в БД
    public void CreateNode(Movie movie) {
        try ( Session session = driver.session() ) {
            String createPNode = session.writeTransaction( new TransactionWork<String>() {
                @Override
                public String execute( Transaction tx )
                {
                    String mname = movie.getName();

                    Result result = tx.run( "CREATE (a:Movie {name: $mname, rate: '0'} ) " +
                                    "RETURN a.name + ', from node ' + id(a)",
                            parameters( "mname", mname) );
                    return result.single().get( 0 ).asString();
                }
            } );
            System.out.println( createPNode );
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
    }

    //Создание узла Genre в БД
    public void CreateNode(Genre genre) {
        try ( Session session = driver.session() ) {
            String createPNode = session.writeTransaction( new TransactionWork<String>() {
                @Override
                public String execute( Transaction tx )
                {
                    String gname = genre.getName();

                    Result result = tx.run( "CREATE (a:Genre {name: $gname, rate: '0'} ) " +
                                    "RETURN a.name + ', from node ' + id(a)",
                            parameters( "gname", gname) );
                    return result.single().get( 0 ).asString();
                }
            } );
            System.out.println( createPNode );
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
    }

    //Создание узла Date в БД
    public void CreateNode(Date date) {
        try ( Session session = driver.session() ) {
            String createPNode = session.writeTransaction( new TransactionWork<String>() {
                @Override
                public String execute( Transaction tx )
                {
                    String dyear = date.getYear();

                    Result result = tx.run( "CREATE (a:Date {year: $dyear, rate: '0'} ) " +
                                    "RETURN a.year + ', from node ' + id(a)",
                            parameters( "dyear", dyear) );
                    return result.single().get( 0 ).asString();
                }
            } );
            System.out.println( createPNode );
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
    }


    //Создание связи Person->Movie в БД
    public void CreateRelation(Person person, Movie movie, final String relation) {
        String prole = person.getRole();
        String pname = person.getName();
        String mname = movie.getName();

        //MATCH (n:User {name: '996676572'}),(m:Actor {name: 'Киану Ривз'}) MERGE (n)-[r:likes]->(m)
        //"MATCH (n:"+prole+" {name: '"+pname+"'}),(m:Movie {name: '"+mname+"'}) MERGE (n)-[r:likes]->(m)"

        try ( Session session = driver.session() ) {
            String createPM = session.writeTransaction( new TransactionWork<String>() {
                @Override
                public String execute( Transaction tx )
                {
                    Result result = tx.run( "MATCH (n:"+prole+" {name: '"
                                    +pname+"'}),(m:Movie {name: '"+mname+"'}) MERGE (n)-[r:"+relation+"]->(m) RETURN type(r)",
                            parameters( "pname", pname, "mname", mname,
                                    "relation", relation ) );
                    return result.single().get( 0 ).asString();
                }
            } );
            System.out.println( createPM );
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
    }

    //Создание связи Movie->Person в БД
    public void CreateRelation( Movie movie, Person person, final String relation) {
        try ( Session session = driver.session() ) {
            String createMP = session.writeTransaction( new TransactionWork<String>() {
                @Override
                public String execute( Transaction tx )
                {
                    String prole = person.getRole();
                    String pname = person.getName();
                    String mname = movie.getName();

                    Result result = tx.run( "MATCH (n:Movie {name: '"
                                    +mname+"'}),(m:"+prole+" {name: '"
                                    +pname+"'}) MERGE (n)-[r:"+relation+"]->(m) RETURN type(r)",
                            parameters( "mname", mname, "pname", pname,
                                    "relation", relation ) );
                    return result.single().get( 0 ).asString();
                }
            } );
            System.out.println( createMP );
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
    }

    //Создание связи Person->Person в БД
    public void CreateRelation( Person per1, Person per2, final String relation) {
        try ( Session session = driver.session() ) {
            String createPP = session.writeTransaction( new TransactionWork<String>() {
                @Override
                public String execute( Transaction tx )
                {
                    String p1name = per1.getName();
                    String p2name = per2.getName();
                    String p1role = per1.getRole();
                    String p2role = per2.getRole();

                    Result result = tx.run( "MATCH (n:"+p1role+" {name: '"
                                    +p1name+"'}),(m:"
                                    +p2role+" {name: '"+p2name+"'}) MERGE (n)-[r:"+relation+"]->(m) RETURN type(r)",
                            parameters( "p1role", p1role, "p2name", p2role,
                                    "p1name", p1name, "p2name", p2name,
                                    "relation", relation ) );
                    return result.single().get( 0 ).asString();
                }
            } );
            System.out.println( createPP );
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
    }

    //Создание связи Person->Genre в БД
    public void CreateRelation( Person person, Genre genre, final String relation) {
        try ( Session session = driver.session() ) {
            String createPG = session.writeTransaction( new TransactionWork<String>() {
                @Override
                public String execute( Transaction tx )
                {
                    String pname = person.getName();
                    String prole = person.getRole();
                    String gname = genre.getName();

                    Result result = tx.run( "MATCH (n:"+prole+" {name: '"
                                    +pname+"'}),(m:Genre {name: '"
                                    +gname+"'}) MERGE (n)-[r:"+relation+"]->(m) RETURN type(r)",
                            parameters( "pname", pname, "gname", gname,
                                    "relation", relation ) );
                    return result.single().get( 0 ).asString();
                }
            } );
            System.out.println( createPG );
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
    }

    //Создание связи Movie->Genre в БД
    public void CreateRelation( Movie movie, Genre genre, final String relation) {
        try ( Session session = driver.session() ) {
            String createPG = session.writeTransaction( new TransactionWork<String>() {
                @Override
                public String execute( Transaction tx )
                {
                    String mname = movie.getName();
                    String gname = genre.getName();

                    Result result = tx.run( "MATCH (n:Movie {name: '"
                                    +mname+"'}),(m:Genre {name: '"
                                    +gname+"'}) MERGE (n)-[r:"+relation+"]->(m) RETURN type(r)",
                            parameters( "mname", mname, "gname", gname,
                                    "relation", relation ) );
                    return result.single().get( 0 ).asString();
                }
            } );
            System.out.println( createPG );
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
    }

    //Создание связи Movie->Date в БД
    public void CreateRelation( Movie movie, Date date, final String relation) {
        try ( Session session = driver.session() ) {
            String createPG = session.writeTransaction( new TransactionWork<String>() {
                @Override
                public String execute( Transaction tx )
                {
                    String mname = movie.getName();
                    String dyear = date.getYear();

                    Result result = tx.run( "MATCH (n:Movie {name: '"
                                    +mname+"'}),(m:Date {year: '"
                                    +dyear+"'}) MERGE (n)-[r:"+relation+"]->(m) RETURN type(r)",
                            parameters( "mname", mname, "dyear", dyear,
                                    "relation", relation ) );
                    return result.single().get( 0 ).asString();
                }
            } );
            System.out.println( createPG );
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
    }

    //Person->Date
    public void CreateRelation(Person person, Date date, final String relation) {
        String prole = person.getRole();
        String pname = person.getName();
        String dyear = date.getYear();

        try ( Session session = driver.session() ) {
            String createPM = session.writeTransaction( new TransactionWork<String>() {
                @Override
                public String execute( Transaction tx )
                {
                    Result result = tx.run( "MATCH (n:"+prole+" {name: '"
                                    +pname+"'}),(m:Date {year: '"+dyear+"'}) MERGE (n)-[r:"+relation+"]->(m) RETURN type(r)",
                            parameters( "pname", pname, "dyear", dyear,
                                    "relation", relation ) );
                    return result.single().get( 0 ).asString();
                }
            } );
            System.out.println( createPM );
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
    }

    //удаление узла Person в БД
    public void DeleteNode( Person person ) {
        try ( Session session = driver.session() ) {
            String greeting = session.writeTransaction( new TransactionWork<String>() {
                @Override
                public String execute( Transaction tx )
                {
                    String prole = person.getRole();
                    String pname = person.getName();
                    Result result = tx.run( "MATCH (n: " + prole + " {name: '" + pname + "'})" +
                            " DELETE n RETURN n.name",
                            parameters( "prole", prole, "pname", pname ) );
                    return result.single().get( 0 ).asString();
                }
            } );
            System.out.println( greeting );
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
    }

    //удаление узла Movie в БД
    public void DeleteNode( Movie movie ) {
        try ( Session session = driver.session() ) {
            String greeting = session.writeTransaction( new TransactionWork<String>() {
                @Override
                public String execute( Transaction tx )
                {
                    String mname = movie.getName();
                    Result result = tx.run( "MATCH (n: Movie {name: '" + mname + "'})" +
                                    " DELETE n RETURN n.name",
                            parameters( "mname", mname) );
                    return result.single().get( 0 ).asString();
                }
            } );
            System.out.println( greeting );
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
    }

    //удаление узла Date в БД
    public void DeleteNode( Date date ) {
        try ( Session session = driver.session() ) {
            String greeting = session.writeTransaction( new TransactionWork<String>() {
                @Override
                public String execute( Transaction tx )
                {
                    String dyear = date.getYear();
                    Result result = tx.run( "MATCH (n: Date {year: $dyear })" +
                                    " DELETE n RETURN n.year",
                            parameters( "dyear", dyear) );
                    return result.single().get( 0 ).asString();
                }
            } );
            System.out.println( greeting );
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
    }

    //удаление узла Genre в БД
    public void DeleteNode( Genre genre ) {
        try ( Session session = driver.session() ) {
            String greeting = session.writeTransaction( new TransactionWork<String>() {
                @Override
                public String execute( Transaction tx )
                {
                    String gname = genre.getName();
                    Result result = tx.run( "MATCH (n: Genre {name: $gname })" +
                                    " DELETE n RETURN n.name",
                            parameters( "gname", gname) );
                    return result.single().get( 0 ).asString();
                }
            } );
            System.out.println( greeting );
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
    }

    //удаление связи от узлов Person к Movie в БД
    public void DeleteRelation( Person person, Movie movie, final String relation ) {
        try ( Session session = driver.session() ) {
            String greeting = session.writeTransaction( new TransactionWork<String>() {
                @Override
                public String execute( Transaction tx )
                {
                    String pname = person.getName();
                    String mname = movie.getName();

                    Result result = tx.run( "MATCH (n {name: '"+
                                    pname + "'})-[r: " + relation + "]->(b {name: '" + mname +
                                    "'}) DELETE r RETURN n.name",
                            parameters( "pname", pname, "mname", mname, "relation", relation ) );
                    return result.single().get( 0 ).asString();
                }
            } );
            System.out.println( greeting );
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
    }

    //удаление связи от узлов Person к Genre в БД
    public void DeleteRelation( Person person, Genre genre, final String relation ) {
        try ( Session session = driver.session() ) {
            String greeting = session.writeTransaction( new TransactionWork<String>() {
                @Override
                public String execute( Transaction tx )
                {
                    String pname = person.getName();
                    String gname = genre.getName();

                    Result result = tx.run( "MATCH (n {name: '"+
                                    pname + "'})-[r:" + relation + "]->(b {name: '" + gname +
                                    "' }) DELETE r RETURN n.name",
                            parameters( "pname", pname, "gname", gname, "relation", relation ) );
                    return result.single().get( 0 ).asString();
                }
            } );
            System.out.println( greeting );
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
    }

    //удаление связи от узлов Person->Person в БД
    public void DeleteRelation( Person person1, Person person2, final String relation ) {
        try ( Session session = driver.session() ) {
            String greeting = session.writeTransaction( new TransactionWork<String>() {
                @Override
                public String execute( Transaction tx )
                {
                    String p1name = person1.getName();
                    String p2name = person2.getName();

                    Result result = tx.run( "MATCH (n {name: '" + p1name + "'})-[r:"
                                    + relation + "]->(b {name: '" + p2name + "' }) DELETE r RETURN n.name",
                            parameters( "p1name", p1name, "relation", relation, "p2name", p2name) );
                    return result.single().get( 0 ).asString();
                }
            } );
            System.out.println( greeting );
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
    }

    //удаление связи от узлов Movie->Genre в БД
    public void DeleteRelation( Movie movie, Genre genre, final String relation ) {
        try ( Session session = driver.session() ) {
            String greeting = session.writeTransaction( new TransactionWork<String>() {
                @Override
                public String execute( Transaction tx )
                {
                    String mname = movie.getName();
                    String gname = genre.getName();

                    Result result = tx.run( "MATCH (n {name: '"+
                                    mname + "'})-[r:" + relation + "]->(b {name: $gname}) DELETE r RETURN n.name",
                            parameters( "mname", mname, "relation", relation, "gname", gname ) );
                    return result.single().get( 0 ).asString();
                }
            } );
            System.out.println( greeting );
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
    }

    //удаление связи от узлов Movie->Person в БД
    public void DeleteRelation( Movie movie, Person person, final String relation ) {
        try ( Session session = driver.session() ) {
            String greeting = session.writeTransaction( new TransactionWork<String>() {
                @Override
                public String execute( Transaction tx )
                {
                    String mname = movie.getName();
                    String pname = person.getName();

                    Result result = tx.run( "MATCH (n {name: '"+
                                    mname + "'})-[r:" + relation + "]->(b {name: $pname}) DELETE r RETURN n.name",
                            parameters( "mname", mname, "relation", relation, "pname", pname ) );
                    return result.single().get( 0 ).asString();
                }
            } );
            System.out.println( greeting );
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
    }

    //удаление связи от узлов Movie->Date в БД
    public void DeleteRelation( Movie movie, Date date, final String relation ) {
        try ( Session session = driver.session() ) {
            String greeting = session.writeTransaction( new TransactionWork<String>() {
                @Override
                public String execute( Transaction tx )
                {
                    String mname = movie.getName();
                    String dyear = date.getYear();

                    Result result = tx.run( "MATCH (n {name: '"+
                                    mname + "'})-[r:" + relation + "]->(b {year: $dyear}) DELETE r RETURN n.name",
                            parameters( "mname", mname, "relation", relation, "dyear", dyear ) );
                    return result.single().get( 0 ).asString();
                }
            } );
            System.out.println( greeting );
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
    }

    //Найти узлы от узла Date
    public void FindNode( Date date, final String rel) {
        try ( Session session = driver.session() ) {
            String greeting = session.writeTransaction( new TransactionWork<String>() {
                @Override
                public String execute( Transaction tx )
                {
                    String dyear = date.getYear();

                        Result result = tx.run("MATCH (d:Date {year: $dyear})"
                                        + "<-[r:" + rel + "]" + "-(m) RETURN m.name",
                                parameters("dyear", dyear, "rel", rel));
                        return result.single().get(0).asString();
                }
            } );
            System.out.println( greeting );
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
    }

    //Найти узлы от узла Genre
    public void FindNode( Genre genre, final String rel, boolean isMovie ) {
        try ( Session session = driver.session() ) {
            String greeting = session.writeTransaction( new TransactionWork<String>() {
                @Override
                public String execute( Transaction tx )
                {
                    String gname = genre.getName();

                    if (isMovie) {
                        Result result = tx.run("MATCH (g:Genre {name: $gname})"
                                        + "<-[r:" + rel + "]" + "-(m) RETURN m.name",
                                parameters("gname", gname, "rel", rel));
                        return result.single().get(0).asString();

                    }
                    else {
                        Result result = tx.run( "MATCH (g:Genre {name: $gname})"
                                        + "<-[r:"+ rel +"]" + "-(m) RETURN m.name",
                                parameters( "gname", gname, "rel", rel ) );
                        return result.single().get( 0 ).asString();
                    }
                }
            } );
            System.out.println( greeting );
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
    }

    //Найти узлы от узла Person
    public String FindNode( Person person, final String rel, boolean isDir, boolean isMovie) {
        final String[] resultStr = {"result is null"};
        try ( Session session = driver.session() ) {
            String greeting = session.writeTransaction( new TransactionWork<String>() {
                @Override
                public String execute( Transaction tx )
                {
                    String pname = person.getName();
                    String prole = person.getRole();

                    if (isMovie && isDir) {
                        Result result = tx.run( "MATCH (p: "+prole+" {name: $pname})"
                                        + "<-[r:"+ rel +"]" + "-(m) RETURN m.name",
                                parameters( "prole", prole, "pname", pname, "rel", rel ) );
                        resultStr[0] = result.single().get( 0 ).asString();
                        return result.single().get( 0 ).asString();

                    }
                    if (isMovie) {
                        Result result = tx.run( "MATCH (p: "+prole+" {name: $pname})"
                                        + "-[r:"+ rel +"]" + "->(m) RETURN m.name",
                                parameters( "prole", prole, "pname", pname, "rel", rel ) );
                        resultStr[0] = result.single().get( 0 ).asString();
                        return result.single().get( 0 ).asString();

                    }
                    else {
                        Result result = tx.run( "MATCH (p: "+prole+" {name: $pname})"
                                + "<-[r:"+ rel +"]" + "-(m) RETURN m.name",
                                parameters( "prole", prole, "pname", pname, "rel", rel ) );
                        resultStr[0] = result.single().get( 0 ).asString();
                        return result.single().get( 0 ).asString();
                    }
                }
            } );
            System.out.println( greeting );
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
        return resultStr[0];
    }

    //Найти узлы от узла Person
    public void FindNode( User user, final String rel, boolean isMG) {
        try ( Session session = driver.session() ) {
            String greeting = session.writeTransaction( new TransactionWork<String>() {
                @Override
                public String execute( Transaction tx )
                {
                    String pname = user.getName();
                    String prole = user.getRole();

                    if (isMG) {
                        Result result = tx.run( "MATCH (p: "+prole+" {name: $pname})"
                                        + "-[r:"+ rel +"]" + "->(m) RETURN m.name LIMIT 1",
                                parameters( "prole", prole, "pname", pname, "rel", rel ) );
                        return result.single().get( 0 ).asString();

                    }
                    else {
                        Result result = tx.run( "MATCH (p: "+prole+" {name: $pname})"
                                        + "-[r:"+ rel +"]" + "->(m) RETURN m.name LIMIT 1",
                                parameters( "prole", prole, "pname", pname, "rel", rel ) );
                        return result.single().get( 0 ).asString();
                    }
                }
            } );
            System.out.println( greeting );
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
    }

    //Найти узлы от Movie
    public void FindNode( Movie movie, final String rel, boolean isDate, boolean isGenre ) {
        try ( Session session = driver.session() ) {
            String greeting = session.writeTransaction( new TransactionWork<String>() {
                @Override
                public String execute( Transaction tx )
                {
                    String mname = movie.getName();

                    if (isDate) {
                        Result result = tx.run( "MATCH (m:Movie {name: $mname})"
                                        + "-[:"+ rel + "]" + "->(d) RETURN d.year",
                                parameters( "mname", mname, "rel", rel ) );
                        return result.single().get( 0 ).asString() + "";
                    }
                    else if (isGenre) {
                        Result result = tx.run( "MATCH (m:Movie {name: $mname})"
                                        + "-[:"+ rel + "]" + "->(d) RETURN d.name",
                                parameters( "mname", mname, "rel", rel ) );
                        return result.single().get( 0 ).asString();
                    }
                    else {
                        Result result = tx.run( "MATCH (m:Movie {name: $mname})"
                                        + "<-[:"+ rel +"]" + "-(person) RETURN person.name",
                                parameters( "mname", mname, "rel", rel ) );
                        return result.single().get( 0 ).asString();
                    }

                }
            } );
            System.out.println( greeting );
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
    }

    //Найти конкретный узел
    public String FindNode( final String nodeName ) {
        final String[] resId = new String[1];
        try ( Session session = driver.session() ) {
            String greeting = session.writeTransaction( new TransactionWork<String>() {
                @Override
                public String execute( Transaction tx )
                {
                    Result result = tx.run( "MATCH (n {name: $nodeName}) RETURN n.id",
                            parameters( "nodeName", nodeName) );
                    resId[0] = result.single().get( 0 ).asString();
                    return result.single().get( 0 ).asString();
                }
            } );
            System.out.println( greeting );
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
        return resId[0];
    }

    //Получение рейтинга узла
    public String getRating(final String nodeName, boolean isActor, boolean isDirector,
                            boolean isMovie, boolean isGenre) {
        String res = "";

        String rolef = "";
        if (isActor) rolef = "Actor";
        else if (isDirector) rolef = "Director";
        else if (isMovie) rolef = "Movie";
        else if (isGenre) rolef = "Genre";
        final String role = rolef;

        try ( Session session = driver.session() ) {
            String query = session.writeTransaction( new TransactionWork<String>() {
                @Override
                public String execute( Transaction tx )
                {

                    Result result = tx.run( "MATCH (m:$role {name: '$nodeName'}) RETURN m.rate",
                            parameters("role", role, "nodeName", nodeName) );
                    return result.single().get( 0 ).asString();
                }
            } );
            System.out.println( query );
            res = query;
        }
        catch(Exception ex) {
            System.out.println(ex);
        }

        return res;
    }

    public void setRate(String role, String nodeName, String recalc) {

        try ( Session session = driver.session() ) {
            String query = session.writeTransaction( new TransactionWork<String>() {
                @Override
                public String execute( Transaction tx )
                {

                    Result result = tx.run( "MATCH (m:"+role+" {name: '"+nodeName+"'}) SET m.rate = '"+recalc+"'",
                            parameters("role", role, "nodeName", nodeName, "recalc", recalc ) );
                    System.out.println(result);
                    return "SET IS DONE";
                }
            } );
            System.out.println( query );
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
    }

    //Пересчет рейтинга узла
    public String recalculateRating(final String nodeName, boolean isActor, boolean isDirector,
                                  boolean isMovie, boolean isGenre) {
        Integer[] numbersUserLikes = new Integer[2];

        String rolef = "";
        if (isActor) rolef = "Actor";
        else if (isDirector) rolef = "Director";
        else if (isMovie) rolef = "Movie";
        else if (isGenre) rolef = "Genre";
        final String role = rolef;

        try ( Session session = driver.session() ) {
            String query = session.writeTransaction( new TransactionWork<String>() {
                @Override
                public String execute( Transaction tx )
                {
                    Result resultUsers = tx.run( "MATCH (n:User) return count(n)",
                            parameters( ) );
                    numbersUserLikes[0] = resultUsers.single().get( 0 ).asInt();
                    Result resultLikes = tx.run( "MATCH (n)-[r:likes]->(m:"+role+" {name: $nodeName})  return count(r)",
                            parameters("role", role, "nodeName", nodeName ) );
                    numbersUserLikes[1] = resultLikes.single().get( 0 ).asInt();
                    return "0";
                }
            } );
            System.out.println( query );
        }
        catch(Exception ex) {
            System.out.println(ex);
        }

        Double rec = Double.valueOf(numbersUserLikes[1]) / Double.valueOf(numbersUserLikes[0]);
        String recalc = String.format("%.2f", rec);

        System.out.println(role + " " + nodeName + " " + recalc);
        System.out.println("MATCH (m:"+role+" {name: '"+nodeName+"'}) SET m.rate = '"+recalc+"'");

        setRate(role, nodeName, recalc);
        return recalc;
    }

    public String[][] topMovies() {
        String[][] arrayResult = null;
        try ( Session session = driver.session() ) {
            Result myResult = session.run("MATCH (n:Movie) RETURN n.name, n.rate ORDER BY n.rate DESC LIMIT 10");

            List<Record> myRecords = myResult.list();
            int i = 0;
            arrayResult = new String[myRecords.size()][3];
            for( Record record: myRecords){
                arrayResult[i][0] = record.get("n.name").asString();
                arrayResult[i][1] = record.get("n.rate").asString();
                i++;
            }
        }
        catch(Exception ex) {
            System.out.println(ex);
        }

        return arrayResult;
    }

    public String[][] topActors() {
        String[][] arrayResult = null;
        try ( Session session = driver.session() ) {
            Result myResult = session.run("MATCH (n:Actor) RETURN n.name, n.rate ORDER BY n.rate DESC LIMIT 10");

            List<Record> myRecords = myResult.list();
            int i = 0;
            arrayResult = new String[myRecords.size()][3];
            for( Record record: myRecords){
                arrayResult[i][0] = record.get("n.name").asString();
                arrayResult[i][1] = record.get("n.rate").asString();
                i++;
            }
        }
        catch(Exception ex) {
            System.out.println(ex);
        }

        return arrayResult;
    }
    public String[][] topDirectors() {
        String[][] arrayResult = null;
        try ( Session session = driver.session() ) {
            Result myResult = session.run("MATCH (n:Director) RETURN n.name, n.rate ORDER BY n.rate DESC LIMIT 10");

            List<Record> myRecords = myResult.list();
            int i = 0;
            arrayResult = new String[myRecords.size()][3];
            for( Record record: myRecords){
                arrayResult[i][0] = record.get("n.name").asString();
                arrayResult[i][1] = record.get("n.rate").asString();
                i++;
            }
        }
        catch(Exception ex) {
            System.out.println(ex);
        }

        return arrayResult;
    }

    public String[][] topGenres() {
        String[][] arrayResult = null;
        try ( Session session = driver.session() ) {
            Result myResult = session.run("MATCH (n:Genre) RETURN n.name, n.rate ORDER BY n.rate DESC LIMIT 10");

            List<Record> myRecords = myResult.list();
            int i = 0;
            //тут ниже было 3, а не 2
            arrayResult = new String[myRecords.size()][2];
            for( Record record: myRecords){
                arrayResult[i][0] = record.get("n.name").asString();
                arrayResult[i][1] = record.get("n.rate").asString();
                i++;
            }
        }
        catch(Exception ex) {
            System.out.println(ex);
        }

        return arrayResult;
    }

    public String[]getAllGenres() {
        String[] arrayResult = null;
        try ( Session session = driver.session() ) {
            Result myResult = session.run("MATCH (n:Genre) RETURN n.name LIMIT 100");

            List<Record> myRecords = myResult.list();
            int i = 0;
            arrayResult = new String[myRecords.size()];
            for( Record record: myRecords){
                arrayResult[i] = record.get("n.name").asString();
                i++;
            }
        }
        catch(Exception ex) {
            System.out.println(ex);
        }

        return arrayResult;
    }

    public String getMoviesByGenres() {
        String result = "";

        String[][] arrayResult = null;

        String[] genres = getAllGenres();

        for (int i = 0; i < genres.length; i++) {

            try ( Session session = driver.session() ) {
                Result myResult = session.run(
                        "MATCH (n:Movie)-->(m:Genre {name:'"+ genres[i] + "'}) " +
                                "RETURN n.name, n.rate ORDER BY n.rate DESC LIMIT 10");
                System.out.println("MATCH (n:Movie)-->(m:Genre {name:' "+ genres[i] + "'}) " +
                        "RETURN n.name, n.rate ORDER BY n.rate DESC LIMIT 10");

                List<Record> myRecords = myResult.list();
                int j = 0;
                //тут ниже было 3, а не 2
                //в данном случае 10 жанров и 10 фильмов в каждом
                arrayResult = new String[100][2];
                for( Record record: myRecords){
                    arrayResult[j][0] = record.get("n.name").asString();
                    arrayResult[j][1] = record.get("n.rate").asString();
                    j++;
                }

                result+= "**Жанр " + genres[i] + ":**\n";
                for (int k = 0; k < 10; k++) {
                    if (arrayResult[0][0] == null) {
                        result += "Ни один фильм данного жанра не получил отметку \"Нравится\" от пользователей.\n";
                        break;
                    }
                    else if (arrayResult[k][0] == null) {
                        break;
                    };
                    result += (k+1) + ". " + arrayResult[k][0] + " " + arrayResult[k][1] + "\n";
                }
            }
            catch(Exception ex) {
                System.out.println(ex);
            }

        }

        return result;
    }
}