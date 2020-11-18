package Cars;

import de.ur.mi.oop.colors.Color;
import de.ur.mi.oop.colors.Colors;
import de.ur.mi.oop.graphics.Rectangle;

import java.util.Random;

public class Car {

    private float speed;
    private Rectangle car;
    private int canvasWidth;

    private static final float MIN_SPEED = 2;
    private static final float MAX_SPEED = 10;

    private Random random;

    public Car(int carWidth, int carHeight, int canvasWidth, int canvasHeight) {
        this.canvasWidth = canvasWidth;
        random = new Random();

        Color carColor = Colors.getRandomColor();
        int randomYPos = getRandomYPos(carHeight, canvasHeight);
        speed = getRandomSpeed();
    }

    private float getRandomSpeed() {
        return random.nextFloat() * (MAX_SPEED - MIN_SPEED) + MIN_SPEED;
    }

    private int getRandomYPos(int carHeight, int canvasHeight) {
        int laneNumTotal = carHeight / canvasHeight;
        int laneNum = random.nextInt(laneNumTotal);
        return laneNum * carHeight;
    }

    public void draw() {
        car.draw();
    }

    public void update() {
        car.move(speed, 0);
        if (car.getXPos() > canvasWidth) {
            car.setXPos(car.getWidth());
        }
    }
}

