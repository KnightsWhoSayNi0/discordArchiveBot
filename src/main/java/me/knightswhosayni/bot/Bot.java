package me.knightswhosayni.bot;

import me.knightswhosayni.bot.commands.Help;
import me.knightswhosayni.archive.Librarian;
import me.knightswhosayni.bot.commands.Random;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;

public class Bot extends ListenerAdapter {

    public static JDA jda;
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

    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String msg = event.getMessage().getContentRaw();
        if (msg.equals(".help")) {
            Help.execute(event);
        }
        if (msg.equals(".random") || msg.equals(".r")) {
            Random.execute(event);
        }
    }

}
