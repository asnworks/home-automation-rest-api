package com.teamtreehouse.control;

import com.teamtreehouse.device.Device;
import com.teamtreehouse.user.User;

import javax.persistence.Entity;

@Entity
public class Control {
    private String name;
    private Device device;
    private int value;
    private User lastModifiedBy;

    protected Control() {
    }

    public Control(String name, Device device, int value, User lastModifiedBy) {
        this.name = name;
        this.device = device;
        this.value = value;
        this.lastModifiedBy = lastModifiedBy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public User getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(User lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }
}
