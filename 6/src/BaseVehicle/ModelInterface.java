package BaseVehicle;

import java.io.Serializable;

public interface ModelInterface extends Serializable, Cloneable {

        String getModelName();
        float getPrice();

        void setModelName(String modelName);
        void setPrice(float price);

}
