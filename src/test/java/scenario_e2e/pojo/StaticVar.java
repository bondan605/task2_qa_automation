package scenario_e2e.pojo;

import model.ObjectModel;
import model.UserModel;

public class StaticVar {

    public final static String BASE_URL = "https://whitesmokehouse.com/webhook";
    public static String tokenLogin;
    public static int objectId;

    public static UserModel user;


    public static ObjectModel object;
    public static ObjectModel.DataDetail objectDataDetail;

}