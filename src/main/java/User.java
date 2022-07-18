public class User extends People {

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
                connector.CreateRelation( this, movie, "LIKES" );
            else {
                connector.DeleteRelation( this, movie, "LIKES" );
                String n = movie.getName();
                String rate = connector.recalculateRating(n,  false, true, false, false, true);
                movie.setRate(rate);
            }

            String n = movie.getName();
            String rate = connector.recalculateRating(n,  false, true, false, true, false);
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
                connector.CreateRelation( this, actor, "LIKES" );
            else
                connector.DeleteRelation( this, actor, "LIKES" );

            String n = actor.getName();
            String rate = connector.recalculateRating(n, true,  false, false, true, false);
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
                connector.CreateRelation( this, director, "LIKES" );
            else
                connector.DeleteRelation( this, director, "LIKES" );

            String n = director.getName();
            String rate = connector.recalculateRating(n,  true, false, false, true, false);
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
                connector.CreateRelation( this, genre, "LIKES" );
            else
                connector.DeleteRelation( this, genre, "LIKES" );

            String n = genre.getName();
            String rate = connector.recalculateRating(n, false, false,  true, true, false);
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
            connector.FindNode( this, "LIKES", true);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }

    //Найти любимых актеров и режиссеров
    public void findActNDir() {
        try ( Connector connector = new Connector( "bolt://localhost:7687", "neo4j", "root" ) )
        {
            connector.FindNode( this, "LIKES", false);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }

    //Найти любимые жанры
    public void findGenres() {
        try ( Connector connector = new Connector( "bolt://localhost:7687", "neo4j", "root" ) )
        {
            connector.FindNode( this, "LIKES", true);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }

}
