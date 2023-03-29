package com.univesp.game.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/test")
class CustomerController {

    @GetMapping
    fun test(): String {
        return "Board Games - It's all good!"
    }
}
