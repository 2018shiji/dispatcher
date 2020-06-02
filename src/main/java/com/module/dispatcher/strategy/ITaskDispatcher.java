package com.module.dispatcher.strategy;

import com.module.dispatcher.Task;
import com.module.dispatcher.handler.ITaskHandler;
import org.springframework.stereotype.Component;

/**
 * 负责任务的分发与处理
 */
@Component
public interface ITaskDispatcher extends TaskCallBack {

    void dispatchTask(Task task);
    ITaskHandler getTaskHandler();
    void handleTask();
}
