package matth.dungeon;

public class Projectile {

    public MainUtility mainUtility;

    public String projectileName;

    Projectile(MainUtility mainUtility) {
        this.mainUtility = mainUtility;
    }

    //TODO make periodic runnable check for active activity for all projectiles
}
