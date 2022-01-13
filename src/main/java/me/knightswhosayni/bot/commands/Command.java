package me.knightswhosayni.bot.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public abstract class Command {

    public String name;
    public String description;
    public String syntax;
    public String[] aliases;

    public Command(String name, String description, String syntax, String[] aliases) {
        this.name = name;
        this.description = description;
        this.syntax = syntax;
        this.aliases = aliases;
    }

    public abstract void onCommand(String[] args, MessageReceivedEvent event);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSyntax() {
        return syntax;
    }

    public void setSyntax(String syntax) {
        this.syntax = syntax;
    }

    public String[] getAliases() {
        return aliases;
    }

    public void setAliases(String[] aliases) {
        this.aliases = aliases;
    }
}
