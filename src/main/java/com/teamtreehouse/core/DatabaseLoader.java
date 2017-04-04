package com.teamtreehouse.core;

import com.teamtreehouse.device.Device;
import com.teamtreehouse.device.DeviceRepository;
import com.teamtreehouse.room.Room;
import com.teamtreehouse.room.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements ApplicationRunner {
    private final RoomRepository rooms;
    private final DeviceRepository devices;

    @Autowired
    public DatabaseLoader(RoomRepository rooms, DeviceRepository devices) {
        this.rooms = rooms;
        this.devices = devices;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Room room = new Room("Living Room", 51);
        Device device = new Device("LCD TV", room);
        devices.save(device);
        room.addDevice(device);
        rooms.save(room);
    }
}
