package com.klef.jfsd.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.klef.jfsd.springboot.model.Resource;
import com.klef.jfsd.springboot.service.ResourceService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000") // React frontend origin
@RequestMapping("/resources")
public class ResourceController {
    @Autowired
    private ResourceService resourceService;

    @GetMapping
    public List<Resource> getResources() {
        return resourceService.getAllResources();
    }

    @PostMapping
    public Resource addResource(@RequestBody Resource resource) {
        return resourceService.addResource(resource);
    }
    
    @DeleteMapping("/{id}")
    public String deleteResource(@PathVariable Long id) {
        resourceService.deleteResourceById(id);
        return "Resource deleted successfully";
    }
}
