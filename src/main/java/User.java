public class User extends Person {

    //TODO: доп.инфо - год и место рождения/проживания
    public User() {}
    public User(String n) {
        name = n;
        role = "User";
    }

    //Создание связи от User к Movie
    public void likes(Movie movie, boolean orNot) {
        try ( Connector connector = new Connector( "bolt://localhost:7687", "neo4j", "root" ) )
        {
            if (orNot)
                connector.CreateRelation( this, movie, "likes" );
            else
                connector.DeleteRelation( this, movie, "likes" );

            String n = movie.getName();
            String rate = connector.recalculateRating(n, false, false, true, false);
            movie.setRate(rate);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }

    //Создание связи от User к Person
    public void likes(Actor actor, boolean orNot) {
        try ( Connector connector = new Connector( "bolt://localhost:7687", "neo4j", "root" ) )
        {
            if (orNot)
                connector.CreateRelation( this, actor, "likes" );
            else
                connector.DeleteRelation( this, actor, "likes" );

            String n = actor.getName();
            String rate = connector.recalculateRating(n, true, false, false, false);
            actor.setRate(rate);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }

    //Создание связи от User к Person
    public void likes(Director director, boolean orNot) {
        try ( Connector connector = new Connector( "bolt://localhost:7687", "neo4j", "root" ) )
        {
            if (orNot)
                connector.CreateRelation( this, director, "likes" );
            else
                connector.DeleteRelation( this, director, "likes" );

            String n = director.getName();
            String rate = connector.recalculateRating(n, false, true, false, false);
            director.setRate(rate);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }

    //Создание связи от User к Genre
    public void likes(Genre genre, boolean orNot) {
        try ( Connector connector = new Connector( "bolt://localhost:7687", "neo4j", "root" ) )
        {
            if (orNot)
                connector.CreateRelation( this, genre, "likes" );
            else
                connector.DeleteRelation( this, genre, "likes" );

            String n = genre.getName();
            String rate = connector.recalculateRating(n, false, false, false, true);
            genre.setRate(rate);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }

    //Найти любимые фильмы
    public void findMovies() {
        try ( Connector connector = new Connector( "bolt://localhost:7687", "neo4j", "root" ) )
        {
            connector.FindNode( this, "likes", true);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }

    //Найти любимых актеров и режиссеров
    public void findActNDir() {
        try ( Connector connector = new Connector( "bolt://localhost:7687", "neo4j", "root" ) )
        {
            connector.FindNode( this, "likes", false);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }

    //Найти любимые жанры
    public void findGenres() {
        try ( Connector connector = new Connector( "bolt://localhost:7687", "neo4j", "root" ) )
        {
            connector.FindNode( this, "likes", true);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }

}
