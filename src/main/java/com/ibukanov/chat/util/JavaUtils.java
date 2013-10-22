package com.ibukanov.chat.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static org.apache.commons.lang3.reflect.ConstructorUtils.invokeConstructor;
import static org.springframework.util.ReflectionUtils.rethrowRuntimeException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JavaUtils {

    public static<T> T invokeConstructorQuietly(Class<T> clazz) {
          try {
              return invokeConstructor(clazz);
          }
          catch (Exception e) {
              rethrowRuntimeException(e);
              return null;
          }
    }
}
