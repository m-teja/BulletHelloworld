package matth.dungeon;

public class PlayerProjectile extends Projectile {

    private PlayerUtility playerUtility;

    PlayerProjectile(MainUtility mainUtility, PlayerUtility playerUtility) {
        super(mainUtility);
        this.playerUtility = playerUtility;
    }
}