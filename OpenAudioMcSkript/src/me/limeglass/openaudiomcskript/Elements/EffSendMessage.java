package me.limeglass.openaudiomcskript.Elements;

import javax.annotation.Nullable;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import net.openaudiomc.actions.Command;

public class EffSendMessage extends Effect {
	
	static {
		Skript.registerEffect(EffSendMessage.class, "send (message|msg) %string% (for|to) [player[s]] %players%");
	}
	
	private Expression<Player> players;
	private Expression<String> MESSAGE;
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, ParseResult parser) {
		players = (Expression<Player>) e[1];
		MESSAGE = (Expression<String>) e[0];
		return true;
	}
	@Override
	public String toString(@Nullable Event paramEvent, boolean paramBoolean) {
		return "send (message|msg) %string% (for|to) [player[s]] %players%";
	}
	@Override
	protected void execute(Event e) {
		if (players != null && MESSAGE != null) {
			for (Player player : players.getAll(e)) {
				Command.sendMessage(player.getName(), MESSAGE.getSingle(e));
			}
		}
	}
}