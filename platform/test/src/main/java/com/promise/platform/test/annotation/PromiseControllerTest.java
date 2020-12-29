package com.promise.platform.test.annotation;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.lang.annotation.*;

/**
 * Annotation that can be used on a Promise controller test class.
 * <p>
 * It includes {@code @WebMvcTest}, because it only test controller layer (So {@code @SprintBootTest} is not used).
 * <p>
 * It includes {@code @ExtendedWith}, for JUnit5.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@ExtendWith(SpringExtension.class)
@WebMvcTest
public @interface PromiseControllerTest {

}
