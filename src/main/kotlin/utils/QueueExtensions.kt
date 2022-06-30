package utils

import java.util.concurrent.BlockingQueue
import java.util.concurrent.TimeUnit

object QueueExtensions {

    fun<T> BlockingQueue<T>.takeIn(): T? {
        return this.poll(10L, TimeUnit.MINUTES)
    }
}
