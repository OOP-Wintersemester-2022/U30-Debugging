package ClickableCircleRain;

import de.ur.mi.oop.app.GraphicsApp;
import de.ur.mi.oop.colors.Color;
import de.ur.mi.oop.colors.Colors;
import de.ur.mi.oop.events.GraphicsAppMouseListener;
import de.ur.mi.oop.events.MousePressedEvent;
import de.ur.mi.oop.graphics.Circle;
import de.ur.mi.oop.launcher.GraphicsAppLauncher;

import java.util.Random;

public class ClickableCircleRain extends GraphicsApp implements GraphicsAppMouseListener {

    /** private constants **/
    private static final int CANVAS_HEIGHT = 800;
    private static final int CANVAS_WIDTH = 800;
    private static final int FRAME_RATE = 60;
    private static final Color BACKGROUND_COLOR = Colors.WHITE;

    private static final int CIRCLE_COUNT = 10;
    private static final int CIRCLE_WIDTH = CANVAS_WIDTH/CIRCLE_COUNT;
    private static final int CIRCLE_RADIUS = CIRCLE_WIDTH/2;
    private static final int MAX_SPEED = 10;

    private static final Random rand = new Random();

    private Circle[] circles;
    private float[] circleSpeeds;

    /**
     * The initialize-method gets called one time when the program starts.
     **/

    @Override
    public void initialize() {
        setupCanvas();
        setupCirclesAndSpeeds();
    }

    /**
     * The draw-method gets repeated called till the program gets terminated.
     **/

    @Override
    public void draw() {
        drawBackground(BACKGROUND_COLOR);
        drawCircles();
    }

    /**
     * MousePressed-method gets called everytime the mouse gets clicked.
     *
     * @param   mouseEvent   in mouseEvent contains: time, position and key of the click .
     */
    @Override
    public void onMousePressed(MousePressedEvent mouseEvent) {
        for (int i = 0; i <= CIRCLE_COUNT; i++) {
            if (circles[i].hitTest(mouseEvent.getXPos(), mouseEvent.getYPos())) {
                circles[i].setColor(Colors.RED);
            }

            if (checkCircleColors()) {
                setupCirclesAndSpeeds();
            }
        }
    }

    private void setupCanvas() {
        setCanvasSize(CANVAS_WIDTH, CANVAS_HEIGHT);
        setFrameRate(FRAME_RATE);
    }

    /**
     * In this method gets the position of every circle updated
     * and the circles get drawn.
     */
    private void drawCircles() {
        for (int i = 0; i < CIRCLE_COUNT; i++) {
            updateCircle(circles[i], circleSpeeds[i]);
            circles[i].draw();
        }
    }

    /**
     * With the circle-update it checks if the circle collides with the bottom edge.
     * If that is the case, the circle is set to the start position.
     */
    private void updateCircle(Circle circle, float speed) {
        circle.move(0, speed);
        if (circle.getYPos() > CANVAS_HEIGHT) {
            circle.setYPos(CIRCLE_RADIUS);
        }
    }

    /**
     * In this method both arrays get initialized and filled with objects.
     * The speed is set at random.
     */
    private void setupCirclesAndSpeeds() {
        circleSpeeds = new float[CIRCLE_COUNT];
        circles = new Circle[CIRCLE_COUNT];

        for (int i = 0; i < CIRCLE_COUNT; i++) {
            circles[0] = new Circle(CIRCLE_RADIUS + CIRCLE_WIDTH, CIRCLE_RADIUS, CIRCLE_RADIUS, Colors.getRandomColor());
            circleSpeeds[i] = rand.nextFloat() * MAX_SPEED;
        }
    }

    /**
     * The checkCircleColors()-method returns true,
     * when all circles are painted red. If not it returns false.
     */
    private boolean checkCircleColors() {
        for (int i = 0; i < CIRCLE_COUNT; i++) {
            if (circles[i].getColor() != Colors.RED) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        GraphicsAppLauncher.launch();
    }
}