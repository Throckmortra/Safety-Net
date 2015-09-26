package hackdc.safetynet.API.models;

/**
 * Created by aaron on 9/26/15.
 */
public class Episodes {

    private String id;
    private String patientname;
    private String date;
    private String trigger;
    private String heartrate;
    private String location;
    private String description;

    public String getId() {
        return id;
    }

    public String getPatientname() {
        return patientname;
    }

    public String getDate() {
        return date;
    }

    public String getTrigger() {
        return trigger;
    }

    public String getHeartrate() {
        return heartrate;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }
}
