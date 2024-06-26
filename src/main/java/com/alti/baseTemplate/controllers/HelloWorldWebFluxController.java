package com.alti.baseTemplate.controllers;

import com.alti.baseTemplate.model.MerchantDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("hello")
public class HelloWorldWebFluxController {

    @GetMapping(value = "merchant" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MerchantDTO> getMerchant(String id) {
        Map<String, MerchantDTO> merchMap = populateMerchants();
        return new ResponseEntity<>(merchMap.get(id), HttpStatus.OK);
    }

    @GetMapping("integerFlux")
    public ResponseEntity<Flux<Integer>> getIntFlux() {

        return new ResponseEntity<>(Flux.just(1,2,3), HttpStatus.OK);
    }

    @GetMapping(value = "mono",  produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<String> getMono() {
        return Mono.just("Hey i m ur first Mono");
    }

    private Map<String, MerchantDTO> populateMerchants() {
        Map<String, MerchantDTO> merchMap = new HashMap<>();
        merchMap.put("1", new MerchantDTO("E001","Avesh"));
        merchMap.put("2", new MerchantDTO("E002","Brajesh"));
        merchMap.put("3", new MerchantDTO("E003","Devesh"));
        merchMap.put("4", new MerchantDTO("E004","Paresh"));
        return merchMap;
    }

}
