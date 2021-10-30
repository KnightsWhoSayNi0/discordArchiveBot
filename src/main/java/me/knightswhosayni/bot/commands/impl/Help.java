package me.knightswhosayni.bot.commands.impl;

import me.knightswhosayni.bot.Bot;
import me.knightswhosayni.bot.commands.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Help extends Command {

    public Help() {
        super("help", "Displays the help screen.", "help", new String[0]);
    }

    @Override
    public void onCommand(String[] args, MessageReceivedEvent event) {
        EmbedBuilder embed = new EmbedBuilder()
                .setTitle("Zoom Sucks Bot - Help")
                .setDescription("Zoom Sucks Bot is a discord bot designed to quote the Zoom Sucks group DM. However this bot can easily be adapted to any discord log.")
                .setAuthor("KnightsWhoSayNi#0001")
                .setFooter("https://github.com/KnightsWhoSayNi0/discordArchiveBot")
                .setThumbnail(Bot.jda.getSelfUser().getAvatarUrl())
                .addField("Random", "Type .random or .r to view a random quote.", true)
                .setColor(0xff22ff77);

        for (Command c : Bot.commandManager.commands) {
            embed.addField(c.getName(), "Description: " + c.getDescription() + "\n Syntax: " + c.getSyntax(), true);
        }

        event.getChannel().sendMessage(embed.build()).queue();
    }

}
