package me.knightswhosayni.bot.commands.impl;

import me.knightswhosayni.archive.Librarian;
import me.knightswhosayni.archive.Message;
import me.knightswhosayni.bot.Bot;
import me.knightswhosayni.bot.commands.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

public class Next extends Command {

    public Next() {
        super("next",
                "Displays the next quote in sequence, after the last random. Optionally, provide how many sequential messages you would like to see.",
                "next | "+ Bot.prefix+"next <# of messages>",
                new String[]{"n"}
        );
    }

    @Override
    public void onCommand(String[] args, MessageReceivedEvent e) {
        if (args.length == 1) {
            Message m = Librarian.getNextMessage();

            EmbedBuilder embed = new EmbedBuilder()
                    .setDescription(m.getContent())
                    .setAuthor(m.getAuthor())
                    .setTimestamp(m.getDate());

            e.getChannel().sendMessage(embed.build()).queue();

            if (!m.getAttachments().isEmpty()) {
                e.getChannel().sendMessage(m.getAttachments()).queue();
            }
        } else if (args.length == 2) {
            try {
                int count = Integer.parseInt(args[1]);

                if (count > 0) {
                    List<Message> messages = Librarian.getNextMessages(count);

                    Random.sendEmbeds(e, messages);
                } else {
                    throw new Exception();
                }
            } catch (Exception ex) {
                e.getChannel().sendMessage(new EmbedBuilder()
                        .setTitle("Incorrect syntax.")
                        .setDescription("Syntax is: " + this.getSyntax())
                        .setColor(0xff22ff77)
                        .build()).queue();
                ex.printStackTrace();
            }
        }
    }
}
