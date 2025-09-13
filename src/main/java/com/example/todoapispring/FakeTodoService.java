package com.example.todoapispring;


import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service("FakeTodoService")
public class FakeTodoService implements TodoService{



    @TimeMonitor
    public String doSomething(){
        for(long i = 0; i < 10000000000L; i++) {}//join point
        return "Something";
    }

    }
