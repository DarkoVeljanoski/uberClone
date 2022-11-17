package project.uberclone.utils;

import project.uberclone.model.entity.Driver;
import project.uberclone.model.entity.DriverStatusEnum;

public class DriverEntityUtil {

    public static Driver getDriver(){
        return new Driver(1L,"driver@mail.com","password","name","lastname",
                30,5.5,4.0, DriverStatusEnum.AVAILABLE, 1, null);
    }
}
