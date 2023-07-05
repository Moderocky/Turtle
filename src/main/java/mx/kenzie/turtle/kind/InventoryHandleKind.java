package mx.kenzie.turtle.kind;

import mx.kenzie.craftscript.kind.Kind;
import mx.kenzie.craftscript.script.core.MainThreadStatement;
import mx.kenzie.turtle.handle.InventoryHandle;
import mx.kenzie.turtle.handle.ItemQuery;

public class InventoryHandleKind extends Kind<InventoryHandle> {

    public InventoryHandleKind() {
        super(InventoryHandle.class);
    }

    @Override
    public Object getProperty(InventoryHandle handle, String s) {
        if (handle == null) return null;
        return switch (s) {
            case "type" -> this;
            case "is_empty" -> handle.inventory().isEmpty();
            case "read_only" -> handle.readOnly();
            case "contains" -> new MainThreadStatement(arguments -> handle.contains(ItemQuery.of(arguments.get(0))));
            case "remove" -> new MainThreadStatement(arguments -> handle.remove(ItemQuery.of(arguments.get(0))));
            case "add" -> new MainThreadStatement(arguments -> handle.add(arguments.get(0)));
            default -> null;
        };
    }

    @Override
    public String[] getProperties() {
        return new String[]{"type", "is_empty", "read_only", "contains", "remove", "add"};
    }

    @Override
    public String toString() {
        return "#inventory";
    }

}
