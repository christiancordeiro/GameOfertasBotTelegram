package org.example.Telegram;

import io.github.cdimascio.dotenv.Dotenv;
import org.example.ApiOfertas.ApiDTO;
import org.example.ApiOfertas.ApiOfertas;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

public class Bot extends TelegramLongPollingBot {
    private static final Dotenv dotenv = Dotenv.load();

    @Override
    public String getBotUsername() {
        return dotenv.get("BOT_USERNAME");
    }

    @Override
    public String getBotToken() {
        return dotenv.get("API_TOKEN");
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        Long chatId = message.getChatId();
        String text = message.getText();

        ApiOfertas api = new ApiOfertas();
        List<ApiDTO> fetchApi = api.buscarOfertas();

        for (ApiDTO oferta : fetchApi) {
            SendMessage sendMessage = getSendMessage(oferta, chatId);
            String sendResposta = sendMessage.getText();

            try{
                execute(sendMessage);
                System.out.println("Ofertas enviadas: " + sendResposta);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    private static SendMessage getSendMessage(ApiDTO oferta, Long chatId) {
        String resposta = "*" + escapeMarkdown("🔥 Promoção Disponível! 🔥") + "*\n\n"
                + escapeMarkdown(oferta.getNome()) + "\n"
                + "*Preço em desconto*: " + escapeMarkdown(oferta.getPrecoDesconto()) + "\n"
                + "*Cupom*: " + escapeMarkdown(oferta.getPercentualDesconto()) + "%\n"
                + "*Link da loja*: " + escapeMarkdown(oferta.getLinkLoja()) + "\n";



        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId.toString());
        sendMessage.setText(resposta);
        sendMessage.setParseMode("MarkdownV2");
        return sendMessage;
    }

    public static String escapeMarkdown(String text) {
        if (text == null) return "";

        // Escapar todos os caracteres reservados do MarkdownV2, incluindo '-'
        return text.replaceAll("([_\\*\\[\\]\\(\\)~`>#+\\-=|{}.!])", "\\\\$1");
    }

}
