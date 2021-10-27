package me.knightswhosayni.bot.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Help {

    public static void execute(MessageReceivedEvent e) {
        EmbedBuilder embed = new EmbedBuilder()
                .setTitle("Zoom Sucks Bot - Help")
                .setDescription("Zoom Sucks Bot is a discord bot designed to quote the Zoom Sucks group DM. However this bot can easily be adapted to any discord log.")
                .setAuthor("KnightsWhoSayNi#0001")
                .setFooter("https://github.com/KnightsWhoSayNi0/discordArchiveBot")
                .addField("Random", "Type .random or .r to view a random quote.", false)
                .setColor(0xff22ff77);

        e.getChannel().sendMessage(embed.build()).queue();
    }

}
