
package de.MRTeam.MinecartRevolution.util;

import org.bukkit.Effect;
import org.bukkit.EntityEffect;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import org.bukkit.entity.Vehicle;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import de.MRTeam.MinecartRevolution.MinecartRevolution;

public class PlayEffectUtil {

    public PlayEffectUtil(MinecartRevolution plugin) {

        this.plugin = plugin;
    }

    public void playEffect(Minecart minecart, String effect) {

        Location location = minecart.getLocation();
        World world = minecart.getLocation().getWorld();

        if (effect.equalsIgnoreCase("Smoke")) {
            world.playEffect(location, Effect.SMOKE, 1);
            world.playEffect(location, Effect.EXTINGUISH, 1);
        } else if (effect.equalsIgnoreCase("Flame")) {
            world.playEffect(location, Effect.MOBSPAWNER_FLAMES, 1);
        } else if (effect.equalsIgnoreCase("Ender")) {
            world.playEffect(location, Effect.ENDER_SIGNAL, 1);
        } else if (effect.equalsIgnoreCase("Door")) {
            world.playEffect(location, Effect.DOOR_TOGGLE, 1);
        } else if (effect.equalsIgnoreCase("Potion")) {
            world.playEffect(location, Effect.POTION_BREAK, 1);
        } else if (effect.equalsIgnoreCase("Dispenser")) {
            world.playEffect(location, Effect.CLICK1, 1);
        } else if (effect.equalsIgnoreCase("Pressure")) {
            world.playEffect(location, Effect.CLICK2, 1);
        } else if (effect.equalsIgnoreCase("Bow")) {
            world.playEffect(location, Effect.BOW_FIRE, 1);
        } else if (effect.equalsIgnoreCase("Ghast")) {
            world.playEffect(location, Effect.GHAST_SHRIEK, 1);
        } else if (effect.equalsIgnoreCase("Shoot")) {
            world.playEffect(location, Effect.GHAST_SHOOT, 1);
        } else if (effect.equalsIgnoreCase("Step")) {
            world.playEffect(location, Effect.ZOMBIE_DESTROY_DOOR, 1);
            world.playEffect(location, Effect.EXTINGUISH, 1);
        } else if (effect.equalsIgnoreCase("Explosion")) {
            world.createExplosion(location, 0);
            for (Entity entity : minecart.getNearbyEntities(2, 2, 2)) {
                if (! (entity instanceof Player) && ! (entity instanceof Vehicle)) {
                    entity.remove();
                }
            }
        } else if (effect.equalsIgnoreCase("Thunder")) {
            world.strikeLightning(location.add(0, 4, 0));
        } else if (effect.equalsIgnoreCase("Hurt")) {
            for (Entity entity : minecart.getNearbyEntities(5, 5, 5)) {
                entity.playEffect(EntityEffect.HURT);
            }
        } else if (effect.equalsIgnoreCase("Confusion")) {
            for (Entity entity : minecart.getNearbyEntities(5, 5, 5)) {
                if (entity instanceof Player) {
                    Player player = (Player) entity;
                    player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 300, 10));
                }
            }
        } else if (effect.equalsIgnoreCase("Animation")) {
            world.playEffect(location, Effect.SMOKE, 1);
            world.playEffect(location, Effect.MOBSPAWNER_FLAMES, 1);
            world.playEffect(location, Effect.ENDER_SIGNAL, 1);
            world.playEffect(location, Effect.POTION_BREAK, 1);
            world.playEffect(location, Effect.EXTINGUISH, 1);
        }

        if (effect.equalsIgnoreCase("SmokeMulti")) {
            for (Entity entity : minecart.getNearbyEntities(5, 5, 5)) {
                world.playEffect(entity.getLocation(), Effect.SMOKE, 1);
                world.playEffect(entity.getLocation(), Effect.EXTINGUISH, 1);
            }
        } else if (effect.equalsIgnoreCase("FlameMulti")) {
            for (Entity entity : minecart.getNearbyEntities(5, 5, 5)) {
                world.playEffect(entity.getLocation(), Effect.MOBSPAWNER_FLAMES, 1);
            }
        } else if (effect.equalsIgnoreCase("EnderMulti")) {
            for (Entity entity : minecart.getNearbyEntities(5, 5, 5)) {
                world.playEffect(entity.getLocation(), Effect.ENDER_SIGNAL, 1);
            }
        } else if (effect.equalsIgnoreCase("DoorMulti")) {
            for (Entity entity : minecart.getNearbyEntities(5, 5, 5)) {
                world.playEffect(entity.getLocation(), Effect.DOOR_TOGGLE, 1);
            }
        } else if (effect.equalsIgnoreCase("PotionMulti")) {
            for (Entity entity : minecart.getNearbyEntities(5, 5, 5)) {
                world.playEffect(entity.getLocation(), Effect.POTION_BREAK, 1);
            }
        } else if (effect.equalsIgnoreCase("DispenserMulti")) {
            for (Entity entity : minecart.getNearbyEntities(5, 5, 5)) {
                world.playEffect(entity.getLocation(), Effect.CLICK1, 1);
            }
        } else if (effect.equalsIgnoreCase("PressureMulti")) {
            for (Entity entity : minecart.getNearbyEntities(5, 5, 5)) {
                world.playEffect(entity.getLocation(), Effect.CLICK2, 1);
            }
        } else if (effect.equalsIgnoreCase("BowMulti")) {
            for (Entity entity : minecart.getNearbyEntities(5, 5, 5)) {
                world.playEffect(entity.getLocation(), Effect.BOW_FIRE, 1);
            }
        } else if (effect.equalsIgnoreCase("GhastMulti")) {
            for (Entity entity : minecart.getNearbyEntities(5, 5, 5)) {
                world.playEffect(entity.getLocation(), Effect.GHAST_SHRIEK, 1);
            }
        } else if (effect.equalsIgnoreCase("ShootMulti")) {
            for (Entity entity : minecart.getNearbyEntities(5, 5, 5)) {
                world.playEffect(entity.getLocation(), Effect.GHAST_SHOOT, 1);
            }
        } else if (effect.equalsIgnoreCase("StepMulti")) {
            for (Entity entity : minecart.getNearbyEntities(5, 5, 5)) {
                world.playEffect(entity.getLocation(), Effect.STEP_SOUND, 1);
            }
        } else if (effect.equalsIgnoreCase("GunMulti")) {
            for (Entity entity : minecart.getNearbyEntities(5, 5, 5)) {
                world.playEffect(entity.getLocation(), Effect.ZOMBIE_DESTROY_DOOR, 1);
                world.playEffect(entity.getLocation(), Effect.EXTINGUISH, 1);
            }
        } else if (effect.equalsIgnoreCase("ExplosionMulti")) {
            for (Entity entity : minecart.getNearbyEntities(5, 5, 5)) {
                world.createExplosion(entity.getLocation(), 0);
            }
        } else if (effect.equalsIgnoreCase("ThunderMulti")) {
            for (Entity entity : minecart.getNearbyEntities(5, 5, 5)) {
                world.strikeLightning(entity.getLocation().add(0, 4, 0));
            }
        } else if (effect.equalsIgnoreCase("AnimationMulti")) {
            for (Entity entity : minecart.getNearbyEntities(5, 5, 5)) {
                world.playEffect(entity.getLocation(), Effect.SMOKE, 1);
                world.playEffect(entity.getLocation(), Effect.MOBSPAWNER_FLAMES, 1);
                world.playEffect(entity.getLocation(), Effect.ENDER_SIGNAL, 1);
                world.playEffect(entity.getLocation(), Effect.POTION_BREAK, 1);
                world.playEffect(entity.getLocation(), Effect.EXTINGUISH, 1);
            }
        }
    }

    MinecartRevolution plugin;
}
