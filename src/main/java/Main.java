import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {

    public static void main( String... args ) throws Exception
    {
       User tUs = new User("testInit");
       Admin tAdm = new Admin("testAdmin");
       Actor tAct = new Actor("testActor");
       Director tDir = new Director("testDir");
       Movie tMov = new Movie("testFilm");
       Genre tGen = new Genre("testGenre");
       Date tDat = new Date("2021");


       TelegramBotsApi telegram = new TelegramBotsApi(DefaultBotSession.class);
       Bot bot = new Bot();
       try {
           telegram.registerBot(bot);
       } catch (TelegramApiRequestException e) {
           e.printStackTrace();
       }


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

        tAct.playsIn(tMov, true);
        tMov.DirectedBy(tDir, true);
        tMov.isGenre(tGen, true);
        tMov.releasedIn(tDat, true);





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
    }
}
