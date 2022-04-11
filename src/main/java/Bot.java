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
                    + "10popular - 10 самых популярных фильмов\n"
                    + "10popularCluster - 10 самых популярных фильмов, похожих на фильм\n"
                    + "10popularGenre - 10 популярных фильмов по жанру\n"
                    + "10popularCountry - 10 популярных фильмов по стране\n"
                    + "10popularYear - 10 популярных фильмов по году\n"
                    + "10popularDirector - 10 популярных фильмов режиссера\n"
                    + "10popularActor - 10 популярных фильмов актера\n"
                    + "10popularWriter - 10 популярных фильмов сценариста\n"
                    + "10popularComposer - 10 популярных фильмов композитора\n"
                    + "actorsFromFilm - все актеры данного фильма\n"
                    + "directorsFromFilm - все режиссеры данного фильма\n"
                    + "writersFromFilm - все сценаристы данного фильма\n"
                    + "filmsByActor - все фильмы данного актера\n"
                    + "filmsByDirector - все фильмы данного режиссера\n"
                    + "filmsByComposer - все фильмы данного композитора\n"
                    + "filmsByWriter - все фильмы данного сценариста\n"
                    + "25filmsByYear - 25 фильмов данного года\n"
                    + "25filmsByCountry - 25 фильмов данной страны\n"
                    + "25filmsByGenre - 25 фильмов данного жанра\n";
            sendMessage.setText(msg);
            sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        else if (update.getMessage().getText().equals("10popular")) {
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
        else if (update.getMessage().getText().contains("10popularCluster")) {
            String movie = update.getMessage().getText().substring(17);
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
        else if (update.getMessage().getText().contains("10popularGenre")) {
            String genre = update.getMessage().getText().substring(15);
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
        else if (update.getMessage().getText().contains("10popularCountry")) {
            String country = update.getMessage().getText().substring(17);
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
        else if (update.getMessage().getText().contains("10popularYear")) {
            String year = update.getMessage().getText().substring(14);
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
        else if (update.getMessage().getText().contains("10popularDirector")) {
            String str = update.getMessage().getText().substring(18);
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
        else if (update.getMessage().getText().contains("10popularActor")) {
            String str = update.getMessage().getText().substring(15);
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
        else if (update.getMessage().getText().contains("10popularComposer")) {
            String str = update.getMessage().getText().substring(18);
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
        else if (update.getMessage().getText().contains("10popularWriter")) {
            String str = update.getMessage().getText().substring(16);
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
        else if (update.getMessage().getText().contains("actorsFromFilm")) {
            String str = update.getMessage().getText().substring(15);
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
        else if (update.getMessage().getText().contains("directorsFromFilm")) {
            String str = update.getMessage().getText().substring(18);
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
        else if (update.getMessage().getText().contains("writersFromFilm")) {
            String str = update.getMessage().getText().substring(16);
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
        else if (update.getMessage().getText().contains("filmsByActor")) {
            String str = update.getMessage().getText().substring(13);
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
        else if (update.getMessage().getText().contains("filmsByDirector")) {
            String str = update.getMessage().getText().substring(16);
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
        else if (update.getMessage().getText().contains("filmsByComposer")) {
            String str = update.getMessage().getText().substring(16);
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
        else if (update.getMessage().getText().contains("filmsByWriter")) {
            String str = update.getMessage().getText().substring(14);
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
        else if (update.getMessage().getText().contains("25filmsByYear")) {
            String str = update.getMessage().getText().substring(14);
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
        else if (update.getMessage().getText().contains("25filmsByCountry")) {
            String str = update.getMessage().getText().substring(17);
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
        else if (update.getMessage().getText().contains("25filmsByGenre")) {
            String str = update.getMessage().getText().substring(15);
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
        //TODO: сделать обработку не найденных узлов
        else {
            //TODO: info - rate, genre, released year
            if (fndMovie) {
                movie = new Movie(update.getMessage().getText());
                sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
                sendMessage.setText("Ваш выбор: фильм\n" + movie.getName() + " \nРейтинг: " + movie.getRate());
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
            sm.setText("Что хотите найти?");
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
            line1.add("Фильмы по жанрам");
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