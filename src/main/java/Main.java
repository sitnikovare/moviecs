public class Main {

    public static void main( String... args ) throws Exception
    {
       User tUs = new User("testUser");
       Admin tAdm = new Admin("testAdmin");
       Actor tAct = new Actor("testActor");
       Director tDir = new Director("testDir");
       Movie tMov = new Movie("testFilm");
       Genre tGen = new Genre("testGenre");
       Date tDat = new Date(2021);

       /*
       Init nodes in DB
       tUs.initInDB();
       tAct.initInDB();
       tDir.initInDB();
       tMov.initInDB();
       tGen.initInDB();
       tAdm.initInDB();
       tDat.initInDB();
       */


        /*
        //Create all User relations
        tUs.likes(tAct, true);
        tUs.likes(tDir, true);
        tUs.likes(tMov, true);
        tUs.likes(tGen, true);
        //Create other relations
        tAct.playsIn(tMov);
        tMov.DirectedBy(tDir);
        tMov.isGenre(tGen);
        tMov.releasedIn(tDat);
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

    }
}
