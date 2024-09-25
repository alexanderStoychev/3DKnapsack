package gui;
import bot.GreedyAlg;
import javafx.application.Platform;
import javafx.geometry.Point3D;
import javafx.scene.shape.*;
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.scene.paint.*;

/**
 * The GUI class extends Application and is responsible for creating and managing the graphical user interface.
 * It sets up a 3D environment where users can visualize and interact with the knapsack problem solution.
 */

public class GUI extends Application{

    //Initializing cargo parameters
    public static int depth = 33;
    public static int height = 8;
    public static int width = 5;


    /**
     * Creating GroupAll objects to allow control of the camera's around the 3D world.
     */

    final Group mainContainer = new Group();
    final GroupAll visualisationGroup = new GroupAll();
    final GroupAll packageGroup = new GroupAll();
    final GroupAll world = new GroupAll();
    final GroupAll cameraRotationGroup = new GroupAll();
    final GroupAll cameraPositionGroup = new GroupAll();
    final GroupAll cameraGroup = new GroupAll();
    final PerspectiveCamera camera = new PerspectiveCamera(true);

    /**
     * Initializing camera settings
     */
    private final double initialCameraDistance = -depth * 15;
    private final double mouseDragSensitivity = 0.1;
    private final double cameraRotationSpeed = 2.0;
    private final int boxSize = 10;
    private final int centerXCoord = (width * boxSize) / 2;
    private final int centerYCoord = (height * boxSize) / 2;
    private final int centerZCoord = (depth * boxSize) / 2;

    /**
     * Initializing variables to track mouse position
     */

    double oldMouseX;
    double oldMouseY;

    /**
     * Initializing GUI components
     */

    public static String type;
    public static String value1Text;
    public static String value2Text;
    public static String value3Text;
    static int[] values = new int[3];
    private static Label scoreLabel;
    public Button clearButton;
    static boolean stop;
    Thread thread;


    /**
     * Method to build the camera and set its initial position and rotation
     */


    private void setCamera(){
        mainContainer.getChildren().add(cameraRotationGroup);

        cameraRotationGroup.getChildren().add(cameraPositionGroup);
        cameraPositionGroup.getChildren().add(cameraGroup);
        cameraGroup.getChildren().add(camera);
        cameraGroup.setRotateZ(180.0);

        double cameraNearClippingPlane = 0.1;
        camera.setNearClip(cameraNearClippingPlane);
        double cameraFarClippingPlane = 2000.0;
        camera.setFarClip(cameraFarClippingPlane);
        camera.setTranslateZ(initialCameraDistance);
        camera.setTranslateY(-(depth * ((double) height / width)) - 10);
        camera.setTranslateX(-(depth * ((double) height / width)));

        double initialCameraYAngle = 10;
        cameraRotationGroup.rotateY.setAngle(initialCameraYAngle);
        double initialCameraXAngle = 0;
        cameraRotationGroup.rotateX.setAngle(initialCameraXAngle);

        GroupAll.setPivot(cameraRotationGroup, centerXCoord - (boxSize / 2), centerYCoord - (boxSize / 2), centerZCoord - (boxSize /2));

    }

    /**
     * Mouse handler allowing users to turn the cargo
     */

    private void handleMouse(SubScene subScene) {
        subScene.setOnMousePressed(me -> {
            oldMouseX = me.getSceneX();
            oldMouseY = me.getSceneY();
        });

        subScene.setOnMouseDragged(me -> {
            double deltaX = me.getSceneX() - oldMouseX;
            double deltaY = me.getSceneY() - oldMouseY;
            oldMouseX = me.getSceneX();
            oldMouseY = me.getSceneY();

            final int speed = 3;

            if (me.isPrimaryButtonDown()) {
                if (me.isShiftDown()) {
                    camera.setTranslateY(camera.getTranslateY() + deltaY * mouseDragSensitivity * (speed * 2));
                    camera.setTranslateX(camera.getTranslateX() + deltaX * mouseDragSensitivity * (speed * 2));
                } else {
                    cameraRotationGroup.rotateY.setAngle(cameraRotationGroup.rotateY.getAngle() - deltaX * mouseDragSensitivity * speed * cameraRotationSpeed);
                    cameraRotationGroup.rotateX.setAngle(cameraRotationGroup.rotateX.getAngle() + deltaY * mouseDragSensitivity * speed * cameraRotationSpeed);
                }
            }
        });

        subScene.setOnScroll(event -> {
            double zoomFactor = event.getDeltaY() < 0 ? 0.95 : 1.05;
            camera.setTranslateZ(camera.getTranslateZ() * zoomFactor);
        });
    }
    /**
     * Method to set a color of a package based on an integer
     *
     * @param id represents the parcel's id
     * @return material
     */
    private PhongMaterial getColor(int id) {
        PhongMaterial material = new PhongMaterial();
        switch (id) {
            case 1:
                material.setDiffuseColor(Color.BEIGE);
                break;
            case 2:
                material.setDiffuseColor(Color.ROYALBLUE);
                break;
            case 3:
                material.setDiffuseColor(Color.CHOCOLATE);
                break;
            default:
                break;
        }
        return material;
    }
    /**
     *  Method to create outlines of the cargo in 3D
     */


    public void drawCargoOutline() {

        int boxWidth = centerXCoord * 2;
        int boxHeight = centerYCoord * 2;
        int boxDepth = centerZCoord * 2;
        int offset = -(boxSize / 2);

        Point3D p1 = new Point3D(offset, offset, offset);
        Point3D p2 = new Point3D(boxWidth + offset, offset, offset);
        Point3D p3 = new Point3D(offset, boxHeight + offset, offset);
        Point3D p4 = new Point3D(boxWidth + offset, boxHeight + offset, offset);
        Point3D p5 = new Point3D(offset, offset, boxDepth + offset);
        Point3D p6 = new Point3D(boxWidth + offset, offset, boxDepth + offset);
        Point3D p7 = new Point3D(offset, boxHeight + offset, boxDepth + offset);
        Point3D p8 = new Point3D(boxWidth + offset, boxHeight + offset, boxDepth + offset);

        drawLine(p1, p2);
        drawLine(p1, p3);
        drawLine(p1, p5);
        drawLine(p2, p6);
        drawLine(p2, p4);
        drawLine(p3, p4);
        drawLine(p3, p7);
        drawLine(p4, p8);
        drawLine(p5, p6);
        drawLine(p5, p7);
        drawLine(p6, p8);
        drawLine(p7, p8);
    }

    /**
     * Method creating lines
     *
     * @param origin start point of a line
     * @param target end point of a line
     */

    public void drawLine(Point3D origin, Point3D target) { // Equation from math.stackexchange.com

        double width = 0.6;

        Point3D yPoint = new Point3D(0, 1, 0); // Reference point for alignment
        Point3D diff = target.subtract(origin); // Calculating the vector difference between target and origin
        double height = diff.magnitude();

        // Finding the midpoint for positioning the line
        Point3D mid = target.midpoint(origin);
        Translate moveToOriginCenter = new Translate(mid.getX(), mid.getY(), mid.getZ());

        // Calculating rotation axis and angle to align the line with the points
        Point3D rAxis = diff.crossProduct(yPoint);
        double angle = Math.acos(diff.normalize().dotProduct(yPoint));
        Rotate rotate = new Rotate(-Math.toDegrees(angle), rAxis);

        Cylinder line = new Cylinder(width, height);

        line.getTransforms().addAll(moveToOriginCenter, rotate);
        world.getChildren().addAll(line);
    }
    /**
     * Method to construct 3D boxes based on an array
     *
     * @param arr data describing the 3D box
     */

    private void createBox(int[][][] arr) {

        packageGroup.getChildren().clear();
        visualisationGroup.getChildren().clear();


        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                for (int k = 0; k < arr[i][j].length; k++) {
                    if (arr[i][j][k] == 0)
                        continue;

                    PhongMaterial color = getColor(arr[i][j][k]);

                    Box box = new Box();

                    box.setWidth(boxSize);
                    box.setHeight(boxSize);
                    box.setDepth(boxSize);
                    box.setMaterial(color);
                    box.setTranslateX((k * boxSize));
                    box.setTranslateY((j * boxSize));
                    box.setTranslateZ((i * boxSize));



                    packageGroup.getChildren().add(box);
                }
            }
        }

        visualisationGroup.getChildren().add(packageGroup);
        world.getChildren().addAll(visualisationGroup);
    }


    /**
     * Method that sets up the GUI with all of its components
     *
     * @param primaryStage main window of the application
     */

    public void start(Stage primaryStage){
        primaryStage.setTitle("3D Knapsack Solver");

        BorderPane root = new BorderPane();

        // Content Pane (Top)
        Pane contentPane = new Pane();
        contentPane.setPrefSize(900, 400);
        contentPane.setMaxHeight(Double.MAX_VALUE);
        contentPane.setStyle("-fx-background-color: #4891cc;");


        root.setTop(contentPane);

        Label currentValueLabel = new Label("Current Value: ");

        currentValueLabel.setLayoutX(10);
        currentValueLabel.setLayoutY(10);
        currentValueLabel.setTextFill(Color.WHITE);
        currentValueLabel.setFont(new Font("Arial", 15));

        scoreLabel = new Label("0");
        scoreLabel.setId("score");
        scoreLabel.setLayoutX(110);
        scoreLabel.setLayoutY(currentValueLabel.getLayoutY());
        scoreLabel.setTextFill(Color.WHITE);
        scoreLabel.setFont(new Font("Arial", 15));

        contentPane.getChildren().addAll(currentValueLabel, scoreLabel);

        root.setTop(contentPane);

        // Control Pane (Bottom)
        Pane controlPane = new Pane();
        controlPane.setPrefHeight(100);
        controlPane.setStyle("-fx-background-color: #e3e2e2;");


        RadioButton parcels = new RadioButton();
        RadioButton pentominoes = new RadioButton();

        ToggleGroup radioGroup = new ToggleGroup();
        parcels.setToggleGroup(radioGroup);
        pentominoes.setToggleGroup(radioGroup);

        radioGroup.selectedToggleProperty().addListener((observable,oldValue,newValue) ->{
            RadioButton selectedRadioButton = (RadioButton) newValue;
            type = selectedRadioButton.getText();
        });

        parcels.setLayoutX(20);
        pentominoes.setLayoutX(20);

        parcels.setLayoutY(20);
        pentominoes.setLayoutY(55);

        parcels.setText("Parcels");
        pentominoes.setText("Pentominoes");

        parcels.setFont(new Font("Arial", 15));
        pentominoes.setFont(new Font("Arial", 15));

        parcels.setVisible(true);
        pentominoes.setVisible(true);

        controlPane.getChildren().addAll(parcels,pentominoes);

        Label valuesLabel = new Label();
        valuesLabel.setLayoutX(200);
        valuesLabel.setLayoutY(40);
        valuesLabel.setText("VALUES:");
        valuesLabel.setFont(new Font("Arial", 20));

        TextField value1 = new TextField();
        value1.setLayoutX(300);
        value1.setLayoutY(40);

        value1.setPrefWidth(40);

        TextField value2 = new TextField();
        value2.setLayoutX(350);
        value2.setLayoutY(40);

        value2.setPrefWidth(40);

        TextField value3 = new TextField();
        value3.setLayoutX(400);
        value3.setLayoutY(40);

        value3.setPrefWidth(40);

        controlPane.getChildren().addAll(valuesLabel, value1, value2,value3);


        Button findSolution = new Button();
        findSolution.setLayoutX(500);
        findSolution.setLayoutY(25);

        findSolution.setPrefHeight(50);
        findSolution.setPrefWidth(150);

        findSolution.setText("Find Solution");
        findSolution.setFont(new Font("Arial", 13));

        findSolution.setOnAction(event -> {
            value1Text = value1.getText();
            value2Text = value2.getText();
            value3Text = value3.getText();

            values = new int[] {Integer.parseInt(value1.getText()), Integer.parseInt(value2.getText()), Integer.parseInt(value3.getText())};

            clearButton.setDisable(false);
            findSolution.setDisable(true);
            runBot();

        });

        clearButton = new Button();
        clearButton.setLayoutX(700);
        clearButton.setLayoutY(25);

        clearButton.setPrefHeight(50);
        clearButton.setPrefWidth(150);

        clearButton.setText("Clear");

        clearButton.setFont(new Font("Arial", 13));

        clearButton.setOnAction(event -> {
            // Clear the container
            packageGroup.getChildren().clear();
            world.getChildren().remove(packageGroup);

            // Reset text fields
            value1.setText("");
            value2.setText("");
            value3.setText("");

            // Reset label
            scoreLabel.setText("0");

            findSolution.setDisable(false);


        });


        controlPane.getChildren().addAll(findSolution, clearButton);

        root.setBottom(controlPane);

        Scene scene = new Scene(root, 900, 500);

        Group subsceneRoot = new Group();

        drawCargoOutline();
        setCamera();

        subsceneRoot.getChildren().add(world);

        SubScene subScene = new SubScene(subsceneRoot, 900, 500, true, SceneAntialiasing.BALANCED);
        subScene.setCamera(camera);
        contentPane.getChildren().add(subScene);
        handleMouse(subScene);
        subScene.setPickOnBounds(true);


        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();




    }

    private void runBot(){
        GreedyAlg.setVisualizer(this);
        thread = new Thread(new GreedyAlg());
        stop = false;
        thread.start();
    }
    /**
     * Method to update the state of the 3D view
     *
     * @param newArr array representing the updated cargo
     */
    public void updateState(int[][][] newArr) {
        world.getChildren().removeAll(visualisationGroup);
        createBox(newArr);
    }

    /**
     * Method to update the score label
     *
     * @param solution result
     */
    public static void setScore(int solution) {
        Platform.runLater(() -> scoreLabel.setText(String.valueOf(solution)));
    }


    /**
     * Method to initiate the Greedy algorithm in a separate thread.
     */



    public void stop(){stop = true;}
}
