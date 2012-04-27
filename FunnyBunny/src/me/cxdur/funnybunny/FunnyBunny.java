package me.cxdur.funnybunny;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class FunnyBunny extends JavaPlugin {

	FunnyListener listener = new FunnyListener(this);

	HashMap<Player, String> noDamage = new HashMap<Player, String>();

	public void onEnable() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(listener, this);
		print("Enabled FunnyBunny by CXdur!");
	}

	public String hasAuth(Player p) {
		if (p.isOp() || (p.hasPermission("funnybunny.*"))) {
			return hasAuth(p);
		}
		return hasAuth(p);
	}

	public void onDisable() {
		print("Disabling FunnyBunny plugin.");
	}

	public void print(String message) {
		System.out.println("[FunnyBunny] " + message);
	}

	public void AddItem(String playerName, ItemStack itemstack)  {
		Bukkit.getPlayer(playerName).getInventory().addItem(itemstack);
	}
	
	public boolean onCommand(CommandSender sender, Command cmd,
			String label, String[] args) {
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("funnybunny") && (p.hasPermission("funnybunny.commands") || (p.isOp()))) {
			if (args.length == 0) {
				p.sendMessage(ChatColor.GOLD + "/funnybunny items [Gives you the items]");
				p.sendMessage(ChatColor.GOLD + "/funnybunny god [GodMode]");
				return true;
			} else if (args.length == 1) {
				if (args[0].equalsIgnoreCase("items")) {
					ItemStack boot = new ItemStack(317, 1);
					ItemStack boot1 = new ItemStack(301, 1);
					ItemStack boot2 = new ItemStack(305, 1);
					ItemStack boot3 = new ItemStack(313, 1);
					ItemStack fishingrod = new ItemStack(346, 1);
					ItemStack saddle = new ItemStack(329, 1);
					p.sendMessage(ChatColor.GOLD + "Here is your items.");
					AddItem(p.getName(), boot1);
					AddItem(p.getName(), boot);
					AddItem(p.getName(), boot2);
					AddItem(p.getName(), boot3);
					AddItem(p.getName(), fishingrod);
					AddItem(p.getName(), saddle);
					return true;
				} else if (args[0].equalsIgnoreCase("god")) {
					if (!noDamage.containsKey(p)) {
						noDamage.put(p, p.getName());
						p.sendMessage(ChatColor.RED + "Damage deactivated, to reactivate type /fb god.");
						p.setHealth(20);
						return true;
					} else {
						noDamage.remove(p);
						p.sendMessage(ChatColor.RED + "Damage activated, to deactivate type /fb god.");
						p.setHealth(p.getHealth());
						return true;
					}
				}
			} 
			return false;
		}
		return false;
	}	}
