package me.knightswhosayni.bot.commands;

import me.knightswhosayni.archive.Librarian;
import me.knightswhosayni.archive.Message;
import me.knightswhosayni.bot.Bot;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.time.LocalDate;

public class Random {

    public static void execute(MessageReceivedEvent e) {
        Message rand = Librarian.getRandomMessage();

        EmbedBuilder embed = new EmbedBuilder()
                .setDescription(rand.getContent())
                .setAuthor(rand.getAuthor())
                .setTimestamp(rand.getDate());

        e.getChannel().sendMessage(embed.build()).queue();

        if (!rand.getAttachments().isEmpty()) {
            e.getChannel().sendMessage(rand.getAttachments()).queue();
        }
    }

}
