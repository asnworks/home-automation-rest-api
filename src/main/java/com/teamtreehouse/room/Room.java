package com.teamtreehouse.room;

import com.teamtreehouse.core.BaseEntity;
import com.teamtreehouse.device.Device;
import com.teamtreehouse.user.User;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Entity
public class Room extends BaseEntity {
    @NotNull
    private String name;
    @NotNull
    @Max(1000)
    private int area;
    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<Device> devices;
    @ManyToMany(cascade = CascadeType.MERGE)
    private List<User> administrators;

    protected Room() {
        super();
        devices = new ArrayList<>();
        administrators = new ArrayList<>();
    }

    public Room(String name, int area) {
        this();
        this.name = name;
        this.area = area;
    }

    public void addDevice(Device device) {
        device.setRoom(this);
        devices.add(device);
    }

    public void addAdministrator(User user) {
        administrators.add(user);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public List<User> getAdministrators() {
        return administrators;
    }

    public void setAdministrators(List<User> administrators) {
        this.administrators = administrators;
    }
}
