package com.teamtreehouse.device;

import com.teamtreehouse.control.Control;
import com.teamtreehouse.room.Room;
import com.teamtreehouse.user.User;
import com.teamtreehouse.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeDelete;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler(Device.class)
public class DeviceEventHandler {
    private final UserRepository users;
    private final DeviceRepository devices;

    @Autowired
    public DeviceEventHandler(UserRepository users, DeviceRepository devices) {
        this.users = users;
        this.devices = devices;
    }

    @HandleBeforeCreate
    public void addControlBasedOnLoggedInUser(Device device, Control control, Room room) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = users.findByName(name);
        if (room.getAdministrators().contains(user)) {
            device.addControl(control);
        } else {
            throw new AccessDeniedException("Access Denied - user must be a room administrator in order to create controls");
        }
    }

    @HandleBeforeSave
    public void saveDeviceBasedOnLoggedInUser(Device device, Room room) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = users.findByName(name);
        if (room.getAdministrators().contains(user)) {
            devices.save(device);
        } else {
            throw new AccessDeniedException("Access Denied - user must be a room administrator in order to modify devices");
        }
    }

    @HandleBeforeDelete
    public void deleteDeviceBasedOnLoggedInUser(Device device, Room room) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = users.findByName(name);
        if (room.getAdministrators().contains(user)) {
            devices.delete(device);
        } else {
            throw new AccessDeniedException("Access Denied - user must be a room administrator in order to delete devices");
        }
    }
}
