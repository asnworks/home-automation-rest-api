package com.teamtreehouse.device;

import com.teamtreehouse.control.Control;
import com.teamtreehouse.core.BaseEntity;
import com.teamtreehouse.room.Room;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Device extends BaseEntity {
    private String name;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Room room;
    @OneToMany(cascade = CascadeType.MERGE)
    private List<Control> controls;

    protected Device() {
        super();
        controls = new ArrayList<>();
    }

    public Device(String name, Room room, List<Control> controls) {
        this();
        this.name = name;
        this.room = room;
        this.controls = controls;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public List<Control> getControls() {
        return controls;
    }

    public void setControls(List<Control> controls) {
        this.controls = controls;
    }
}
