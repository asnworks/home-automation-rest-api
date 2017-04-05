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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

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
        List<User> userList = Arrays.asList(
                new User("Barbara Ann Minerva", new String[] {"ROLE_USER"}, "password"),
                new User("Wade Wilson", new String[] {"ROLE_USER", "ROLE_ADMIN"}, "password"),
                new User("Floyd Lawton", new String[] {"ROLE_USER"}, "password"),
                new User("Barry Allen", new String[] {"ROLE_USER", "ROLE_ADMIN"}, "password"),
                new User("Eobard Thawn", new String[] {"ROLE_USER", "ROLE_ADMIN"}, "password"),
                new User("Thomas Wayne", new String[] {"ROLE_USER", "ROLE_ADMIN"}, "password"),
                new User("Diana Prince", new String[] {"ROLE_USER", "ROLE_ADMIN"}, "password"),
                new User("Donna Troy", new String[] {"ROLE_USER"}, "password"),
                new User("Barbara Gordon", new String[] {"ROLE_USER", "ROLE_ADMIN"}, "password"),
                new User("Steve Rogers", new String[] {"ROLE_USER", "ROLE_ADMIN"}, "password")
        );
        administrators.save(userList);

        List<Room> roomList = new ArrayList<>();
        List<Control> controlList = new ArrayList<>();
        List<Device> deviceList = new ArrayList<>();

        IntStream.range(0, 100)
                .forEach(i -> {
                    String roomName = String.format("Room %d", i + 1);
                    Room room = new Room(roomName, 1 + (int)(Math.random() * 1000));

                    String deviceName = String.format("Device %d", i + 1);
                    Device device = new Device(deviceName);

                    String controlName = String.format("Control %d", i + 1);
                    Control control = new Control(controlName);

                    controls.save(controlList);
                    device.addControl(control);
                    room.addDevice(device);
                    devices.save(deviceList);
                    roomList.add(room);
                });
        rooms.save(roomList);
    }
}
