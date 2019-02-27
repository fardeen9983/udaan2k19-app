package in.ac.bvmengineering.udaan2k19.DataClass;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Event {

//    public static int TYPE_NON_TECH = 0;
//    public static int TYPE_TECH = 1;
//    public static int DEPT_ECEL = 0;

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

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String[] getRounds() {
        return rounds;
    }

    public void setRounds(String[] rounds) {
        this.rounds = rounds;
    }

    public List<Manager> getManagers() {
        return managers;
    }

    public void setManagers(List<Manager> managers) {
        this.managers = managers;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    class Manager {
        @SerializedName("name")
        private String name;
        @SerializedName("phone")
        private String phone;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }

}
