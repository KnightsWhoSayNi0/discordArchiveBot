package me.knightswhosayni.bot.commands.impl;

import me.knightswhosayni.archive.Librarian;
import me.knightswhosayni.archive.Message;
import me.knightswhosayni.bot.Bot;
import me.knightswhosayni.bot.commands.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

public class Context extends Command {

    public Context() {
        super("context",
                "Displays the next quote in sequence, after the last random. Optionally, provide how many sequential messages you would like to see.",
                "context | "+ Bot.prefix+"context <# of messages>",
                new String[]{"c"}
        );
    }

    @Override
    public void onCommand(String[] args, MessageReceivedEvent e) {
        if (args.length == 1) {
            Message m = Librarian.getLastMessage();

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
                    List<Message> messages = Librarian.getRandomMessages(count);

                    for (Message m : messages) {
                        EmbedBuilder embed = new EmbedBuilder()
                                .setDescription(m.getContent())
                                .setAuthor(m.getAuthor())
                                .setTimestamp(m.getDate());

                        e.getChannel().sendMessage(embed.build()).queue();

                        if (!m.getAttachments().isEmpty()) {
                            e.getChannel().sendMessage(m.getAttachments()).queue();
                        }
                    }
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
