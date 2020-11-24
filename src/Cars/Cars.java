package Cars;

import de.ur.mi.oop.app.GraphicsApp;
import de.ur.mi.oop.colors.Color;
import de.ur.mi.oop.colors.Colors;
import de.ur.mi.oop.launcher.GraphicsAppLauncher;

import java.util.ArrayList;

public class Cars extends GraphicsApp {

    /** private constants **/
    private static final int CANVAS_HEIGHT = 800;
    private static final int CANVAS_WIDTH = 800;
    private static final int FRAME_RATE = 60;
    private static final Color BACKGROUND_COLOR = Colors.WHITE;

    private static final int CAR_NUM = 100;
    private static final int CAR_WIDTH = 15;
    private static final int CAR_HEIGHT = 5;

    private ArrayList<Car> cars;

    /**
     * The initialize-method gets called one time when the program starts.
     **/

    @Override
    public void initialize() {
        setupCanvas();
        setupCars();
    }

    /**
     * The draw-method gets repeated called till the program gets terminated.
     **/

    @Override
    public void draw() {
        drawBackground(BACKGROUND_COLOR);
        drawCars();
    }

    private void setupCanvas() {
        setCanvasSize(CANVAS_WIDTH, CANVAS_HEIGHT);
        setFrameRate(FRAME_RATE);
    }

    private void drawCars() {
        for(Car car: cars) {
            car.update();
            car.draw();
        }
    }

    private void setupCars() {
        cars = new ArrayList<Car>();
        for (int i = 0; i < CAR_NUM; i++) {
            Car car = new Car(CAR_WIDTH, CAR_HEIGHT, CANVAS_WIDTH, CANVAS_HEIGHT);
            cars.add(car);
        }
    }

    public static void main(String[] args) {
        GraphicsAppLauncher.launch();
    }
}