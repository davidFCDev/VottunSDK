package com.vottun.SDK.Test.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeployERC20Request {
    private String name;
    private String symbol;
    private String alias;
    private BigInteger initialSupply;
    private int network;
    private int gasLimit;
}
