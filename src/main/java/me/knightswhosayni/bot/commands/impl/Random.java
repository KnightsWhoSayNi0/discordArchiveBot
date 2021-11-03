package me.knightswhosayni.bot.commands.impl;

import me.knightswhosayni.archive.Librarian;
import me.knightswhosayni.archive.Message;
import me.knightswhosayni.bot.Bot;
import me.knightswhosayni.bot.commands.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

public class Random extends Command {

    public Random() {
        super("random", "Displays a random quote. Optionally, provide how many sequential messages you would like to see.", "random | "+Bot.prefix+"random <# of messages>", new String[]{"r"});
    }

    @Override
    public void onCommand(String[] args, MessageReceivedEvent e) {
        if (args.length == 1) {
            Message rand = Librarian.getRandomMessage();

            EmbedBuilder embed = new EmbedBuilder()
                    .setDescription(rand.getContent())
                    .setAuthor(rand.getAuthor())
                    .setTimestamp(rand.getDate());

            e.getChannel().sendMessage(embed.build()).queue();

            if (!rand.getAttachments().isEmpty()) {
                e.getChannel().sendMessage(rand.getAttachments()).queue();
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
