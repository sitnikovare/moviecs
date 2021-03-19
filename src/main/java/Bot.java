import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;


public class Bot extends TelegramLongPollingBot {

    User Us;
    Actor Act;
    Director Dir;
    Movie Mov;
    Genre Gen;
    Date Dat;
    //тут еще chatId

    @Override
    public void onUpdateReceived(Update update) {
        update.getUpdateId();

        SendMessage sendMessage = new SendMessage();

        if (update.getMessage().getText().equals("/start")) {

            //создание юзера в базе
            User newUser = new User(String.valueOf(update.getMessage().getChatId()));
            newUser.initInDB();

            sendMessage.setText("Привет!");
            sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));

            try {
                execute(sendMessage);
                execute(sendInlineKeyBoardMessage(update.getMessage().getChatId(), true));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        else if (update.getMessage().getText().equals("testM")) {

            try {
                execute(sendInlineKeyBoardMessage(update.getMessage().getChatId(), true));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        else if (update.getMessage().getText().equals("testL")) {

            try {
                execute(sendInlineKeyBoardMessage(update.getMessage().getChatId(), false));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        else if (update.hasCallbackQuery()) {
            //тут обработать различные запросы
            //сохранять в поля бота, потом удалять через проверку пустоты name узла

            //после этого отправлять клавиатуру нравится-не нравится

            String action = update.getCallbackQuery().getData();
            SendMessage sm = new SendMessage();
            sm.setChatId(String.valueOf(update.getCallbackQuery().getMessage().getChatId()));

            //поиск узлов и лайки
            if (action.equals("movie")) {}
            if (action.equals("actor")) {}
            if (action.equals("director")) {}
            if (action.equals("genre")) {}
            if (action.equals("like")) {}
            if (action.equals("unlike")) {}
            else {sm.setText("ничего не понимаю:(");}


            try {
                execute(sm);
                execute(sendInlineKeyBoardMessage(update.getCallbackQuery().getMessage().getChatId(), true));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        else {
            sendMessage.setText("ничего не понимаю:(");
            sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

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
        }
        else {
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


    @Override
    public String getBotUsername() {
        return "@moviecsBot";
    }

    @Override
    public String getBotToken() {
        return "1683477750:AAERfI0w4BQHjyuZRTCP882-OeSqbUw2e_0";
    }
}
