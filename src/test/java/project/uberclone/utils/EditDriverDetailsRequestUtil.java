package project.uberclone.utils;

import project.uberclone.model.request.EditDriverDetailsRequest;

public class EditDriverDetailsRequestUtil {

    public static EditDriverDetailsRequest get(){
        return new EditDriverDetailsRequest("editedName","editLastName",30,5.4);
    }
}
