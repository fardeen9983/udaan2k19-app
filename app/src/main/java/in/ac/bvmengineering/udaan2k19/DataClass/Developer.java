package in.ac.bvmengineering.udaan2k19.DataClass;

import com.google.gson.annotations.SerializedName;

public class Developer {
    @SerializedName("name")
    private String name;
    private String type;
    @SerializedName("mail")
    private String mail;
    @SerializedName("github")
    private String github;
    @SerializedName("phone")
    private String phone;
    private int imageID;

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMail() {
        return mail;
    }

    public String getGithub() {
        return github;
    }

    public String getPhone() {
        return phone;
    }
}
