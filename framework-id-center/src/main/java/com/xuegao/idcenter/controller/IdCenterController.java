package com.xuegao.idcenter.controller;

import com.xuegao.idcenter.XuegaoIdGeneratorLoader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IdCenterController {

    @RequestMapping("/idCenter/nextId")
    public String nextId() {
        return XuegaoIdGeneratorLoader.getIdGenerator().nextId();
    }

}
