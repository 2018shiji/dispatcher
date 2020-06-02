package com.module.dispatcher;

import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
@Scope("prototype")
public class Task {

    @Setter
    private String taskName;
    @Setter
    private String content;
    private static AtomicInteger serialNum;

    public void taskLogic() {

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("task is processing" + "   " + content == null ? "null" : content);
        System.out.println(taskName+ "  " + serialNum.incrementAndGet());
    }

}
