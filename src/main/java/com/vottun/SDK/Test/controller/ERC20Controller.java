package com.vottun.SDK.Test.controller;

import com.vottun.SDK.Test.model.DeployERC20Request;
import com.vottun.SDK.Test.model.GetNameRequest;
import com.vottun.SDK.Test.service.ERC20Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/erc20")
public class ERC20Controller {

    @Autowired
    private ERC20Service erc20Service;

    @PostMapping("/deploy")
    public ResponseEntity<?> deployToken(@RequestBody DeployERC20Request tokenRequest) {
        try {
            Object deployResponse = erc20Service.deployToken(tokenRequest);
            return ResponseEntity.ok(deployResponse);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deploying token: " + e.getMessage());
        }
    }

    @PostMapping("/getTokenName")
    public ResponseEntity<?> getToken(@RequestBody GetNameRequest getNameRequest) {
        try {
            Object tokenResponse = erc20Service.getName(getNameRequest);
            return ResponseEntity.ok(tokenResponse);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error getting token: " + e.getMessage());
        }
    }
}
