package mx.kenzie.turtle.kind;

import mx.kenzie.craftscript.kind.BlockKind;
import mx.kenzie.craftscript.kind.Kind;
import mx.kenzie.turtle.handle.BlockHandle;

import java.util.ArrayList;
import java.util.List;

public class BlockHandleKind extends Kind<BlockHandle> {

    public static final BlockHandleKind BLOCK_HANDLE = new BlockHandleKind();

    public BlockHandleKind() {
        super(BlockHandle.class);
    }

    @Override
    public Object getProperty(BlockHandle handle, String s) {
        if (handle == null) return null;
        return switch (s) {
            case "read_only" -> handle.readOnly();
            case "is_empty" -> handle.block().isEmpty();
            case "inventory" -> handle.getInventory();
            case "type" -> handle.getType().getKey();
            default -> BlockKind.BLOCK.getProperty(handle.block(), s);
        };
    }

    @Override
    public String toString() {
        return "#block";
    }

    @Override
    public String[] getProperties() {
        final List<String> list = new ArrayList<>(List.of(BlockKind.BLOCK.getProperties()));
        list.addAll(List.of("read_only", "inventory"));
        return list.toArray(new String[0]);
    }

}
