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
                    String mtitle = movie.getTitle();

                    Result result = tx.run( "CREATE (a:Movie {title: $mtitle} ) " +
                                    "RETURN a.title + ', from node ' + id(a)",
                            parameters( "mtitle", mtitle) );
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
                    String gtitle = genre.getTitle();

                    Result result = tx.run( "CREATE (a:Genre {title: $gtitle} ) " +
                                    "RETURN a.title + ', from node ' + id(a)",
                            parameters( "gtitle", gtitle) );
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
                    String mtitle = movie.getTitle();

                    Result result = tx.run( "MATCH (a:" + prole +"), (b:Movie) " +
                                    "WHERE a.name = $pname " +
                                    "AND b.title = $mtitle " +
                                    "CREATE (a)-[r:" + relation + "]->(b) " +
                                    "RETURN type(r)",
                            parameters( "pname", pname, "mtitle", mtitle,
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
                    String mtitle = movie.getTitle();

                    Result result = tx.run( "MATCH (a:Movie), (b:" + prole + ") " +
                                    "WHERE a.title = $mtitle " +
                                    "AND b.name = $pname " +
                                    "CREATE (a)-[r:" + relation + "]->(b) " +
                                    "RETURN type(r)",
                            parameters( "mtitle", mtitle, "pname", pname,
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
                    String gtitle = genre.getTitle();

                    Result result = tx.run( "MATCH (a:" + prole + "), (b:Genre) " +
                                    "WHERE a.name = $pname " +
                                    "AND b.title = $gtitle " +
                                    "CREATE (a)-[r:" + relation + "]->(b) " +
                                    "RETURN type(r)",
                            parameters( "pname", pname, "gtitle", gtitle,
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
                    String mtitle = movie.getTitle();
                    String gtitle = genre.getTitle();

                    Result result = tx.run( "MATCH (a:Movie), (b:Genre) " +
                                    "WHERE a.title = $mtitle " +
                                    "AND b.title = $gtitle " +
                                    "CREATE (a)-[r:" + relation + "]->(b) " +
                                    "RETURN type(r)",
                            parameters( "mtitle", mtitle, "gtitle", gtitle,
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
                    String mtitle = movie.getTitle();
                    String dyear = date.getYear();

                    Result result = tx.run( "MATCH (a:Movie), (b:Date) " +
                                    "WHERE a.title = $mtitle " +
                                    "AND b.year = $dyear " +
                                    "CREATE (a)-[r:" + relation + "]->(b) " +
                                    "RETURN type(r)",
                            parameters( "mtitle", mtitle, "dyear", dyear,
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
                    String mtitle = movie.getTitle();
                    Result result = tx.run( "MATCH (n: Movie {title: '" + mtitle + "'})" +
                                    " DELETE n RETURN n.title",
                            parameters( "mtitle", mtitle) );
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
                    String gtitle = genre.getTitle();
                    Result result = tx.run( "MATCH (n: Genre {title: $gtitle })" +
                                    " DELETE n RETURN n.title",
                            parameters( "gtitle", gtitle) );
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
                    String mtitle = movie.getTitle();

                    Result result = tx.run( "MATCH (n {name: '"+
                                    pname + "'})-[r: " + relation + "]->(b {title: '" + mtitle +
                                    "'}) DELETE r RETURN n.name",
                            parameters( "pname", pname, "mtitle", mtitle, "relation", relation ) );
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
                    String gtitle = genre.getTitle();

                    Result result = tx.run( "MATCH (n {name: '"+
                                    pname + "'})-[r:" + relation + "]->(b {title: '" + gtitle +
                                    "' }) DELETE r RETURN n.name",
                            parameters( "pname", pname, "gtitle", gtitle, "relation", relation ) );
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
                    String mtitle = movie.getTitle();
                    String gtitle = genre.getTitle();

                    Result result = tx.run( "MATCH (n {title: '"+
                                    mtitle + "'})-[r:" + relation + "]->(b {title: $gtitle}) DELETE r RETURN n.title",
                            parameters( "mtitle", mtitle, "relation", relation, "gtitle", gtitle ) );
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
                    String mtitle = movie.getTitle();
                    String pname = person.getName();

                    Result result = tx.run( "MATCH (n {title: '"+
                                    mtitle + "'})-[r:" + relation + "]->(b {name: $pname}) DELETE r RETURN n.title",
                            parameters( "mtitle", mtitle, "relation", relation, "pname", pname ) );
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
                    String mtitle = movie.getTitle();
                    String dyear = date.getYear();

                    Result result = tx.run( "MATCH (n {title: '"+
                                    mtitle + "'})-[r:" + relation + "]->(b {year: $dyear}) DELETE r RETURN n.title",
                            parameters( "mtitle", mtitle, "relation", relation, "dyear", dyear ) );
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
                                        + "<-[r:" + rel + "]" + "-(m) RETURN m.title",
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
                    String gtitle = genre.getTitle();

                    if (isMovie) {
                        Result result = tx.run("MATCH (g:Genre {title: $gtitle})"
                                        + "<-[r:" + rel + "]" + "-(m) RETURN m.title",
                                parameters("gtitle", gtitle, "rel", rel));
                        return result.single().get(0).asString();

                    }
                    else {
                        Result result = tx.run( "MATCH (g:Genre {title: $gtitle})"
                                        + "<-[r:"+ rel +"]" + "-(m) RETURN m.name",
                                parameters( "gtitle", gtitle, "rel", rel ) );
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
    public void FindNode( Person person, final String rel, boolean isDir, boolean isMovie) {
        try ( Session session = driver.session() ) {
            String greeting = session.writeTransaction( new TransactionWork<String>() {
                @Override
                public String execute( Transaction tx )
                {
                    String pname = person.getName();
                    String prole = person.getRole();

                    if (isMovie && isDir) {
                        Result result = tx.run( "MATCH (p: "+prole+" {name: $pname})"
                                        + "<-[r:"+ rel +"]" + "-(m) RETURN m.title",
                                parameters( "prole", prole, "pname", pname, "rel", rel ) );
                        return result.single().get( 0 ).asString();

                    }
                    if (isMovie) {
                        Result result = tx.run( "MATCH (p: "+prole+" {name: $pname})"
                                        + "-[r:"+ rel +"]" + "->(m) RETURN m.title",
                                parameters( "prole", prole, "pname", pname, "rel", rel ) );
                        return result.single().get( 0 ).asString();

                    }
                    else {
                        Result result = tx.run( "MATCH (p: "+prole+" {name: $pname})"
                                + "<-[r:"+ rel +"]" + "-(m) RETURN m.name",
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
                                        + "-[r:"+ rel +"]" + "->(m) RETURN m.title LIMIT 1",
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
                    String mtitle = movie.getTitle();

                    if (isDate) {
                        Result result = tx.run( "MATCH (m:Movie {title: $mtitle})"
                                        + "-[:"+ rel + "]" + "->(d) RETURN d.year",
                                parameters( "mtitle", mtitle, "rel", rel ) );
                        return result.single().get( 0 ).asString() + "";
                    }
                    else if (isGenre) {
                        Result result = tx.run( "MATCH (m:Movie {title: $mtitle})"
                                        + "-[:"+ rel + "]" + "->(d) RETURN d.title",
                                parameters( "mtitle", mtitle, "rel", rel ) );
                        return result.single().get( 0 ).asString();
                    }
                    else {
                        Result result = tx.run( "MATCH (m:Movie {title: $mtitle})"
                                        + "<-[:"+ rel +"]" + "-(person) RETURN person.name",
                                parameters( "mtitle", mtitle, "rel", rel ) );
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



}