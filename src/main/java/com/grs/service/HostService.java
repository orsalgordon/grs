package com.grs.service;

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

    public HostDto createHost(HostDto hostDto) {
        Host host = DTOMapper.map(hostDto);
        host.setPassword(passwordEncoder.encode(hostDto.getPassword()));
        Host response = hostRepository.save(host);
        return Optional.of(response)
                .map(HostDto::of)
                .orElseThrow(() -> new InternalServerException("There's an error saving the host details"));
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

    public LoginResponseDto login(LoginRequestDto request) {
        String email = request.getEmail();
        String password = request.getPassword();

        try {
            Host host = hostRepository.findByEmail(email);
            if (host != null) {
                boolean isSame = passwordEncoder.matches(password, host.getPassword());
                if (isSame) {
                    return LoginResponseDto.builder()
                            .message(String.valueOf(host.getHostId()))
                            .status(true)
                            .build();
                }
            }
            return LoginResponseDto.builder()
                    .message("Invalid email or password")
                    .status(false)
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
