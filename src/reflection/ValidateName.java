package reflection;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)//visibility
@Target({ElementType.FIELD, ElementType.TYPE, ElementType.PARAMETER})//position for annotation
public @interface ValidateName {
}
