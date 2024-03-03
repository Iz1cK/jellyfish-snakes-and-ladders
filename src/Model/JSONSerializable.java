package Model;

import com.google.gson.JsonObject;

public abstract class JSONSerializable {

    abstract void fromJSON(JsonObject jsonObject);
    
    // Template method
    public final void readJSON(JsonObject jsonObject) {
        // Call the abstract method with the provided JsonObject
        fromJSON(jsonObject);
    }
}
