package com.module.dispatcher.handler;

import com.module.dispatcher.Task;
import com.module.dispatcher.strategy.TaskCallBack;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public interface ITaskHandler {
    void handleTask(TaskCallBack callBack, Task task);
}
