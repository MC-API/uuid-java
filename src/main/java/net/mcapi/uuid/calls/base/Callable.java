package net.mcapi.uuid.calls.base;

import java.util.concurrent.Future;

import net.mcapi.uuid.UUIDAPI;

/**
 * An async callable
 *
 * @param <T> type to call and return
 */
public abstract class Callable<T> implements java.util.concurrent.Callable<T> {

    private Future<T> run() {
        return UUIDAPI.getExecutor().submit(this);
    }

    public T execute() throws Exception {
        return run().get();
    }

}
