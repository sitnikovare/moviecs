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
                execute(sendReplyKeyBoardMessage(update.getMessage().getChatId(), true));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        } else if (update.getMessage().getText().equals("testR")) {

            try {
                execute(sendReplyKeyBoardMessage(update.getMessage().getChatId(), true));
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
            if (fndMovie) { user.likes(movie, true); fndMovie = false;}
            else if (fndActor) { user.likes(actor, true); fndActor = false;}
            else if (fndGenre) { user.likes(genre, true); fndGenre = false;}
            else if (fndDirector) { user.likes(director, true); fndDirector = false;}

            try {
                sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
                sendMessage.setText("Изменения сохранены.");
                execute(sendMessage);
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
                execute(sendReplyKeyBoardMessage(update.getMessage().getChatId(), true));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        else if (update.getMessage().getText().equals("Искать дальше")) {
            try {
                execute(sendReplyKeyBoardMessage(update.getMessage().getChatId(), true));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        else {
            if (fndMovie) {
                movie = new Movie(update.getMessage().getText());
                //тут по идее поиск по базе, чтобы связь поставить
            }
            else if (fndActor) {actor = new Actor(update.getMessage().getText());}
            else if (fndGenre) {genre = new Genre(update.getMessage().getText());}
            else if (fndDirector) {director = new Director(update.getMessage().getText());}
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
                execute(sendReplyKeyBoardMessage(update.getMessage().getChatId(), false));
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

    public static SendMessage sendReplyKeyBoardMessage(long chatId, boolean fnd) {
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

        if (fnd) {
            line1.add("Фильм");
            line1.add("Жанр");
            line2.add("Актер");
            line2.add("Режиссер");
            sm.setText("Что хотите найти?");
        } else {
            line1.add("Нравится");
            line1.add("Не нравится");
            line2.add("Искать дальше");
            sm.setText("Нравится ли вам это?");
        }

        keyboard.add(line1);
        keyboard.add(line2);
        replyKeyboardMarkup.setKeyboard(keyboard);

        sm.setChatId(String.valueOf(chatId));
        sm.setReplyMarkup(replyKeyboardMarkup);
        return sm;
    }

    public static SendMessage sendInlineKeyBoardMessage(long chatId, boolean fnd) {
        SendMessage sm = new SendMessage();

        if (fnd) {
            InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

            InlineKeyboardButton ikbMovie = new InlineKeyboardButton();
            InlineKeyboardButton ikbActor = new InlineKeyboardButton();
            InlineKeyboardButton ikbDirector = new InlineKeyboardButton();
            InlineKeyboardButton ikbGenre = new InlineKeyboardButton();

            ikbMovie.setText("Фильм");
            ikbMovie.setCallbackData("movie");
            ikbActor.setText("Актер");
            ikbActor.setCallbackData("actor");
            ikbDirector.setText("Режиссер");
            ikbDirector.setCallbackData("director");
            ikbGenre.setText("Жанр");
            ikbGenre.setCallbackData("genre");


            //inlineKeyboardButton1.setText("Тык");
            //inlineKeyboardButton1.setCallbackData("Button \"Тык\" has been pressed");

            List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
            List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
            List<InlineKeyboardButton> keyboardButtonsRow3 = new ArrayList<>();
            List<InlineKeyboardButton> keyboardButtonsRow4 = new ArrayList<>();

            keyboardButtonsRow1.add(ikbMovie);
            keyboardButtonsRow2.add(ikbActor);
            keyboardButtonsRow3.add(ikbDirector);
            keyboardButtonsRow4.add(ikbGenre);

            List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
            rowList.add(keyboardButtonsRow1);
            rowList.add(keyboardButtonsRow2);
            rowList.add(keyboardButtonsRow3);
            rowList.add(keyboardButtonsRow4);
            inlineKeyboardMarkup.setKeyboard(rowList);

            sm.setChatId(String.valueOf(chatId));
            sm.setText("Что хотите найти?");
            sm.setReplyMarkup(inlineKeyboardMarkup);
        } else {
            InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

            InlineKeyboardButton ikbLike = new InlineKeyboardButton();
            InlineKeyboardButton ikbUnlike = new InlineKeyboardButton();

            ikbLike.setText("Нравится");
            ikbLike.setCallbackData("like");
            ikbUnlike.setText("Не нравится");
            ikbUnlike.setCallbackData("unlike");

            List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
            List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();

            keyboardButtonsRow1.add(ikbLike);
            keyboardButtonsRow2.add(ikbUnlike);

            List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
            rowList.add(keyboardButtonsRow1);
            rowList.add(keyboardButtonsRow2);
            inlineKeyboardMarkup.setKeyboard(rowList);

            sm.setChatId(String.valueOf(chatId));
            sm.setText("Нравится ли вам это?");
            sm.setReplyMarkup(inlineKeyboardMarkup);
        }

        return sm;
    }
}