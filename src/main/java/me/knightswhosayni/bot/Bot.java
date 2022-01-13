package me.knightswhosayni.bot;

import me.knightswhosayni.bot.commands.CommandManager;
import me.knightswhosayni.archive.Librarian;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;

public class Bot extends ListenerAdapter {

    /*
    TODO
    - add search by date command
    - add author thumbnails
     */

    public static JDA jda;
    public static CommandManager commandManager;
    public static String prefix = ".";

    public static void main(String[] args) throws LoginException {
        if (args.length < 1) {
            System.out.println("You have to provide a token as first argument!");
            System.exit(1);
        }

        Librarian.init();

        jda = JDABuilder.createLight(args[0], GatewayIntent.GUILD_MESSAGES)
                .addEventListeners(new Bot())
                .setActivity(Activity.playing(prefix + "help"))
                .build();

        commandManager = new CommandManager();

    }

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        String msg = e.getMessage().getContentRaw();
        String[] args = msg.split(" ");

        if (e.getAuthor().isBot()) {
            return;
        }

        if (args[0].startsWith(Bot.prefix) && args[0].length > 1) {
            Bot.commandManager.handleCommands(args, e);
        }
    }

}
