import com.sun.tools.internal.xjc.model.SymbolSpace;
import org.neo4j.driver.*;


import org.neo4j.driver.AuthTokens;
        import org.neo4j.driver.Driver;
        import org.neo4j.driver.GraphDatabase;
        import org.neo4j.driver.Result;
        import org.neo4j.driver.Session;
        import org.neo4j.driver.Transaction;
        import org.neo4j.driver.TransactionWork;

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
                                    "{name: $name} ) " +
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

                    Result result = tx.run( "CREATE (a:Movie {name: $mname} ) " +
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

                    Result result = tx.run( "CREATE (a:Genre {name: $gname} ) " +
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

                    Result result = tx.run( "CREATE (a:Date {year: $dyear} ) " +
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
    public void CreateRelation( Person person, Movie movie, final String relation) {
        try ( Session session = driver.session() ) {
            String createPM = session.writeTransaction( new TransactionWork<String>() {
                @Override
                public String execute( Transaction tx )
                {
                    String prole = person.getRole();
                    String pname = person.getName();
                    String mname = movie.getName();

                    Result result = tx.run( "MATCH (a:" + prole +"), (b:Movie) " +
                                    "WHERE a.name = $pname " +
                                    "AND b.name = $mname " +
                                    "CREATE (a)-[r:" + relation + "]->(b) " +
                                    "RETURN type(r)",
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

                    Result result = tx.run( "MATCH (a:Movie), (b:" + prole + ") " +
                                    "WHERE a.name = $mname " +
                                    "AND b.name = $pname " +
                                    "CREATE (a)-[r:" + relation + "]->(b) " +
                                    "RETURN type(r)",
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

                    Result result = tx.run( "MATCH (a:" + p1role + "), (b:" + p2role + ") " +
                                    "WHERE a.name = $p1name " +
                                    "AND b.name = $p2name " +
                                    "CREATE (a)-[r:" + relation + "]->(b) " +
                                    "RETURN type(r)",
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

                    Result result = tx.run( "MATCH (a:" + prole + "), (b:Genre) " +
                                    "WHERE a.name = $pname " +
                                    "AND b.name = $gname " +
                                    "CREATE (a)-[r:" + relation + "]->(b) " +
                                    "RETURN type(r)",
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

                    Result result = tx.run( "MATCH (a:Movie), (b:Genre) " +
                                    "WHERE a.name = $mname " +
                                    "AND b.name = $gname " +
                                    "CREATE (a)-[r:" + relation + "]->(b) " +
                                    "RETURN type(r)",
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

                    Result result = tx.run( "MATCH (a:Movie), (b:Date) " +
                                    "WHERE a.name = $mname " +
                                    "AND b.year = $dyear " +
                                    "CREATE (a)-[r:" + relation + "]->(b) " +
                                    "RETURN type(r)",
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
}