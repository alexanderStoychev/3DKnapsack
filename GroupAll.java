package gui;

import javafx.scene.Group;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;

/**
 * The GroupAll class extends the JavaFX Group class.
 * It is designed to represent a group of 3D objects with added functionalities for translation,
 * rotation, and scaling.
 */

public class GroupAll extends Group{


    public Translate translate = new Translate();
    public Rotate rotateX = new Rotate(0, Rotate.X_AXIS);
    public Rotate rotateY = new Rotate(0, Rotate.Y_AXIS);
    public Rotate rotateZ = new Rotate(0, Rotate.Z_AXIS);
    public Scale scale = new Scale();


    /**
     * Constructor for GroupAll.
     * Initializes the group and adds all transformation objects to it.
     */
    public GroupAll() {
        super();
        // Adding all transformation objects to the group's transformation list.
        this.getTransforms().addAll(translate, rotateZ, rotateY, rotateX, scale);
    }

    /**
     * Sets the rotation angle around the Z-axis.
     *
     * @param z The angle in degrees to rotate around the Z-axis.
     */
    public void setRotateZ(double z) {
        rotateZ.setAngle(z);
    }

    /**
     * Sets the pivot point for all rotations.
     * This method adjusts the pivot points for the X, Y, and Z rotations to the same specified coordinates.
     *
     * @param group The GroupAll object whose pivot points are to be set.
     * @param xCoord The X coordinate of the pivot point.
     * @param yCoord The Y coordinate of the pivot point.
     * @param zCoord The Z coordinate of the pivot point.
     */
    public static void setPivot(GroupAll group, int xCoord, int yCoord, int zCoord) {
        group.rotateX.setPivotX(xCoord);
        group.rotateX.setPivotY(yCoord);
        group.rotateX.setPivotZ(zCoord);
        group.rotateY.setPivotX(xCoord);
        group.rotateY.setPivotY(yCoord);
        group.rotateY.setPivotZ(zCoord);
        group.rotateZ.setPivotX(xCoord);
        group.rotateZ.setPivotY(yCoord);
        group.rotateZ.setPivotZ(zCoord);
    }
}
