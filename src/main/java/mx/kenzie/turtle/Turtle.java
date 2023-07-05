package mx.kenzie.turtle;

import mx.kenzie.craftscript.script.Context;
import mx.kenzie.turtle.utility.Angle;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ItemDisplay;

public class Turtle {

    protected final ItemDisplay display;

    public Turtle(ItemDisplay display) {this.display = display;}

    public boolean look(Angle angle) {
        // todo
        return false;
    }

    public boolean move(Number amount) {
        if (amount == null) return this.move(0);
        else return this.move(amount.intValue());
    }

    public boolean move(int amount) {
        // todo
        return false;
    }

    public boolean isMoveLocationOk(Context context, Location location) {
        if (!Bukkit.isPrimaryThread())
            return (boolean) context.manager().executeOnPrimary(context, () -> this.isMoveLocationOk(location));
        return this.isMoveLocationOk(location);
    }

    protected boolean isMoveLocationOk(Location location) {
        // todo
        return false;
    }

}
