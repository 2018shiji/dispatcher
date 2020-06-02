package com.module.dispatcher.handler;

import com.module.dispatcher.Task;
import com.module.dispatcher.strategy.TaskCallBack;
import org.springframework.stereotype.Component;

@Component("oralHandler")
public class OralHandler implements ITaskHandler {

    @Override
    public void handleTask(TaskCallBack callBack, Task task) {
        System.out.println("oralHandler for task");
        task.taskLogic();
        callBack.processed(true);
    }

}
