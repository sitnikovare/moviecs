public class Database {

    Movie mov1 = new Movie("Форрест Гамп");
    Movie mov2 = new Movie("Назад в будущее");
    Movie mov3 = new Movie("Крестный отец");
    Movie mov4 = new Movie("Волк с Уолл-стрит");
    Movie mov5 = new Movie("День сурка");
    Movie mov6 = new Movie("Братство кольца");
    Movie mov7 = new Movie("Линкольн для адвоката");
    Movie mov8 = new Movie("Рокки");
    Movie mov9 = new Movie("Матрица");
    Movie mov10 = new Movie("Леон");
    Movie mov11 = new Movie("Васаби");
    Movie mov12 = new Movie("Убить Билла");
    Movie mov13 = new Movie("Пятый элемент");

    Actor act1 = new Actor("Жан Рено");
    Actor act2 = new Actor("Билл Мюррей");
    Actor act3 = new Actor("Аль Пачино");
    Actor act4 = new Actor("Мэттью Макконехи");
    Actor act5 = new Actor("Уильям Мэйси");
    Actor act6 = new Actor("Сами Насери");
    Actor act7 = new Actor("Винни Джонс");
    Actor act8 = new Actor("Джейсон Стейтем");
    Actor act9 = new Actor("Кристофер Ллойд");
    Actor act10 = new Actor("Майкл Джей Фокс");
    Actor act11 = new Actor("Киану Ривз");
    Actor act12 = new Actor("Ума Турман");
    Actor act13 = new Actor("Натали Портман");
    Actor act14 = new Actor("Вайнона Райдер");
    Actor act15 = new Actor("Брюс Уиллис");

    Director dir1 = new Director("Люк Бессон");
    Director dir2 = new Director("Сестры Вачовски");
    Director dir3 = new Director("Роберт Земекис");
    Director dir4 = new Director("Квентин Тарантино");
    Director dir5 = new Director("Джеймс Кемерон");
    Director dir6 = new Director("Стивен Спилберг");
    Director dir7 = new Director("Гай Ричи");
    Director dir8 = new Director("Кристофер Нолан");
    Director dir9 = new Director("Гароль Рамис");
    Director dir10 = new Director("Жерар Кравчик");
    Director dir11 = new Director("Девид Линч");
    Director dir12 = new Director("Фрэнсис Форд Коппола");
    Director dir13 = new Director("Мартин Скорсезе");
    Director dir14 = new Director("Питер Джексон");
    Director dir15 = new Director("Харольд Рэмис");
    Director dir16 = new Director("Брэд Фурман");

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

    public Database() {};

    public void initDatabase() {
        initNodes();
        createRel_directedBy();
        createRel_isGenre();
        createRel_PlaysIn();
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
        tS.likes(dir16, true);

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
        dir16.initInDB();

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
        mov7.DirectedBy(dir16, true);
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
        connector.recalculateRating(dir16.getName(), false, true, false, false);

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