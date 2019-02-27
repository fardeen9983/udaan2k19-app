package in.ac.bvmengineering.udaan2k19.DataClass;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class Event implements Serializable {

    @SerializedName("tagline")
    private String tagLine;

    @SerializedName("_id")
    private String id;
    @SerializedName("department")
    private String dept;
    @SerializedName("eventName")
    private String name;
    @SerializedName("eventType")
    private String type;
    @SerializedName("rounds")
    private String[] rounds;
    @SerializedName("managers")
    private List<Manager> managers;

    @android.support.annotation.NonNull
    @Override
    public String toString() {
        return "Event{" +
                "id='" + id + '\'' +
                ", dept='" + dept + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", rounds=" + Arrays.toString(rounds) +
                ", managers=" + managers +
                '}';
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

    public String getType() {
        return type;
    }

    public String[] getRounds() {
        return rounds;
    }

    public List<Manager> getManagers() {
        return managers;
    }

    class Manager implements Serializable {
        @SerializedName("name")
        private String name;
        @SerializedName("phone")
        private String phone;

        public String getName() {
            return name;
        }

        public String getPhone() {
            return phone;
        }

        @android.support.annotation.NonNull
        @Override
        public String toString() {
            return "Manager{" +
                    "name='" + name + '\'' +
                    ", phone='" + phone + '\'' +
                    '}';
        }
    }

}
