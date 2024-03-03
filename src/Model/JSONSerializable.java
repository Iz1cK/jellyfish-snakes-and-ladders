package Model;

import com.google.gson.JsonObject;

public abstract class JSONSerializable {

    abstract void fromJSON(JsonObject jsonObject);
    abstract JsonObject toJSON();
    
    // Template method
    public final void readJSON(JsonObject jsonObject) {
        // Call the abstract method with the provided JsonObject
        fromJSON(jsonObject);
        
        toJSON();
    }
}
