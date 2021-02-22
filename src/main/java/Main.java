public class Main {

    public static void main( String... args ) throws Exception
    {
       User tUs = new User("testInit");
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
        Create all User relations
        tUs.likes(tAct);
        tUs.likes(tDir);
        tUs.likes(tMov);
        tUs.likes(tGen);
        */

        /*
        Create other relations
        tAct.playsIn(tMov);
        tMov.DirectedBy(tDir);
        tMov.isGenre(tGen);
        tMov.releasedIn(tDat);
        */


    }
}
