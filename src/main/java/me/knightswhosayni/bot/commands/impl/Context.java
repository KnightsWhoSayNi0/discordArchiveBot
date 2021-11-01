package me.knightswhosayni.bot.commands.impl;

import me.knightswhosayni.archive.Librarian;
import me.knightswhosayni.archive.Message;
import me.knightswhosayni.bot.commands.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Context extends Command {

    public Context() {
        super("context", "Displays the next quote in sequence, after the last random.", "context", new String[]{"c"});
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
        } else {
          e.getChannel().sendMessage(new EmbedBuilder()
                  .setTitle("Incorrect syntax.")
                  .setDescription("Syntax is: " + this.getSyntax())
                  .setColor(0xff22ff77)
                  .build()).queue();
        }
    }
}
