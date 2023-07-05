package mx.kenzie.turtle;

import mx.kenzie.craftscript.variable.LibraryObject;
import org.bukkit.plugin.java.JavaPlugin;

public class Turtles extends LibraryObject implements AutoCloseable {

    public static Turtles turtles;

    protected final JavaPlugin plugin;

    public Turtles(JavaPlugin plugin) {
        super("get");
        this.plugin = plugin;
        Turtles.turtles = this;
    }

    @Override
    public void close() {

    }

    @Override
    public Object get(String s) {
        return switch (s) {
            case "get" -> null;
            default -> null;
        };
    }

}
