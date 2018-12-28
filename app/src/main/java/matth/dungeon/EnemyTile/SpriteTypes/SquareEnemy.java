package matth.dungeon.EnemyTile.SpriteTypes;

import android.app.Activity;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.util.Log;

import matth.dungeon.Utility.EnemyUtility;
import matth.dungeon.Utility.MainUtility;
import matth.dungeon.R;

public class SquareEnemy extends Enemy implements EnemyBehaviour {

    private final float STARTING_HEALTH = 30;
    private final int DAMAGE = 10;
    private final String SPRITE_NAME = "square_enemy";
    private final String PROJECTILE_NAME = "square_projectile";
    private final int DESTINATION_DELAY = 800;

    private Handler moveSprite = new Handler();
    private Handler updatePlayerPosition = new Handler();

    private float destinationX;
    private float destinationY;
    private int velocity = 15;
    private float velocityX;
    private float velocityY;

    public SquareEnemy(MainUtility mainUtility, EnemyUtility enemyUtility) {
        super(mainUtility, enemyUtility);
        super.health = STARTING_HEALTH;
        super.spriteName = SPRITE_NAME;
        super.projectileName = PROJECTILE_NAME;
    }

    public void init() {
        runUpdatePlayerPosition.run();
        move.run();
    }

    public void delete() {
        Log.d("test", "square terminated");
        terminated = true;
        moveSprite.removeCallbacksAndMessages(null);
        updatePlayerPosition.removeCallbacksAndMessages(null);

        ConstraintLayout cl = ((Activity)mainUtility.getCon()).findViewById(R.id.enemyLay);
        cl.removeView(super.getSprite());
    }

    private void effect() {
        enemyUtility.getPlayerSprite().setHealth(enemyUtility.getPlayerSprite().getHealth() - DAMAGE);
    }

    private void calcVelocity() {

        float differenceX = destinationX - super.getX();
        float differenceY = destinationY - super.getY();

        if (differenceX != 0 && differenceY != 0) {
            float differenceXY = (float)Math.sqrt(Math.pow(differenceX, 2) + Math.pow(differenceY, 2));

            float cosAngle = (float)Math.acos(differenceY/differenceXY);
            float sinAngle = (float)Math.asin(differenceX/differenceXY);

            velocityY = (float)(Math.cos(cosAngle) * velocity);
            velocityX = (float)(Math.sin(sinAngle) * velocity);
        }
        else if (differenceX == 0) {
            velocityX = 0;
            if (destinationY > super.getY()) {
                velocityY = velocity;
            }
            else {
                velocityY = -velocity;
            }
        }
        else {
            velocityY = 0;
            if (destinationX > super.getX()) {
                velocityX = velocity;
            }
            else {
                velocityX = -velocity;
            }
        }

    }

    private Runnable move = new Runnable() {
        @Override
        public void run() {

            EnemyUtility.moveImage(SquareEnemy.super.getSprite(), SquareEnemy.super.getX() + velocityX, SquareEnemy.super.getY() + velocityY);

            if (enemyUtility.checkPlayerOverlap(SquareEnemy.super.getSprite())) {
                effect();
                delete();
            }

            if (!SquareEnemy.super.terminated) {
                moveSprite.postDelayed(move, SquareEnemy.super.ANIMATION_DELAY);
            }
        }
    };

    private Runnable runUpdatePlayerPosition = new Runnable() {
        @Override
        public void run() {
            destinationX = SquareEnemy.super.enemyUtility.getPlayerSprite().getX();
            destinationY = SquareEnemy.super.enemyUtility.getPlayerSprite().getY();

            calcVelocity();

            if (!SquareEnemy.super.terminated) {
                updatePlayerPosition.postDelayed(runUpdatePlayerPosition, DESTINATION_DELAY);
            }

        }
    };


}
