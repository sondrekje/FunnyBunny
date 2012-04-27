package me.cxdur.funnybunny;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

public class FunnyListener implements Listener {

	private FunnyBunny plugin;

	public FunnyListener(FunnyBunny instance) {
		plugin = instance;
	}

	//Mounting method
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerRapeEvent(final PlayerInteractEntityEvent e) {
		Entity raped = e.getRightClicked();
		Player p = e.getPlayer();
		if (p.getItemInHand().getType() == Material.SADDLE) {
			if (p.hasPermission("funnybunny.mount") || (p.isOp())) {
				raped.setPassenger(p);
				p.sendMessage(ChatColor.GOLD + "You just mounted that poor entity.");
			}
		}
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerChatEvent(final PlayerChatEvent e) {
		Player p = e.getPlayer();
		if (e.getMessage().contains("op me")) {
			if (p.hasPermission("funnybunny.opmessage")) {
				p.sendMessage(ChatColor.YELLOW + "You are now OP!");
			}
		}
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerGetRapedEvent(final PlayerInteractEntityEvent e) {
		Player p = e.getPlayer();
		Entity raper = e.getRightClicked();
		if (p.getItemInHand().getType() == Material.FISHING_ROD) {
			if (p.hasPermission("funnybunny.mounted") || (p.isOp())) {
				p.setPassenger(raper);
				p.sendMessage(ChatColor.GOLD + "You just got mounted by a poor entity.");
			}	
		}
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerBoostEvent(final PlayerMoveEvent e) {
		Player p = e.getPlayer();
		if (p.getInventory().getBoots() != null) {
			if (p.getInventory().getBoots().getType() == Material.GOLD_BOOTS && (p.isSneaking())) {
				if (p.hasPermission("funnybunny.superspeed") || (p.isOp())) {
					Vector vec = p.getLocation().getDirection();
					vec.multiply(5);
					p.setVelocity(vec);
				}
			}	}	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerSuperBoost(final PlayerMoveEvent e) {
		Player p = e.getPlayer();
		if(p.hasPermission("funnybunny.boost") || (p.isOp())) {
			if (p.getInventory().getBoots() != null) {
				if (p.getInventory().getBoots().getType() == Material.CHAINMAIL_BOOTS) {
					p.setFoodLevel(20);
					if (p.isSprinting()) {
						p.setVelocity(p.getLocation().getDirection().multiply(3));
						p.setFoodLevel(20);
						if (e.getTo().getY() > e.getFrom().getY() + 1) {
							e.setTo(e.getFrom());
						}
					}
				}
			}	}
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void onFoodLevelChange(final FoodLevelChangeEvent e) {
		Player p = (Player) e.getEntity();
		if (p.getInventory().getBoots() != null) {
			if (p.getInventory().getBoots().getType() == Material.CHAINMAIL_BOOTS) {
				if (p.hasPermission("funnybunny.boost") || (p.isOp())) {
					e.setCancelled(true);
				}
			}
		}
	}


	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerSuperJumpEvent(final PlayerMoveEvent e) { 
		Player p = e.getPlayer();
		if (p.getInventory().getBoots() != null) {
			if (p.getInventory().getBoots().getType() == Material.DIAMOND_BOOTS) {
				if (p.hasPermission("funnybunny.superjump") || (p.isOp())) {
					if (e.getTo().getBlockY() > e.getFrom().getBlockY()) {
						p.setVelocity( new Vector( 0, 10, 0) );			
					}
				}
			}	}
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerTakeDamageEvent(final EntityDamageEvent e) {
		if (!(e.getEntity() instanceof Player)) {
			return;
		}
		Player p = (Player) e.getEntity();
		if (plugin.noDamage.containsKey(p)) {
			e.setCancelled(true);
		}
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerKick(final PlayerKickEvent e) {
		Player p = e.getPlayer();	
		if (e.getReason().contains("kicked for")) {
			if (p.hasPermission("funnybunny.antikick") || (p.isOp())) {
				if (p.getInventory().getBoots() != null) {
					if (p.getInventory().getBoots().getType() == Material.DIAMOND_BOOTS && (p.hasPermission("funnybunny.antikick")) || (p.hasPermission("funnybunny.antikickalways")) || (p.isOp())) {		
						e.setCancelled(true);
						e.setLeaveMessage(null);
					}
				}	}
			}	}
				@EventHandler(priority = EventPriority.NORMAL)
				public void onSuperJumpCancelEvent(final EntityDamageEvent e) {
					if (!(e.getEntity() instanceof Player)) {
						return;
					}
					Player p = (Player) e.getEntity();
					if (e.getCause() == DamageCause.FALL) {
						if (p.getInventory().getBoots() != null) {
							if (p.getInventory().getBoots().getType() == Material.DIAMOND_BOOTS || (p.getInventory().getBoots().getType() == Material.GOLD_BOOTS)) {
								if (p.hasPermission("funnybunny.superjump") || (p.isOp())) {
									e.setCancelled(true);
								}
							}
						}
					}
				}	}