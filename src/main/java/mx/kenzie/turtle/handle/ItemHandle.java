package mx.kenzie.turtle.handle;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class ItemHandle implements Handle {

    protected volatile ItemStack item;
    protected volatile boolean readOnly;

    public ItemHandle(ItemStack item, boolean readOnly) {
        this.item = item;
        this.readOnly = readOnly;
    }

    public ItemHandle(ItemStack item) {
        this(item, item == null || item.getAmount() < 1 && item.getType().isAir());
    }

    public Entity drop(Location location) {
        if (location == null) return null;
        if (item == null || this.readOnly) return null;
        final Item entity = location.getWorld().dropItem(location, item);
        this.item = null;
        this.readOnly = true;
        return entity;
    }

    public ItemStack item() {return item;}

    @Override
    public boolean readOnly() {return readOnly;}

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (ItemHandle) obj;
        return Objects.equals(this.item, that.item) &&
            this.readOnly == that.readOnly;
    }

    @Override
    public int hashCode() {
        return Objects.hash(item, readOnly);
    }

    @Override
    public String toString() {
        if (item == null) return "null";
        return item.getType().getKey().toString();
    }

}
