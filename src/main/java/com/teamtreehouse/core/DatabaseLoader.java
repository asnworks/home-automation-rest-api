package com.teamtreehouse.core;

import com.teamtreehouse.device.Device;
import com.teamtreehouse.room.Room;
import com.teamtreehouse.room.RoomRepository;
import com.teamtreehouse.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DatabaseLoader implements ApplicationRunner {
    private final RoomRepository rooms;

    @Autowired
    public DatabaseLoader(RoomRepository rooms) {
        this.rooms = rooms;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<Device> devices = new ArrayList<>();
        List<User> administrators = new ArrayList<>();
        Room room = new Room("Living Room", 51, devices, administrators);
        rooms.save(room);
    }
}
