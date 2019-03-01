package in.ac.bvmengineering.udaan2k19.DataClass;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class Event implements Serializable {

    @SerializedName("tagline")
    private String tagLine;
    @SerializedName("entryFee")
    private int fees;
    @SerializedName("notes")
    private String notes;
    @SerializedName("_id")
    private String id;
    @SerializedName("department")
    private String dept;
    @SerializedName("eventName")
    private String name;
    @SerializedName("eventType")
    private String type;
    @SerializedName("rounds")
    private ArrayList<String> rounds;
    @SerializedName("managers")
    private ArrayList<Manager> managers;
    @SerializedName("teamSize")
    private int teamSize;

    public int getTeamSize() {
        return teamSize;
    }

    public String getNotes() {
        return notes;
    }
    public int getFees() {
        return fees;
    }

    public String getTagLine() {
        return tagLine;
    }

    public String getId() {
        return id;
    }

    public String getDept() {
        return dept;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getRounds() {
        return rounds;
    }

    public String getType() {
        return type;
    }

    @NonNull
    @Override
    public String toString() {
        return "Event{" +
                "tagLine='" + tagLine + '\'' +
                ", fees=" + fees +
                ", id='" + id + '\'' +
                ", dept='" + dept + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", rounds=" + rounds +
                ", managers=" + managers +
                '}';
    }

    public ArrayList<Manager> getManagers() {
        return managers;
    }

}
