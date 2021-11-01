package me.knightswhosayni.bot.commands;

import me.knightswhosayni.bot.Bot;
import me.knightswhosayni.bot.commands.impl.*;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.ArrayList;
import java.util.List;

public class CommandManager {

    public List<Command> commands;

    public CommandManager() {
        commands = new ArrayList<Command>();
        setup();
    }

    public void setup() { // add commands
        commands.add(new Help());
        commands.add(new Random());
        commands.add(new Context());
    }

    public void handleCommands(String[] args, MessageReceivedEvent event) {
        boolean foundCommand = false;
        for (Command c : commands) {
            if (args[0].equalsIgnoreCase(Bot.prefix + c.getName())) {
                c.onCommand(args, event);
                foundCommand = true;
            } else if (c.getAliases().length != 0) {
                for (String alias : c.getAliases()) {
                    if (args[0].equalsIgnoreCase(Bot.prefix + alias)) {
                        c.onCommand(args, event);
                        foundCommand = true;
                    }
                }
            }
        }

        if (!foundCommand) {
            EmbedBuilder embed = new EmbedBuilder();
            embed.setTitle("Command Not Found");
            embed.setColor(0xff22ff77);

            MessageReceivedEvent e = (MessageReceivedEvent) event;
            e.getChannel().sendMessage(embed.build()).queue();
        }
    }

}
