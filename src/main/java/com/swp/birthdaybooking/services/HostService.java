package com.swp.birthdaybooking.services;

import com.swp.birthdaybooking.Dtos.Request.HostRq;
import com.swp.birthdaybooking.entities.Host;
import com.swp.birthdaybooking.repositories.HostRepository;
import org.springframework.stereotype.Service;

@Service
public class HostService {

    private final HostRepository hostRepository;

    public HostService(HostRepository hostRepository) {
        this.hostRepository = hostRepository;
    }

    public Host editProfile(HostRq hostRq) {
        var host = hostRepository.findById(hostRq.getHostId()).orElseThrow();
        host.setPhone(hostRq.getPhone());
        host.setName(hostRq.getName());
        return hostRepository.save(host);
    }

}