package project.uberclone.utils;

import project.uberclone.model.request.EditPassengerDetailsRequest;

public class EditPassengerDetailsRequestUtil {

    public static EditPassengerDetailsRequest get(){
        return new EditPassengerDetailsRequest("editedName","editedLastname",33);
    }
}
