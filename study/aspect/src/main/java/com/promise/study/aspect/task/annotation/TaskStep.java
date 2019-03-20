package com.promise.study.aspect.task.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicate that this method represents a task step.
 *
 */
@Target({
        ElementType.METHOD
})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TaskStep
{
    /**
     * The weight of the task step. For example, if a task has 2 task steps and the
     * weight of the first task is twice of the second one, then after the first one
     * is done, the percentage of task should be 66.6%.
     *
     * @return
     */
    int weight() default 1;
}
