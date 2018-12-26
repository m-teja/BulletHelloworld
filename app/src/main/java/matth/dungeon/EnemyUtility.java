package matth.dungeon;

import android.graphics.Rect;
import android.widget.ImageView;

public class EnemyUtility {

    private PlayerSprite playerSprite;

    EnemyUtility(PlayerSprite playerSprite) {
        this.playerSprite = playerSprite;
    }

    public static void moveImage(ImageView image, int x, int y) {
        image.setX(x);
        image.setY(y);
    }

    public boolean checkPlayerOverlap(ImageView image) {
        Rect playerRect = new Rect();
        Rect imageRect = new Rect();

        playerSprite.getPlayerImage().getHitRect(playerRect);
        image.getHitRect(imageRect);

        if (playerRect.intersect(imageRect)) {
            return true;
        }
        else {
            return false;
        }
    }


}