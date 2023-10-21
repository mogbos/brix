package mogbos.brix.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import mogbos.brix.utils.level.Block;
import mogbos.brix.utils.level.Level;

public class LevelParser {

    JsonReader jsonReader = new JsonReader();

    public LevelParser(){}

    public Level getLevel(){
        Level level = new Level();
        String levelPath = "levels/level00-example.json";
        JsonValue jsonValue = jsonReader.parse(Gdx.files.internal(levelPath));
        JsonValue jsonBlocks = jsonValue.get("blocks");
        for(int i = 0; i < jsonBlocks.size; i++) {
            JsonValue jsonBlock = jsonBlocks.get(i);
            Block block = new Block();
            block.setRow(jsonBlock.getInt("row"));
            block.setColumn(jsonBlock.getInt("column"));
            block.setStamina(jsonBlock.getInt("stamina"));
            level.addBlock(block);
        }
        validateLevel(level);
        return level;
    }

    void validateLevel(Level level){
        byte[][] validationMatrix = new byte[17][10];
        for(int x = 0; x < 17; x++){
            for(int y = 0; y < 10; y++){
                validationMatrix[x][y] = 0;
            }
        }
        for(int i = 0; i < level.getBlocks().size(); i++) {
            Block block = level.getBlocks().get(i);
            if(block.getColumn() > 17 || block.getRow() > 10){
                throw new Error("Invalid level map!");
            }
            validationMatrix[block.getColumn()-1][block.getRow()-1]++;
        }
        for(int x = 0; x < 17; x++){
            for(int y = 0; y < 10; y++){
                if(validationMatrix[x][y] > 1) {
                    throw new Error("Invalid level map!");
                }
            }
        }
    }
}
