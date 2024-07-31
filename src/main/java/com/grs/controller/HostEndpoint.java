package com.grs.controller;

import com.grs.model.dto.HostDto;
import com.grs.model.dto.LoginRequestDto;
import com.grs.model.dto.LoginResponseDto;
import com.grs.service.HostService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("/rest/v1/host")
@Tag(name = "Host")
public class HostEndpoint {

    @Autowired
    private HostService hostService;

    @GetMapping
    public ResponseEntity<List<HostDto>> getAllHosts(){
        return ResponseEntity.ok(hostService.getALlHosts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HostDto> getHostById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(hostService.getHostById(id));
    }

    @PostMapping
    public ResponseEntity<HostDto> createHost(@RequestBody HostDto hostDto){
        return ResponseEntity.ok(hostService.createHost(hostDto));
    }

    @PatchMapping("{id}")
    public ResponseEntity<HostDto> updateHost(@PathVariable("id") Integer id, @RequestBody HostDto hostDto){
        return ResponseEntity.ok(hostService.updateHost(id, hostDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHost(@PathVariable("id") Integer id){
        hostService.deleteHost(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto request){
        return ResponseEntity.ok(hostService.login(request));
    }
}
