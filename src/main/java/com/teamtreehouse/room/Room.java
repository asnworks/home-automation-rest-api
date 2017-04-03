package com.teamtreehouse.room;

import com.teamtreehouse.device.Device;
import com.teamtreehouse.user.User;

import javax.persistence.*;
import java.util.List;

@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private final Long id;
    private String name;
    private int area;
    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<Device> devices;
    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<User> administrators;

    protected Room() {
        id = null;
    }

    public Room(String name, int area, List<Device> devices, List<User> administrators) {
        this();
        this.name = name;
        this.area = area;
        this.devices = devices;
        this.administrators = administrators;
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
