package com.teamtreehouse.device;

import com.teamtreehouse.control.Control;
import com.teamtreehouse.user.User;
import com.teamtreehouse.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
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
    public void addControlBasedOnLoggedInUser(Device device, Control control) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = users.findByName(name);
        if (user.hasAdminRole()) {
            device.addControl(control);
        } else {
            throw new AccessDeniedException("Access Denied - user must be an administrator in order to create controls");
        }
    }

    @HandleBeforeSave
    public void saveDeviceBasedOnLoggedInUser(Device device) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = users.findByName(name);
        if (user.hasAdminRole()) {
            devices.save(device);
        } else {
            throw new AccessDeniedException("Access Denied - user must be an administrator in order to modify devices");
        }
    }
}
