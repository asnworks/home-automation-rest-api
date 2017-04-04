package com.teamtreehouse.room;

import com.teamtreehouse.core.BaseEntity;
import com.teamtreehouse.device.Device;
import com.teamtreehouse.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Room extends BaseEntity {
    private String name;
    private int area;
    @OneToMany(cascade = CascadeType.MERGE)
    private List<Device> devices;
    @ManyToMany(cascade = CascadeType.MERGE)
    private List<User> administrators;

    protected Room() {
        super();
        devices = new ArrayList<>();
        administrators = new ArrayList<>();
    }

    public Room(String name, int area, List<Device> devices, List<User> administrators) {
        this();
        this.name = name;
        this.area = area;
        this.devices = devices;
        this.administrators = administrators;
    }

    public void addDevice(Device device) {
        device.setRoom(this);
        devices.add(device);
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

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    public List<User> getAdministrators() {
        return administrators;
    }

    public void setAdministrators(List<User> administrators) {
        this.administrators = administrators;
    }
}
