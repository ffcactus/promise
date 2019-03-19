package com.promise.apps.example;

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
public @interface Taskstep
{

}
