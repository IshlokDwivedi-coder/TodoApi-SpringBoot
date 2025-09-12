package com.example.todoapispring;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service("FakeTodoService")
public class FakeTodoService implements TodoService{

    public String dosmth(){
        return "Something";
    }

    @Override
    public String doSomething() {
        return "";
    }
}
