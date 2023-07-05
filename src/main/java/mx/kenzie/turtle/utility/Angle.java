package mx.kenzie.turtle.utility;

import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

import static java.lang.Math.PI;

public record Angle(double yaw, double pitch) {

    private static final double TAU = 2 * PI;

    public Angle(Vector vector) {
        this(getYaw(vector), getPitch(vector));
    }

    private static double getYaw(Vector vector) {
        final double x = vector.getX(), z = vector.getZ();
        final double theta = Math.atan2(-x, z);
        if (x == 0 && z == 0) return 0;
        return Math.toDegrees((theta + TAU) % TAU);
    }

    private static double getPitch(Vector vector) {
        final double x = vector.getX(), z = vector.getZ();
        if (x == 0 && z == 0) return vector.getY() > 0 ? -90 : 90;
        final double x2 = x * x;
        final double z2 = z * z;
        final double xz = Math.sqrt(x2 + z2);
        return Math.toDegrees(Math.atan(-vector.getY() / xz));
    }

    public static Angle of(Entity entity) {
        return new Angle(
            entity.getLocation().getYaw(),
            entity.getLocation().getPitch()
        );
    }

    public Vector toVector() {
        final Vector vector = new Vector();
        final double rotX = Math.toRadians(yaw), rotY = Math.toRadians(pitch);
        final double xz = Math.cos(rotY);
        vector.setY(-Math.sin(rotY));
        vector.setX(-xz * Math.sin(rotX));
        vector.setZ(xz * Math.cos(rotX));
        return vector;
    }

    public Angle of(Vector vector) {
        final double x = vector.getX(), z = vector.getZ();
        final double theta = Math.atan2(-x, z);
        if (x == 0 && z == 0) return new Angle(0, vector.getY() > 0 ? -90 : 90);
        final double x2 = x * x;
        final double z2 = z * z;
        final double xz = Math.sqrt(x2 + z2);
        return new Angle(Math.toDegrees((theta + TAU) % TAU), Math.toDegrees(Math.atan(-vector.getY() / xz)));
    }

    @Override
    public String toString() {
        return "[%06.2f ψ + %06.2f θ]".formatted(yaw, pitch);
    }

}
