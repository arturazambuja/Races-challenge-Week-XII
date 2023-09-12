package com.project.msraces.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "ms-cars", url = "${http://localhost:8080/v1/cars}")
public interface CarFeignClient {
}
