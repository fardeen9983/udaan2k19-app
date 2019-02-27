package in.ac.bvmengineering.udaan2k19.DataClass;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Manager implements Serializable {
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