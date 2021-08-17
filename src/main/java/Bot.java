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

    @Override
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
        //TODO: write a function to make top on genres
        else if (update.getMessage().getText().equals("Фильмы по жанрам")) {
            sendMessage.setText("ТУТ ДОЛЖНЫ БЫТЬ ТОПЫ ПО ЖАНРАМ");
            sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        else if (update.getMessage().getText().equals("Получить рекомендацию")) {
            try {
                execute(sendReplyKeyBoardMessage(update.getMessage().getChatId(), false, false, false, true));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        //TODO: write a function to recommend a random film
        else if (update.getMessage().getText().equals("Случ.фильм")) {
            sendMessage.setText("ТУТ ДОЛЖНА БЫТЬ РЕКОМЕНДАЦИЯ СЛУЧАЙНОГО ФИЛЬМА");
            sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        //TODO: write a function to recommend a personalized film
        else if (update.getMessage().getText().equals("Перс.фильм")) {
            sendMessage.setText("ТУТ ДОЛЖНА БЫТЬ РЕКОМЕНДАЦИЯ ПЕРСОНАЛИЗИРОВАННОГО ФИЛЬМА");
            sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        else {
            if (fndMovie) {
                movie = new Movie(update.getMessage().getText());
                sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
                sendMessage.setText("Ваш выброр: фильм\n" + movie.getName() + " \nРейтинг: " + movie.getRate());
                try {
                    execute(sendMessage);
                    execute(sendReplyKeyBoardMessage(update.getMessage().getChatId(), false, false, false, false));
                    //fndMovie = false;
                    return;
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            else if (fndActor) {actor = new Actor(update.getMessage().getText());
                sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
                sendMessage.setText("Ваш выброр: актер\n" + actor.getName() + " \nРейтинг: " + actor.getRate());
                try {
                    execute(sendMessage);
                    execute(sendReplyKeyBoardMessage(update.getMessage().getChatId(), false, false, false, false));
                    //fndActor = false;
                    return;
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            else if (fndGenre) {genre = new Genre(update.getMessage().getText());
                sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
                sendMessage.setText("Ваш выброр: жанр\n" + genre.getName() + " \nРейтинг: " + genre.getRate());
                try {
                    execute(sendMessage);
                    execute(sendReplyKeyBoardMessage(update.getMessage().getChatId(), false, false, false, false));
                    //fndGenre = false;
                    return;
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            else if (fndDirector) {director = new Director(update.getMessage().getText());
                sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
                sendMessage.setText("Ваш выброр: режиссер\n" + director.getName() + " \nРейтинг: " + director.getRate());
                try {
                    execute(sendMessage);
                    execute(sendReplyKeyBoardMessage(update.getMessage().getChatId(), false, false, false, false));
                    //fndDirector = false;
                    return;
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
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