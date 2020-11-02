package com.movement_trail;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.events.GameStateChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

@Slf4j
@PluginDescriptor(
	name = "Movement trail"
)
public class ExamplePlugin extends Plugin
{
	Player Localplayer;
	@Inject
	private Client client;

	@Inject
	private ExampleConfig config;

	@Override
	protected void startUp() throws Exception
	{
		log.info("MovementTrail started");

	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info("MovementTrail stopped");
	}
	@Override
	protected void mouseClicked(MouseEvent mouse){
		this.Localplayer.getLocalLocation();
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{
		if (gameStateChanged.getGameState() == GameState.LOGGED_IN)
		{
			this.Localplayer = getLocalPlayer();
		}
	}

	@Provides
	ExampleConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(Config.class);
	}
}
