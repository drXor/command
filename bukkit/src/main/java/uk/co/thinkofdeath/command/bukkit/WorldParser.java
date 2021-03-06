/*
 * Copyright 2014 Matthew Collins
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.co.thinkofdeath.command.bukkit;

import org.bukkit.World;
import org.bukkit.plugin.Plugin;
import uk.co.thinkofdeath.command.CommandError;
import uk.co.thinkofdeath.command.parsers.ArgumentParser;
import uk.co.thinkofdeath.command.parsers.ParserException;

import java.util.HashSet;
import java.util.Set;

/**
 * Parses world name's into World objects
 */
public class WorldParser implements ArgumentParser<World> {
    private final Plugin plugin;

    public WorldParser(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public World parse(String argument) throws ParserException {
        World world = plugin.getServer().getWorld(argument);
        if (world == null) {
            throw new ParserException(new CommandError(2, "bukkit.no-world", argument));
        }
        return world;
    }

    @Override
    public Set<String> complete(String argument) {
        HashSet<String> completions = new HashSet<>();
        argument = argument.toLowerCase();
        for (World world : plugin.getServer().getWorlds()) {
            if (world.getName().toLowerCase().startsWith(argument)) {
                completions.add(world.getName());
            }
        }
        return completions;
    }
}
