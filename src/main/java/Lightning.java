import ars.AStory.api.SkillAPI;
import ars.AStory.api.data;
import ars.AStory.api.rd;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class Lightning{

    public boolean skill(Player player){

        Block block = player.getTargetBlock(null, (int) SkillAPI.getSkillInfo(player, "Lightning", "MaxDistance"));
        if(block == null){
            return false;
        }
        Location location = block.getLocation();

        player.getWorld().strikeLightning(location);

        double modifier = SkillAPI.getSkillInfo(player, "Lightning", "Modifier");
        int duration = (int) SkillAPI.getSkillInfo(player, "Lightning", "Duration");
        boolean silence = SkillAPI.getSkillBaseBoolean(player, "Lightning", "Silence");

        for(LivingEntity entity : SkillAPI.getEntityFromLoc(location, 1)){
            SkillAPI.Skilldamage(player, entity, modifier, 0, rd.isCRIT(data.PlayerCRITP.get(player.getUniqueId())), "AP", "Lightning");
            if(silence){
                SkillAPI.Silence.put(player, System.currentTimeMillis() + duration);
            }
        }
        return true;
    }

}
