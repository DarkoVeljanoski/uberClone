package project.uberclone.utils;

import project.uberclone.model.request.RegisterDriverRequest;

public class RegisterDriverRequestUtil {
    public static RegisterDriverRequest get(){
        return new RegisterDriverRequest("driver@mail.com","password","name","lastname",
                30,5.5);
    }
}
