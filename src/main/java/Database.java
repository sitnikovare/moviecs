public class Database {

    //TODO: добавить сюда country & city

    Movie mov1 = new Movie("Форрест Гамп"); //1994
    Movie mov2 = new Movie("Назад в будущее"); //1985
    Movie mov3 = new Movie("Крестный отец"); //1972
    Movie mov4 = new Movie("Волк с Уолл-стрит"); //2013
    Movie mov5 = new Movie("День сурка"); //1993
    Movie mov6 = new Movie("Братство кольца"); //2001
    Movie mov7 = new Movie("Линкольн для адвоката"); //2011
    Movie mov8 = new Movie("Рокки"); //1976
    Movie mov9 = new Movie("Матрица"); //1999
    Movie mov10 = new Movie("Леон"); //1994
    Movie mov11 = new Movie("Васаби"); //2001
    Movie mov12 = new Movie("Убить Билла"); //2003
    Movie mov13 = new Movie("Пятый элемент"); //1997

    Actor act1 = new Actor("Жан Рено"); //1948
    Actor act2 = new Actor("Билл Мюррей"); //1950
    Actor act3 = new Actor("Аль Пачино"); //1940
    Actor act4 = new Actor("Мэттью Макконехи"); //1969
    Actor act5 = new Actor("Уильям Мэйси"); //1950
    Actor act6 = new Actor("Сами Насери"); //1961
    Actor act7 = new Actor("Винни Джонс"); //1965
    Actor act8 = new Actor("Джейсон Стейтем"); //1967
    Actor act9 = new Actor("Кристофер Ллойд"); //1938
    Actor act10 = new Actor("Майкл Джей Фокс"); //1961
    Actor act11 = new Actor("Киану Ривз"); //1964
    Actor act12 = new Actor("Ума Турман"); //1970
    Actor act13 = new Actor("Натали Портман"); //1981
    Actor act14 = new Actor("Вайнона Райдер"); //1971
    Actor act15 = new Actor("Брюс Уиллис"); //1955

    Director dir1 = new Director("Люк Бессон"); //1959
    Director dir2 = new Director("Сестры Вачовски"); //1965, 1967
    Director dir3 = new Director("Роберт Земекис"); //1951
    Director dir4 = new Director("Квентин Тарантино"); //1963
    Director dir5 = new Director("Джеймс Кемерон"); //1954
    Director dir6 = new Director("Стивен Спилберг"); //1946
    Director dir7 = new Director("Гай Ричи"); //1968
    Director dir8 = new Director("Кристофер Нолан"); //1970
    Director dir9 = new Director("Гарольд Рамис"); //1944
    Director dir10 = new Director("Жерар Кравчик"); //1953
    Director dir11 = new Director("Дэвид Линч"); //1946
    Director dir12 = new Director("Фрэнсис Форд Коппола"); //1939
    Director dir13 = new Director("Мартин Скорсезе"); //1942
    Director dir14 = new Director("Питер Джексон"); //1961
    Director dir15 = new Director("Брэд Фурман"); //???

    Genre gen1 = new Genre("Триллер");
    Genre gen2 = new Genre("Детектив");
    Genre gen3 = new Genre("Ужасы");
    Genre gen4 = new Genre("Антиутопия");
    Genre gen5 = new Genre("Комедия");
    Genre gen6 = new Genre("Драма");
    Genre gen7 = new Genre("Вестерн");
    Genre gen8 = new Genre("Фантастика");
    Genre gen9 = new Genre("Боевик");
    Genre gen10 = new Genre("Нуар");

    Date dt1994 = new Date("1994");
    Date dt1985 = new Date("1985");
    Date dt1975 = new Date("1975");
    Date dt1993 = new Date("1993");
    Date dt2013 = new Date("2013");
    Date dt2001 = new Date("2001");
    Date dt2011 = new Date("2011");
    Date dt1976 = new Date("1976");
    Date dt1999 = new Date("1999");
    Date dt2003 = new Date("2003");
    Date dt1997 = new Date("1997");
    Date dt1948 = new Date("1948");
    Date dt1950 = new Date("1950");
    Date dt1940 = new Date("1940");
    Date dt1969 = new Date("1969");
    Date dt1961 = new Date("1961");
    Date dt1965 = new Date("1965");
    Date dt1967 = new Date("1967");
    Date dt1938 = new Date("1938");
    Date dt1964 = new Date("1964");
    Date dt1970 = new Date("1970");
    Date dt1981 = new Date("1981");
    Date dt1971 = new Date("1971");
    Date dt1955 = new Date("1955");
    Date dt1959 = new Date("1959");
    Date dt1951 = new Date("1951");
    Date dt1963 = new Date("1963");
    Date dt1954 = new Date("1954");
    Date dt1946 = new Date("1946");
    Date dt1939 = new Date("1939");
    Date dt1942 = new Date("1942");
    Date dt1972 = new Date("1972");
    Date dt1968 = new Date("1968");
    Date dt1944 = new Date("1944");
    Date dt1953 = new Date("1953");

    public Database() {};

    public void initDatabase() {
        initNodes();
        createRel_directedBy();
        createRel_isGenre();
        createRel_PlaysIn();
        createRel_bornIn();
        createRel_releasedIn();
    }

    public void createUsersRel() {
        User tI = new User("449084446");
        User tM = new User("1182997916");
        User tS = new User("996676572");

//        tI.initInDB();
//        tM.initInDB();
//        tS.initInDB();

        tS.likes(mov1, true);
        tS.likes(mov2, true);
        tS.likes(mov10, true);
        tS.likes(mov3, true);
        tS.likes(mov4, true);
        tS.likes(mov5, true);
        tS.likes(mov6, true);
        tS.likes(mov7, true);
        tS.likes(mov8, true);

        tS.likes(act1, true);
        tS.likes(act2, true);
        tS.likes(act3, true);
        tS.likes(act4, true);
        tS.likes(act5, true);
        tS.likes(act6, true);
        tS.likes(act7, true);
        tS.likes(act8, true);
        tS.likes(act9, true);
        tS.likes(act10, true);

        tS.likes(dir3, true);
        tS.likes(dir8, true);
        tS.likes(dir5, true);
        tS.likes(dir1, true);
        tS.likes(dir9, true);
        tS.likes(dir10, true);
        tS.likes(dir12, true);
        tS.likes(dir7, true);
        tS.likes(dir15, true);

        tS.likes(gen5, true);
        tS.likes(gen1, true);
        tS.likes(gen6, true);
        tS.likes(gen7, true);

        tM.likes(mov1, true);
        tM.likes(mov10, true);
        tM.likes(mov4, true);
        tM.likes(mov9, true);
        tM.likes(mov11, true);
        tM.likes(mov13, true);
        tM.likes(mov12, true);

        tM.likes(act15, true);
        tM.likes(act11, true);
        tM.likes(act12, true);
        tM.likes(act1, true);
        tM.likes(act8, true);

        tM.likes(dir1, true);
        tM.likes(dir2, true);
        tM.likes(dir3, true);
        tM.likes(dir4, true);
        tM.likes(dir5, true);
        tM.likes(dir6, true);

        tM.likes(gen1, true);
        tM.likes(gen9, true);
        tM.likes(gen8, true);
        tM.likes(gen5, true);
    }

    public void initNodes() {
        mov1.initInDB();
        mov2.initInDB();
        mov3.initInDB();
        mov4.initInDB();
        mov5.initInDB();
        mov6.initInDB();
        mov7.initInDB();
        mov8.initInDB();
        mov9.initInDB();
        mov10.initInDB();
        mov11.initInDB();
        mov12.initInDB();
        mov13.initInDB();

        act1.initInDB();
        act2.initInDB();
        act3.initInDB();
        act4.initInDB();
        act5.initInDB();
        act6.initInDB();
        act7.initInDB();
        act8.initInDB();
        act9.initInDB();
        act10.initInDB();
        act11.initInDB();
        act12.initInDB();
        act13.initInDB();
        act14.initInDB();
        act15.initInDB();

        dir1.initInDB();
        dir2.initInDB();
        dir3.initInDB();
        dir4.initInDB();
        dir5.initInDB();
        dir6.initInDB();
        dir7.initInDB();
        dir8.initInDB();
        dir9.initInDB();
        dir10.initInDB();
        dir11.initInDB();
        dir12.initInDB();
        dir13.initInDB();
        dir14.initInDB();
        dir15.initInDB();

        gen1.initInDB();
        gen2.initInDB();
        gen3.initInDB();
        gen4.initInDB();
        gen5.initInDB();
        gen6.initInDB();
        gen7.initInDB();
        gen8.initInDB();
        gen9.initInDB();
        gen10.initInDB();

        dt1994.initInDB();
        dt1985.initInDB();
        dt1975.initInDB();
        dt1993.initInDB();
        dt2013.initInDB();
        dt2001.initInDB();
        dt2011.initInDB();
        dt1976.initInDB();
        dt1999.initInDB();
        dt2003.initInDB();
        dt1997.initInDB();
        dt1948.initInDB();
        dt1950.initInDB();
        dt1940.initInDB();
        dt1969.initInDB();
        dt1961.initInDB();
        dt1965.initInDB();
        dt1967.initInDB();
        dt1938.initInDB();
        dt1964.initInDB();
        dt1970.initInDB();
        dt1981.initInDB();
        dt1971.initInDB();
        dt1955.initInDB();
        dt1959.initInDB();
        dt1951.initInDB();
        dt1963.initInDB();
        dt1954.initInDB();
        dt1946.initInDB();
        dt1939.initInDB();
        dt1942.initInDB();
        dt1972.initInDB();
        dt1968.initInDB();
        dt1944.initInDB();
        dt1953.initInDB();
    }

    public void createRel_PlaysIn() {
        act1.playsIn( mov10, true);
        act1.playsIn(mov11, true);
        act2.playsIn(mov5, true);
        act3.playsIn(mov3, true);
        act4.playsIn(mov7, true);
        act5.playsIn(mov7, true);
        act6.playsIn(mov10, true);
        act9.playsIn(mov2, true);
        act10.playsIn(mov2, true);
        act11.playsIn(mov9, true);
        act15.playsIn(mov13, true);
    }

    public void createRel_directedBy() {
        mov1.DirectedBy(dir3, true);
        mov2.DirectedBy(dir3, true);
        mov3.DirectedBy(dir12, true);
        mov4.DirectedBy(dir13, true);
        mov6.DirectedBy(dir14, true);
        mov7.DirectedBy(dir15, true);
        mov9.DirectedBy(dir2, true);
        mov10.DirectedBy(dir1, true);
        mov11.DirectedBy(dir10, true);
        mov13.DirectedBy(dir1, true);
        mov12.DirectedBy(dir4, true);
    }

    public void createRel_isGenre() {
        mov1.isGenre(gen6, true);
        mov1.isGenre(gen5, true);
        mov2.isGenre(gen8, true);
        mov2.isGenre(gen5, true);
        mov3.isGenre(gen6, true);
        mov4.isGenre(gen6, true);
        mov4.isGenre(gen5, true);
        mov6.isGenre(gen6, true);
        mov7.isGenre(gen6, true);
        mov7.isGenre(gen2, true);
        mov9.isGenre(gen8, true);
        mov9.isGenre(gen9, true);
        mov10.isGenre(gen9, true);
        mov10.isGenre(gen6, true);
        mov10.isGenre(gen1, true);
        mov11.isGenre(gen9, true);
        mov11.isGenre(gen1, true);
        mov11.isGenre(gen6, true);
        mov11.isGenre(gen5, true);
        mov13.isGenre(gen8, true);
        mov13.isGenre(gen9, true);
        mov13.isGenre(gen1, true);
        mov13.isGenre(gen5, true);
        mov12.isGenre(gen9, true);
        mov12.isGenre(gen1, true);
        mov12.isGenre(gen6, true);
    }

    public void createRel_bornIn() {
        act1.bornIn(dt1948);
        act2.bornIn(dt1950);
        act3.bornIn(dt1940);
        act4.bornIn(dt1969);
        act5.bornIn(dt1950);
        act6.bornIn(dt1961);
        act7.bornIn(dt1965);
        act8.bornIn(dt1967);
        act9.bornIn(dt1938);
        act10.bornIn(dt1961);
        act11.bornIn(dt1964);
        act12.bornIn(dt1970);
        act13.bornIn(dt1981);
        act14.bornIn(dt1971);
        act15.bornIn(dt1955);

        dir1.bornIn(dt1959);
        dir2.bornIn(dt1965);
        dir2.bornIn(dt1967);
        dir3.bornIn(dt1951);
        dir4.bornIn(dt1963);
        dir5.bornIn(dt1954);
        dir6.bornIn(dt1946);
        dir7.bornIn(dt1968);
        dir8.bornIn(dt1970);
        dir9.bornIn(dt1944);
        dir10.bornIn(dt1953);
        dir11.bornIn(dt1946);
        dir12.bornIn(dt1939);
        dir13.bornIn(dt1942);
        dir14.bornIn(dt1961);
    }

    public void createRel_releasedIn() {
        mov1.releasedIn(dt1994, true);
        mov2.releasedIn(dt1985, true);
        mov3.releasedIn(dt1972, true);
        mov4.releasedIn(dt2013, true);
        mov5.releasedIn(dt1993, true);
        mov6.releasedIn(dt2001, true);
        mov7.releasedIn(dt2011, true);
        mov8.releasedIn(dt1976, true);
        mov9.releasedIn(dt1999, true);
        mov10.releasedIn(dt1994, true);
        mov11.releasedIn(dt2001, true);
        mov12.releasedIn(dt2003, true);
        mov13.releasedIn(dt1997, true);
    }



    public void updateRate() {
    try (Connector connector = new Connector("bolt://localhost:7687", "neo4j", "root")) {
        connector.recalculateRating(mov1.getName(), false, false, true, false);
        connector.recalculateRating(mov2.getName(), false, false, true, false);
        connector.recalculateRating(mov3.getName(), false, false, true, false);
        connector.recalculateRating(mov4.getName(), false, false, true, false);
        connector.recalculateRating(mov5.getName(), false, false, true, false);
        connector.recalculateRating(mov6.getName(), false, false, true, false);
        connector.recalculateRating(mov7.getName(), false, false, true, false);
        connector.recalculateRating(mov8.getName(), false, false, true, false);
        connector.recalculateRating(mov9.getName(), false, false, true, false);
        connector.recalculateRating(mov10.getName(), false, false, true, false);
        connector.recalculateRating(mov11.getName(), false, false, true, false);
        connector.recalculateRating(mov12.getName(), false, false, true, false);

        connector.recalculateRating(act1.getName(), true, false, false, false);
        connector.recalculateRating(act2.getName(), true, false, false, false);
        connector.recalculateRating(act3.getName(), true, false, false, false);
        connector.recalculateRating(act4.getName(), true, false, false, false);
        connector.recalculateRating(act5.getName(), true, false, false, false);
        connector.recalculateRating(act6.getName(), true, false, false, false);
        connector.recalculateRating(act7.getName(), true, false, false, false);
        connector.recalculateRating(act8.getName(), true, false, false, false);
        connector.recalculateRating(act9.getName(), true, false, false, false);
        connector.recalculateRating(act10.getName(), true, false, false, false);
        connector.recalculateRating(act11.getName(), true, false, false, false);
        connector.recalculateRating(act12.getName(), true, false, false, false);
        connector.recalculateRating(act13.getName(), true, false, false, false);
        connector.recalculateRating(act14.getName(), true, false, false, false);
        connector.recalculateRating(act15.getName(), true, false, false, false);

        connector.recalculateRating(dir1.getName(), false, true, false, false);
        connector.recalculateRating(dir2.getName(), false, true, false, false);
        connector.recalculateRating(dir3.getName(), false, true, false, false);
        connector.recalculateRating(dir4.getName(), false, true, false, false);
        connector.recalculateRating(dir5.getName(), false, true, false, false);
        connector.recalculateRating(dir6.getName(), false, true, false, false);
        connector.recalculateRating(dir7.getName(), false, true, false, false);
        connector.recalculateRating(dir8.getName(), false, true, false, false);
        connector.recalculateRating(dir9.getName(), false, true, false, false);
        connector.recalculateRating(dir10.getName(), false, true, false, false);
        connector.recalculateRating(dir11.getName(), false, true, false, false);
        connector.recalculateRating(dir12.getName(), false, true, false, false);
        connector.recalculateRating(dir13.getName(), false, true, false, false);
        connector.recalculateRating(dir14.getName(), false, true, false, false);
        connector.recalculateRating(dir15.getName(), false, true, false, false);

        connector.recalculateRating(gen1.getName(), false, false, false, true);
        connector.recalculateRating(gen2.getName(), false, false, false, true);
        connector.recalculateRating(gen3.getName(), false, false, false, true);
        connector.recalculateRating(gen4.getName(), false, false, false, true);
        connector.recalculateRating(gen5.getName(), false, false, false, true);
        connector.recalculateRating(gen6.getName(), false, false, false, true);
        connector.recalculateRating(gen7.getName(), false, false, false, true);
        connector.recalculateRating(gen8.getName(), false, false, false, true);
        connector.recalculateRating(gen9.getName(), false, false, false, true);
        connector.recalculateRating(gen10.getName(), false, false, false, true);
    } catch (Exception ex) {
        System.out.println(ex);
    }
}

public String getTop(String knd) {
        String res = new String();

    try ( Connector connector = new Connector( "bolt://localhost:7687", "neo4j", "root" ) )
    {
        String[][] result = null;
        if (knd == "movies") {result = connector.topMovies();}
        else if (knd == "genres") {result = connector.topGenres();}
        else if (knd == "actors") {result = connector.topActors();}
        else if (knd == "directors") {result = connector.topDirectors();}

        for (int i = 0; i < 10; i++) {
            res += (i+1) + ". " + result[i][0] + " " + result[i][1] + "\n";
        }
    }
    catch (Exception ex) {
        System.out.println(ex);
    }
        return res;
}

    public String getMoviesByGenres() {
        String res = new String();

        try ( Connector connector = new Connector( "bolt://localhost:7687", "neo4j", "root" ) )
        {
            String result = connector.getMoviesByGenres();
            res = result;

        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        return res;
    }
}


//movie.initInDB();


//Init nodes in DB
/*
       tUs.initInDB();
       tAct.initInDB();
       tDir.initInDB();
       tMov.initInDB();
       tGen.initInDB();
       tAdm.initInDB();
       tDat.initInDB();
*/



//Create all User relations
/*
        tUs.likes(tAct, true);
        tUs.likes(tDir, true);
        tUs.likes(tMov, true);
        tUs.likes(tGen, true);

*/


//Create other relations
/*
        tAct.playsIn(tMov, true);
        tMov.DirectedBy(tDir, true);
        tMov.isGenre(tGen, true);
        tMov.releasedIn(tDat, true);
*/




//удаление всех остальных связей
        /*
        tUs.likes(tAct, false);
        tUs.likes(tDir, false);
        tUs.likes(tMov, false);
        tUs.likes(tGen, false);
        tAct.playsIn(tMov, false);
        tMov.DirectedBy(tDir, false);
        tMov.isGenre(tGen, false);
        tMov.releasedIn(tDat, false);
        */

//Удаление всех узлов
        /*
        tUs.deleteFromDB();
        tAct.deleteFromDB();
        tDir.deleteFromDB();
        tMov.deleteFromDB();
        tGen.deleteFromDB();
        tAdm.deleteFromDB();
        tDat.deleteFromDB();
        */

//Поиск относительно фильма
        /*
        tMov.findDate();
        tMov.findGenre();
        tMov.findDirector();
        tMov.findActor();
        tMov.findLikers();
         */

//Поиск относительно актера
        /*
        tAct.findMovies();
        tAct.findLikers();
         */

//Поиск относительно режиссера
        /*
        tDir.findMovies();
        tDir.findLikers();
         */

//Поиск относительно жанра
        /*
        tGen.findMovies();
        tGen.findLikers();
         */

//Поиск относительно даты
//tDat.findMovies();

//Поиск относительно юзера - БОЛЬШЕ ОДНОГО НЕ ВЕРНУТ
        /*
        tUs.findMovies();
        tUs.findActNDir();
        tUs.findGenres();
         */