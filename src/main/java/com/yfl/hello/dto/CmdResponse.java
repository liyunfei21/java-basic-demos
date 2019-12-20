package com.yfl.hello.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CmdResponse {

    private int code;

    private List<String> result;
}
