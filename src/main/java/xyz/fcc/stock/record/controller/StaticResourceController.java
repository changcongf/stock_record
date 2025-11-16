package xyz.fcc.stock.record.controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StaticResourceController {
    private final ResourceLoader resourceLoader;

    public StaticResourceController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @GetMapping("/company")
    public ResponseEntity<Resource> serveCompanyStaticResource() {
        try {
            Resource resource = resourceLoader.getResource("classpath:/static/company.html" );
            if (resource.exists()) {
                return ResponseEntity.ok().body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/day")
    public ResponseEntity<Resource> serveDayStaticResource() {
        try {
            Resource resource = resourceLoader.getResource("classpath:/static/day.html" );
            if (resource.exists()) {
                return ResponseEntity.ok().body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/industry")
    public ResponseEntity<Resource> serveIndustryStaticResource() {
        try {
            Resource resource = resourceLoader.getResource("classpath:/static/industry.html" );
            if (resource.exists()) {
                return ResponseEntity.ok().body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/product")
    public ResponseEntity<Resource> serveProductStaticResource() {
        try {
            Resource resource = resourceLoader.getResource("classpath:/static/product.html" );
            if (resource.exists()) {
                return ResponseEntity.ok().body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}