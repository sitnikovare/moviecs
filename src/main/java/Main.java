public class Main {

    public static void main( String... args ) throws Exception
    {
       User tUs = new User("testInit");
       Actor tAct = new Actor("testActor");
       Director tDir = new Director("testDir");
       Movie tMov = new Movie("testFilm");
       Genre tGen = new Genre("testGenre");

       /*
       Инициализация узлов в БД
       tUs.initInDB();
       tAct.initInDB();
       tDir.initInDB();
       tMov.initInDB();
       tGen.initInDB();
       */

        /*
        Создание всех пользовательских связей
        tUs.likes(tAct);
        tUs.likes(tDir);
        tUs.likes(tMov);
        tUs.likes(tGen);
        */

        /*
        Создание остальных возможных связей
        tAct.playsIn(tMov);
        tMov.DirectedBy(tDir);
        tMov.isGenre(tGen);
        */

    }
}
