package com.teamtreehouse.core;

import com.teamtreehouse.control.Control;
import com.teamtreehouse.control.ControlRepository;
import com.teamtreehouse.device.Device;
import com.teamtreehouse.device.DeviceRepository;
import com.teamtreehouse.room.Room;
import com.teamtreehouse.room.RoomRepository;
import com.teamtreehouse.user.User;
import com.teamtreehouse.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements ApplicationRunner {
    private final RoomRepository rooms;
    private final DeviceRepository devices;
    private final ControlRepository controls;
    private final UserRepository administrators;

    @Autowired
    public DatabaseLoader(RoomRepository rooms, DeviceRepository devices,
                          ControlRepository controls, UserRepository administrators) {
        this.rooms = rooms;
        this.devices = devices;
        this.controls = controls;
        this.administrators = administrators;

    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Room room1 = new Room("Living Room", 251);
        Device device1 = new Device("RGB LED Lights", room1);
        devices.save(device1);
        room1.addDevice(device1);
        User user1 = new User("Diana Prince", new String[] {"ROLE_USER", "ROLE_ADMIN"}, "password");
        administrators.save(user1);
        Control control1 = new Control("Power On", device1, 1, user1);
        controls.save(control1);
        rooms.save(room1);

        Room room2 = new Room("Dining Room", 234);
        Device device2 = new Device("Window Shades", room2);
        devices.save(device2);
        room2.addDevice(device2);
        User user2 = new User("Wade Wilson", new String[] {"ROLE_USER", "ROLE_ADMIN"}, "password");
        administrators.save(user2);
        Control control2 = new Control("Shades Up", device2, 1, user2);
        controls.save(control2);
        rooms.save(room2);

        Room room3 = new Room("Kitchen", 121);
        Device device3 = new Device("Coffee Machine", room3);
        devices.save(device3);
        room1.addDevice(device3);
        User user3 = new User("Floyd Lawton", new String[] {"ROLE_USER"}, "password");
        administrators.save(user3);
        Control control3 = new Control("Brew Coffee", device3, 1, user3);
        controls.save(control3);
        rooms.save(room3);

        Room room4 = new Room("Game Room", 151);
        Device device4 = new Device("LCD TV", room4);
        devices.save(device4);
        room1.addDevice(device4);
        User user4 = new User("Barry Allen", new String[] {"ROLE_USER", "ROLE_ADMIN"}, "password");
        administrators.save(user4);
        Control control4 = new Control("Power Off", device4, 1, user4);
        controls.save(control4);
        rooms.save(room4);

        Room room5 = new Room("Study", 142);
        Device device5 = new Device("Smart Thermostat", room5);
        devices.save(device5);
        room1.addDevice(device5);
        User user5 = new User("Barbara Ann Minerva", new String[] {"ROLE_USER"}, "password");
        administrators.save(user5);
        Control control5 = new Control("Power Off", device5, 1, user5);
        controls.save(control5);
        rooms.save(room5);
    }
}
