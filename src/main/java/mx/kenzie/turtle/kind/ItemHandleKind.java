package mx.kenzie.turtle.kind;

import mx.kenzie.craftscript.kind.Kind;
import mx.kenzie.craftscript.kind.LocationKind;
import mx.kenzie.craftscript.script.core.MainThreadStatement;
import mx.kenzie.turtle.handle.ItemHandle;

public class ItemHandleKind extends Kind<ItemHandle> {

    public ItemHandleKind() {
        super(ItemHandle.class);
    }

    @Override
    public Object getProperty(ItemHandle handle, String s) {
        if (handle == null) return null;
        return switch (s) {
            case "read_only" -> handle.readOnly();
            case "type" -> handle.item() != null ? handle.item().getType().getKey() : null;
            case "amount" -> handle.item() != null ? handle.item().getAmount() : 0;
            case "drop" ->
                new MainThreadStatement(arguments -> handle.drop(LocationKind.LOCATION.convert(arguments.get(0))));
            default -> null;
        };
    }

    @Override
    public String[] getProperties() {
        return new String[]{"read_only", "type", "amount", "drop"};
    }

    @Override
    public String toString() {
        return "#item";
    }

}
