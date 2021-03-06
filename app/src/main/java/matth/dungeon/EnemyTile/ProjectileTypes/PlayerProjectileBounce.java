package matth.dungeon.EnemyTile.ProjectileTypes;

import matth.dungeon.EnemyTile.SpriteTypes.Enemy;
import matth.dungeon.Utility.MainUtility;
import matth.dungeon.Utility.PlayerUtility;

public class PlayerProjectileBounce extends PlayerProjectile {

    private final String PROJECTILE_NAME = "projectile_bounce";
    private final float DAMAGE = 8;
    private final float VELOCITY = 25;

    private boolean bounce = false;

    public PlayerProjectileBounce(MainUtility mainUtility, PlayerUtility playerUtility) {
        super(mainUtility, playerUtility);
    }

    @Override
    public void effect(Enemy enemy) {

        if (bounce) {
            enemy.takeDamage(damage * 2);
        }
        else {
            enemy.takeDamage(damage);
        }

        deleteAll();
    }

    @Override
    public void init() {
        move.run();
    }

    @Override
    public void movePattern() {
        if (!bounce) {
            PlayerUtility.moveImage(getProjectileImage(), getX(), getY() - VELOCITY);
        }
        else {
            PlayerUtility.moveImage(getProjectileImage(), getX(), getY() + VELOCITY);
        }
    }

    @Override
    public void setDamage() {
        super.damage = DAMAGE;
    }

    @Override
    public void setProjectileName() {
        super.projectileName = PROJECTILE_NAME;
    }

    @Override
    public void delete() {

    }

    @Override
    public void outOfBounds() {
        if (projectileImage.getY() < 0) {
            bounce = true;
        }
        if (projectileImage.getY() > mainUtility.getScreenHeight()) {
            deleteAll();
        }
        if (projectileImage.getX() < 0) {
            deleteAll();
        }
        if (projectileImage.getY() > mainUtility.getScreenHeight()) {
            deleteAll();
        }
    }
}
