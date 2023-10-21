package mogbos.brix.utils.level;

import java.util.ArrayList;

public class Level {

    ArrayList<Block> blocks;

    public Level(){
        blocks = new ArrayList<>();
    }

    public ArrayList<Block> getBlocks() {
        return blocks;
    }

    public void addBlock(Block b) {
        blocks.add(b);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Level [Blocks: [");

        for (Block block : blocks) {
            stringBuilder.append(block.toString());
            stringBuilder.append(", ");
        }

        // Remove the trailing ", " if there are blocks in the list
        if (!blocks.isEmpty()) {
            stringBuilder.setLength(stringBuilder.length() - 2);
        }

        stringBuilder.append("]]");

        return stringBuilder.toString();
    }
}
