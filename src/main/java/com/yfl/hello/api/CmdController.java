package com.yfl.hello.api;

import com.yfl.hello.dto.CmdResponse;
import com.yfl.hello.utils.CmdToolkit;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cmd")
public class CmdController {

    private final CmdToolkit cmdToolkit = new CmdToolkit();

    /**
     *
     * @param cmd git
     * @param command --help
     * @return
     */
    @GetMapping("{cmd}")
    public CmdResponse cmd(@PathVariable("cmd")String cmd, @RequestParam("command") String command){
        return cmdToolkit.readConsole(cmd, command);
    }


}
