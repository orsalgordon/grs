package com.grs.service;

import com.grs.client.AuthClient;
import com.grs.exception.InternalServerException;
import com.grs.exception.NotFoundException;
import com.grs.model.Host;
import com.grs.model.dto.HostDto;
import com.grs.model.dto.LoginRequestDto;
import com.grs.model.dto.LoginResponseDto;
import com.grs.repository.HostRepository;
import com.grs.util.DTOMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class HostService {

    private HostRepository hostRepository;
    private PasswordEncoder passwordEncoder;
    private AuthClient authClient;

    public List<HostDto> getALlHosts() {
        List<Host> hosts = hostRepository.findAll();
        return hosts.stream()
                .map(HostDto::of)
                .toList();
    }

    public HostDto getHostById(Integer id) {
        Optional<Host> host = hostRepository.findById(id.longValue());
        return host.map(HostDto::of).orElseThrow(() -> new NotFoundException(String.format("Host with id %s not found", id)));
    }

    public HostDto updateHost(Integer id, HostDto hostDto) {
        Optional<Host> existingHost = hostRepository.findById(id.longValue());
        if (existingHost.isPresent()) {
            Host host = DTOMapper.map(hostDto);
            Host response = hostRepository.save(host);
            return Optional.of(response)
                    .map(HostDto::of)
                    .orElseThrow(() -> new InternalServerException("There's an error saving the host details"));
        }
        throw new NotFoundException(String.format("Host with id %s not found", id));
    }

    public void deleteHost(Integer id) {
        try {
            hostRepository.deleteById(id.longValue());
        } catch (Exception e) {
            throw new InternalServerException("There's an error deleting the host details for id " + id);
        }
    }

    public HostDto signup(HostDto request) {
        return authClient.callAuthSignup(request);
    }

    public LoginResponseDto login(LoginRequestDto request) {
        return authClient.callAuthLogin(request);
    }
}
