import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;


public class Bot extends TelegramLongPollingBot {

    private User user;
    private Actor actor;
    private Director director;
    private Movie movie;
    private Genre genre;
    private Date date;

    private boolean fndMovie = false;
    private boolean fndActor = false;
    private boolean fndDirector = false;
    private boolean fndGenre = false;
    private boolean fndDate = false;

    Recommedation recom = new Recommedation( "bolt://localhost:7687", "neo4j", "root" );

    @Override
    //TODO: make a nice messages (bold font for example)
    public void onUpdateReceived(Update update) {
        update.getUpdateId();
        user = new User(String.valueOf(update.getMessage().getChatId()));

        SendMessage sendMessage = new SendMessage();

        if (update.getMessage().getText().equals("/start")) {

            //создание юзера в базе
            User newUser = new User(String.valueOf(update.getMessage().getChatId()));
            newUser.initInDB();

            sendMessage.setText("Привет!");
            sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));

            try {
                execute(sendMessage);
                execute(sendReplyKeyBoardMessage(update.getMessage().getChatId(), true, false, false, false));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        else if (update.getMessage().getText().equals("Поиск")) {
            try {
                execute(sendReplyKeyBoardMessage(update.getMessage().getChatId(), false, true, false, false));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        else if (update.getMessage().getText().equals("Фильм")) {
            fndMovie = true;

            sendMessage.setText("Какой фильм хотите найти? Введите название:");
            sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        else if (update.getMessage().getText().equals("Актер")) {
            fndActor = true;

            sendMessage.setText("Какого актера или какую актрису хотите найти? Введите имя:");
            sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        else if (update.getMessage().getText().equals("Жанр")) {
            fndGenre = true;

            sendMessage.setText("Какой жанр хотите найти? Введите название:");
            sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        else if (update.getMessage().getText().equals("Режиссер")) {
            fndDirector = true;

            sendMessage.setText("Какого режиссера хотите найти? Введите имя:");
            sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        else if (update.getMessage().getText().equals("Нравится")) {
            if (fndMovie) { user.likes(movie, true); fndMovie = false; }
            else if (fndActor) { user.likes(actor, true); fndActor = false;}
            else if (fndGenre) { user.likes(genre, true); fndGenre = false;}
            else if (fndDirector) { user.likes(director, true); fndDirector = false;}

            try {
                sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
                sendMessage.setText("Изменения сохранены.");
                execute(sendMessage);
                execute(sendReplyKeyBoardMessage(update.getMessage().getChatId(), true, false, false, false));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        else if (update.getMessage().getText().equals("Не нравится")) {
            if (fndMovie) { user.likes(movie, false); fndMovie = false;}
            else if (fndActor) { user.likes(actor, false); fndActor = false;}
            else if (fndGenre) { user.likes(genre, false); fndGenre = false;}
            else if (fndDirector) { user.likes(director, false); fndDirector = false;}

            try {
                sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
                sendMessage.setText("Изменения сохранены.");
                execute(sendMessage);
                execute(sendReplyKeyBoardMessage(update.getMessage().getChatId(), true, false, false, false));

            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        else if (update.getMessage().getText().equals("Гл.меню")) {
            try {
                execute(sendReplyKeyBoardMessage(update.getMessage().getChatId(), true, false, false, false));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        else if (update.getMessage().getText().equals("Рейтинг")) {
            try {
                execute(sendReplyKeyBoardMessage(update.getMessage().getChatId(), false, false, true, false));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        else if (update.getMessage().getText().equals("Фильмы")) {
            Database db = new Database();
            String msg = db.getTop("movies");
            sendMessage.setText("Топ-10 фильмов:\n" + msg);
            sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        else if (update.getMessage().getText().equals("Актеры")) {
            Database db = new Database();
            String msg = db.getTop("actors");
            sendMessage.setText("Топ-10 актеров:\n" + msg);
            sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        else if (update.getMessage().getText().equals("Режиссеры")) {
            Database db = new Database();
            String msg = db.getTop("directors");
            sendMessage.setText("Топ-10 режиссеров:\n" + msg);
            sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        else if (update.getMessage().getText().equals("Жанры")) {
            Database db = new Database();
            String msg = db.getTop("genres");
            sendMessage.setText("Топ-10 жанров:\n" + msg);
            sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        else if (update.getMessage().getText().equals("Фильмы по жанрам")) {
            Database db = new Database();
            String msg = db.getMoviesByGenres();
            sendMessage.setText(msg);
            sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        else if (update.getMessage().getText().equals("Получить рекомендацию")) {
            String msg = "Список команд для рекомендации:\n"
                    + "/01 - 10 самых популярных фильмов\n"
                    + "/02 <название фильма> - 10 самых популярных фильмов, похожих на фильм\n"
                    + "/03 <жанр> - 10 популярных фильмов по жанру\n"
                    + "/04 <страна> - 10 популярных фильмов по стране\n"
                    + "/05 <год> - 10 популярных фильмов по году\n"
                    + "/06 <режиссер> - 10 популярных фильмов режиссера\n"
                    + "/07 <актер> - 10 популярных фильмов актера\n"
                    + "/08 <сценарист> - 10 популярных фильмов сценариста\n"
                    + "/09 <композитор> - 10 популярных фильмов композитора\n"
                    + "/10 <фильм> - все актеры данного фильма\n"
                    + "/11 <фильм> - все режиссеры данного фильма\n"
                    + "/12 <фильм> - все сценаристы данного фильма\n"
                    + "/13 <актер> - все фильмы данного актера\n"
                    + "/14 <режиссер> - все фильмы данного режиссера\n"
                    + "/15 <композитор> - все фильмы данного композитора\n"
                    + "/16 <сценарист> - все фильмы данного сценариста\n"
                    + "/17 <год> - 25 фильмов данного года\n"
                    + "/18 <страна> - 25 фильмов данной страны\n"
                    + "/19 <жанр> - 25 фильмов данного жанра\n"
                    + "/20 <год 4 знака>_<жанр> - 10 фильмов данного года и жанра\n"
                    + "/21 <режиссер>_<жанр> - 10 фильмов данного режиссера и жанра\n"
                    + "/22 <режиссер>_<страна> - 10 фильмов данного режиссера и страны\n"
                    + "/23 <актер>_<жанр> - 10 фильмов данного актера и жанра\n"
                    + "/24 <фильм> - 10 похожих фильмов того же года\n"
                    + "/25 <фильм> - 10 похожих фильмов того же жанра\n"
                    + "/26 <фильм> - 10 похожих фильмов той же страны\n";
            sendMessage.setText(msg);
            sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        else if (update.getMessage().getText().equals("/01")) {
            String msg = "10 самых популярных фильмов:\n";
            String rec = recom.popular10();
            sendMessage.setText(msg + rec);
            sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
            try {
                execute(sendMessage);
                execute(sendReplyKeyBoardMessage(update.getMessage().getChatId(), true, false, false, false));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        else if (update.getMessage().getText().contains("/02")) {
            String movie = update.getMessage().getText().substring(4);
            String msg = "10 популярных фильмов, похожих на: ";
            String rec = recom.popular10Cluster(movie);
            sendMessage.setText(msg + movie + "\n" + rec);
            sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
            try {
                execute(sendMessage);
                execute(sendReplyKeyBoardMessage(update.getMessage().getChatId(), true, false, false, false));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        else if (update.getMessage().getText().contains("/03")) {
            String genre = update.getMessage().getText().substring(4);
            String msg = "10 популярных фильмов в жанре: ";
            String rec = recom.popular10Genre(genre);
            sendMessage.setText(msg + genre + "\n" + rec);
            sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
            try {
                execute(sendMessage);
                execute(sendReplyKeyBoardMessage(update.getMessage().getChatId(), true, false, false, false));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        else if (update.getMessage().getText().contains("/04")) {
            String country = update.getMessage().getText().substring(4);
            String msg = "10 популярных фильмов по стране: ";
            String rec = recom.popular10Country(country);
            sendMessage.setText(msg + country + "\n" + rec);
            sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
            try {
                execute(sendMessage);
                execute(sendReplyKeyBoardMessage(update.getMessage().getChatId(), true, false, false, false));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        else if (update.getMessage().getText().contains("/05")) {
            String year = update.getMessage().getText().substring(4);
            String msg = "10 популярных фильмов по году: ";
            String rec = recom.popular10Year(year);
            sendMessage.setText(msg + year + "\n" + rec);
            sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
            try {
                execute(sendMessage);
                execute(sendReplyKeyBoardMessage(update.getMessage().getChatId(), true, false, false, false));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        else if (update.getMessage().getText().contains("/06")) {
            String str = update.getMessage().getText().substring(4);
            String msg = "10 популярных фильмов режиссера: ";
            String rec = recom.popular10Director(str);
            sendMessage.setText(msg + str + "\n" + rec);
            sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
            try {
                execute(sendMessage);
                execute(sendReplyKeyBoardMessage(update.getMessage().getChatId(), true, false, false, false));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        else if (update.getMessage().getText().contains("/07")) {
            String str = update.getMessage().getText().substring(4);
            String msg = "10 популярных фильмов актера: ";
            String rec = recom.popular10Actor(str);
            sendMessage.setText(msg + str + "\n" + rec);
            sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
            try {
                execute(sendMessage);
                execute(sendReplyKeyBoardMessage(update.getMessage().getChatId(), true, false, false, false));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        else if (update.getMessage().getText().contains("/08")) {
            String str = update.getMessage().getText().substring(4);
            String msg = "10 популярных фильмов сценариста: ";
            String rec = recom.popular10Writer(str);
            sendMessage.setText(msg + str + "\n" + rec);
            sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
            try {
                execute(sendMessage);
                execute(sendReplyKeyBoardMessage(update.getMessage().getChatId(), true, false, false, false));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        else if (update.getMessage().getText().contains("/09")) {
            String str = update.getMessage().getText().substring(4);
            String msg = "10 популярных фильмов композитора: ";
            String rec = recom.popular10Composer(str);
            sendMessage.setText(msg + str + "\n" + rec);
            sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
            try {
                execute(sendMessage);
                execute(sendReplyKeyBoardMessage(update.getMessage().getChatId(), true, false, false, false));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        else if (update.getMessage().getText().contains("/10")) {
            String str = update.getMessage().getText().substring(4);
            String msg = "Все актеры фильма: ";
            String rec = recom.actorsFromFilm(str);
            sendMessage.setText(msg + str + "\n" + rec);
            sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
            try {
                execute(sendMessage);
                execute(sendReplyKeyBoardMessage(update.getMessage().getChatId(), true, false, false, false));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        else if (update.getMessage().getText().contains("/11")) {
            String str = update.getMessage().getText().substring(4);
            String msg = "Все режиссеры фильма: ";
            String rec = recom.directorsFromFilm(str);
            sendMessage.setText(msg + str + "\n" + rec);
            sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
            try {
                execute(sendMessage);
                execute(sendReplyKeyBoardMessage(update.getMessage().getChatId(), true, false, false, false));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        else if (update.getMessage().getText().contains("/12")) {
            String str = update.getMessage().getText().substring(4);
            String msg = "Все сценаристы фильма: ";
            String rec = recom.writersFromFilm(str);
            sendMessage.setText(msg + str + "\n" + rec);
            sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
            try {
                execute(sendMessage);
                execute(sendReplyKeyBoardMessage(update.getMessage().getChatId(), true, false, false, false));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        else if (update.getMessage().getText().contains("/13")) {
            String str = update.getMessage().getText().substring(4);
            String msg = "Все фильмы данного актера: ";
            String rec = recom.filmsByActor(str);
            sendMessage.setText(msg + str + "\n" + rec);
            sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
            try {
                execute(sendMessage);
                execute(sendReplyKeyBoardMessage(update.getMessage().getChatId(), true, false, false, false));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        else if (update.getMessage().getText().contains("/14")) {
            String str = update.getMessage().getText().substring(4);
            String msg = "Все фильмы данного режиссера: ";
            String rec = recom.filmsByDirector(str);
            sendMessage.setText(msg + str + "\n" + rec);
            sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
            try {
                execute(sendMessage);
                execute(sendReplyKeyBoardMessage(update.getMessage().getChatId(), true, false, false, false));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        else if (update.getMessage().getText().contains("/15")) {
            String str = update.getMessage().getText().substring(4);
            String msg = "Все фильмы данного композитора: ";
            String rec = recom.filmsByComposer(str);
            sendMessage.setText(msg + str + "\n" + rec);
            sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
            try {
                execute(sendMessage);
                execute(sendReplyKeyBoardMessage(update.getMessage().getChatId(), true, false, false, false));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        else if (update.getMessage().getText().contains("/16")) {
            String str = update.getMessage().getText().substring(4);
            String msg = "Все фильмы данного сценариста: ";
            String rec = recom.filmsByWriter(str);
            sendMessage.setText(msg + str + "\n" + rec);
            sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
            try {
                execute(sendMessage);
                execute(sendReplyKeyBoardMessage(update.getMessage().getChatId(), true, false, false, false));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        else if (update.getMessage().getText().contains("/17")) {
            String str = update.getMessage().getText().substring(4);
            String msg = "Все фильмы данного года: ";
            String rec = recom.films25ByYear(str);
            sendMessage.setText(msg + str + "\n" + rec);
            sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
            try {
                execute(sendMessage);
                execute(sendReplyKeyBoardMessage(update.getMessage().getChatId(), true, false, false, false));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        else if (update.getMessage().getText().contains("/18")) {
            String str = update.getMessage().getText().substring(4);
            String msg = "Все фильмы данной страны: ";
            String rec = recom.films25ByCountry(str);
            sendMessage.setText(msg + str + "\n" + rec);
            sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
            try {
                execute(sendMessage);
                execute(sendReplyKeyBoardMessage(update.getMessage().getChatId(), true, false, false, false));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        else if (update.getMessage().getText().contains("/19")) {
            String str = update.getMessage().getText().substring(4);
            String msg = "Все фильмы данного жанра: ";
            String rec = recom.films25ByGenre(str);
            sendMessage.setText(msg + str + "\n" + rec);
            sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
            try {
                execute(sendMessage);
                execute(sendReplyKeyBoardMessage(update.getMessage().getChatId(), true, false, false, false));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        else if (update.getMessage().getText().contains("/20")) {
            String str = update.getMessage().getText().substring(4);
            int idx = str.indexOf('_');
            String substr1 = str.substring(0, idx);
            String substr2 = str.substring(idx+1);
            ////////////////////////////////////////
            String msg = "Все фильмы данного года и жанра: ";
            String rec = recom.films10ByYearGenre(substr1, substr2);
            sendMessage.setText(msg + str + "\n" + rec);
            sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
            try {
                execute(sendMessage);
                execute(sendReplyKeyBoardMessage(update.getMessage().getChatId(), true, false, false, false));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        else if (update.getMessage().getText().contains("/21")) {
            String str = update.getMessage().getText().substring(4);
            int idx = str.indexOf('_');
            String substr1 = str.substring(0, idx);
            String substr2 = str.substring(idx+1);
            ////////////////////////////////////////
            String msg = "Все фильмы данного режиссера и жанра: ";
            String rec = recom.films10ByDirectorGenre(substr1, substr2);
            sendMessage.setText(msg + str + "\n" + rec);
            sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
            try {
                execute(sendMessage);
                execute(sendReplyKeyBoardMessage(update.getMessage().getChatId(), true, false, false, false));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        else if (update.getMessage().getText().contains("/22")) {
            String str = update.getMessage().getText().substring(4);
            int idx = str.indexOf('_');
            String substr1 = str.substring(0, idx);
            String substr2 = str.substring(idx+1);
            ////////////////////////////////////////
            String msg = "Все фильмы данного режиссера, снятые в стране: ";
            String rec = recom.films10ByDirectorCountry(substr1, substr2);
            sendMessage.setText(msg + str + "\n" + rec);
            sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
            try {
                execute(sendMessage);
                execute(sendReplyKeyBoardMessage(update.getMessage().getChatId(), true, false, false, false));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        else if (update.getMessage().getText().contains("/23")) {
            String str = update.getMessage().getText().substring(4);
            int idx = str.indexOf('_');
            String substr1 = str.substring(0, idx);
            String substr2 = str.substring(idx+1);
            ////////////////////////////////////////
            String msg = "Все фильмы, в которых снимался актер, такого жанра: ";
            String rec = recom.films10ByActorGenre(substr1, substr2);
            sendMessage.setText(msg + str + "\n" + rec);
            sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
            try {
                execute(sendMessage);
                execute(sendReplyKeyBoardMessage(update.getMessage().getChatId(), true, false, false, false));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        else if (update.getMessage().getText().contains("/24")) {
            String str = update.getMessage().getText().substring(4);
            String msg = "10 похожих фильмов того же года: ";
            String rec = recom.films10ByFilmYear(str);
            sendMessage.setText(msg + str + "\n" + rec);
            sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
            try {
                execute(sendMessage);
                execute(sendReplyKeyBoardMessage(update.getMessage().getChatId(), true, false, false, false));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        else if (update.getMessage().getText().contains("/25")) {
            String str = update.getMessage().getText().substring(4);
            String msg = "10 похожих фильмов того же жанра: ";
            String rec = recom.films10ByFilmGenre(str);
            sendMessage.setText(msg + str + "\n" + rec);
            sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
            try {
                execute(sendMessage);
                execute(sendReplyKeyBoardMessage(update.getMessage().getChatId(), true, false, false, false));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        else if (update.getMessage().getText().contains("/26")) {
            String str = update.getMessage().getText().substring(4);
            String msg = "10 похожих фильмов той же страны: ";
            String rec = recom.films10ByFilmCountry(str);
            sendMessage.setText(msg + str + "\n" + rec);
            sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
            try {
                execute(sendMessage);
                execute(sendReplyKeyBoardMessage(update.getMessage().getChatId(), true, false, false, false));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
//        //TODO: write a function to recommend a random film
//        else if (update.getMessage().getText().equals("Случ.фильм")) {
//            sendMessage.setText("ТУТ ДОЛЖНА БЫТЬ РЕКОМЕНДАЦИЯ СЛУЧАЙНОГО ФИЛЬМА");
//            sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
//
//            try {
//                execute(sendMessage);
//            } catch (TelegramApiException e) {
//                e.printStackTrace();
//            }
//        }
//        //TODO: write a function to recommend a personalized film
//        else if (update.getMessage().getText().equals("Перс.фильм")) {
//            sendMessage.setText("ТУТ ДОЛЖНА БЫТЬ РЕКОМЕНДАЦИЯ ПЕРСОНАЛИЗИРОВАННОГО ФИЛЬМА");
//            sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
//
//            try {
//                execute(sendMessage);
//            } catch (TelegramApiException e) {
//                e.printStackTrace();
//            }
//        }
        else {
            //TODO: сделать обработку не найденных узлов
            //TODO: info - rate, genre, released year
            if (fndMovie) {
                movie = new Movie(update.getMessage().getText());
                sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
                String mname = "Ваш выбор: фильм\n" + movie.getName();
                String mrate = " \nРейтинг: " + movie.getRate();

                String mdir = "\nРежиссер: " + movie.getDirector();
                String mwrit = "\nСценарист: " + movie.getWriter();
//                String mcomp = "\nКомпозитор: " + movie.getComposer();
                String mcont = "\nСтрана: " + movie.getCountry();
                String myear = "\nГод выхода в прокат: " + movie.getYear();
                //актеры
                sendMessage.setText(mname + mrate + mwrit + mdir /* + mcomp */ + mcont + myear);

                try {
                    execute(sendMessage);
                    execute(sendReplyKeyBoardMessage(update.getMessage().getChatId(), false, false, false, false));
                    //fndMovie = false;
                    return;
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                fndMovie = false;
            }
            //TODO: info - rate, born year (maybe county & city)
            else if (fndActor) {actor = new Actor(update.getMessage().getText());
                sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
                sendMessage.setText("Ваш выбор: актер\n" + actor.getName() + " \nРейтинг: " + actor.getRate());
                try {
                    execute(sendMessage);
                    execute(sendReplyKeyBoardMessage(update.getMessage().getChatId(), false, false, false, false));
                    //fndActor = false;
                    return;
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }

                fndActor = false;
            }
            else if (fndGenre) {genre = new Genre(update.getMessage().getText());
                sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
                sendMessage.setText("Ваш выбор: жанр\n" + genre.getName() + " \nРейтинг: " + genre.getRate());
                try {
                    execute(sendMessage);
                    execute(sendReplyKeyBoardMessage(update.getMessage().getChatId(), false, false, false, false));
                    //fndGenre = false;
                    return;
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }

                fndGenre = false;
            }
            //TODO: info - rate, born year (maybe county & city)
            else if (fndDirector) {director = new Director(update.getMessage().getText());
                sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
                sendMessage.setText("Ваш выбор: режиссер\n" + director.getName() + " \nРейтинг: " + director.getRate());
                try {
                    execute(sendMessage);
                    execute(sendReplyKeyBoardMessage(update.getMessage().getChatId(), false, false, false, false));
                    //fndDirector = false;
                    return;
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }

                fndDirector = false;
            }
            else {
                sendMessage.setText("ничего не понимаю:(");
                sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
                try {
                    execute(sendMessage);
                    return;
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }

            try {
                execute(sendReplyKeyBoardMessage(update.getMessage().getChatId(), false, false, false, false));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public String getBotUsername() {
        return "@moviecsBot";
    }

    @Override
    public String getBotToken() {
        return "1683477750:AAERfI0w4BQHjyuZRTCP882-OeSqbUw2e_0";
    }

    public static SendMessage sendReplyKeyBoardMessage(long chatId,
                                                       boolean main, boolean search,
                                                       boolean rate, boolean recommend) {
        SendMessage sm = new SendMessage();

        ArrayList<KeyboardRow> keyboard = new ArrayList<>();
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

        KeyboardRow line1 = new KeyboardRow();
        KeyboardRow line2 = new KeyboardRow();

        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        keyboard.clear();
        line1.clear();
        line2.clear();

        //TODO: добавить кнопку ПРОФИЛЬ с доп [Показать, Редактировать, Гл.меню]
        if (main) {
            line1.add("Поиск");
            line1.add("Рейтинг");
            line2.add("Получить рекомендацию");
            sm.setText("Какое действие совершить?");
        }
        else if (search) {
            line1.add("Фильм");
            line1.add("Жанр");
            line2.add("Актер");
            line2.add("Режиссер");
            line2.add("Гл.меню");
            sm.setText("В каком направлении осуществить поиск?");
        }
        else if(rate) {
            line1.add("Фильмы");
            line1.add("Жанры");
            line2.add("Актеры");
            line2.add("Режиссеры");
            line2.add("Гл.меню");
            sm.setText("По какому направлению вы хотите узнать рейтинг?");
        }
        else if(recommend) {
            line1.add("Случ.фильм");
            line1.add("Перс.фильм");
            line2.add("Гл.меню");
            sm.setText("Хотите получить название случайного фильма или персонализированную рекомендацию?");
        }
        else {
            line1.add("Нравится");
            line1.add("Не нравится");
            line2.add("Гл.меню");
            sm.setText("Нравится ли вам это?");
        }

        keyboard.add(line1);
        keyboard.add(line2);
        replyKeyboardMarkup.setKeyboard(keyboard);

        sm.setChatId(String.valueOf(chatId));
        sm.setReplyMarkup(replyKeyboardMarkup);
        return sm;
    }
}