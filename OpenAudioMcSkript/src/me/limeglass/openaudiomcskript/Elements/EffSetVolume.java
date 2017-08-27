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

public class EffSetVolume extends Effect {
	
	static {
		Skript.registerEffect(EffSetVolume.class, "set [audio] volume (to|for|of) [player[s]] %players% to %number%");
	}
	
	private Expression<Player> players;
	private Expression<Number> volume;
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, ParseResult parser) {
		players = (Expression<Player>) e[0];
		volume = (Expression<Number>) e[1];
		return true;
	}
	@Override
	public String toString(@Nullable Event paramEvent, boolean paramBoolean) {
		return "set [audio] volume (to|for|of) [player[s]] %players% to %number%";
	}
	@Override
	protected void execute(Event e) {
		if (players != null && volume != null) {
			for (Player player : players.getAll(e)) {
				Command.setVolume(player.getName(), volume.getSingle(e).toString());
			}
		}
	}
}