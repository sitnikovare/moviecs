import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {

    public static void main( String... args ) throws Exception
    {

      Database moviecsDB = new Database();
//      moviecsDB.readFromFile("C:/Users/illus/Documents/CSIT/composer.txt");
//        moviecsDB.readFromFile("C:/Users/illus/Documents/CSIT/director.txt");
//        moviecsDB.readFromFile("C:/Users/illus/Documents/CSIT/writer.txt");
//        moviecsDB.readFromFile("C:/Users/illus/Documents/CSIT/actor.txt");
//        moviecsDB.readFromFile("C:/Users/illus/Documents/CSIT/actress.txt");

////        moviecsDB.initDatabase();
//        moviecsDB.createUsersRel();
//        moviecsDB.updateRate();


       TelegramBotsApi telegram = new TelegramBotsApi(DefaultBotSession.class);
       Bot bot = new Bot();
       try {
           telegram.registerBot(bot);
       } catch (TelegramApiRequestException e) {
           e.printStackTrace();
       }

    }
}
