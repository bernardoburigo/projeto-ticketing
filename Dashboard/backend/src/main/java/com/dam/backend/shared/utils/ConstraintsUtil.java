package com.dam.backend.shared.utils;

import com.dam.backend.shared.exceptions.ClasseNaoInstanciavelException;
import com.dam.backend.shared.exceptions.ModelException;
import java.util.Objects;
import java.util.function.Supplier;

public final class ConstraintsUtil {

    private ConstraintsUtil() {
        throw new ClasseNaoInstanciavelException();
    }

    public static <T> T notSupportedOperation() {
        return notSupportedOperation("Not Supported Operation");
    }

    public static <T> T notSupportedOperation(String message) {
        throw new ModelException(message);
    }

    public static <T> T requireNonNull(T value, String message) {
        return requireNonNull(value, () -> new ModelException(message));
    }

    public static <T, X extends Throwable> T requireNonNull(T value, Supplier<? extends X> exceptionSupplier) throws X {
        if (value != null) {
            return value;
        } else {
            throw exceptionSupplier.get();
        }
    }

    public static <X extends Throwable> boolean eq(Object value, Object other, String messageIfNotEq) throws X {
        return eq(value, other, () -> new ModelException(messageIfNotEq));
    }

    public static <X extends Throwable> boolean eq(Object value, Object other, Supplier<? extends X> exceptionSupplier) throws X {
        if (!Objects.equals(value, other)) {
            throw exceptionSupplier.get();
        }
        return true;
    }

    public static <X extends Throwable> boolean ne(Object value, Object other, String messageIfNotEq) throws X {
        return ne(value, other, () -> new ModelException(messageIfNotEq));
    }

    public static <X extends Throwable> boolean ne(Object value, Object other, Supplier<? extends X> exceptionSupplier) throws X {
        if (Objects.equals(value, other)) {
            throw exceptionSupplier.get();
        }
        return true;
    }

    public static <X extends Throwable> boolean mustBeTrue(boolean value, String messageIfFalse) throws X {
        return mustBeTrue(value, () -> new ModelException(messageIfFalse));
    }

    public static <X extends Throwable> boolean mustBeTrue(boolean value, Supplier<? extends X> exceptionSupplier) throws X {
        if (!value) {
            throw exceptionSupplier.get();
        }
        return true;
    }

    public static <X extends Throwable> void mustBeFalse(boolean value, String messageIfTrue) throws X {
        mustBeFalse(value, () -> new ModelException(messageIfTrue));
    }

    public static <X extends Throwable> void mustBeFalse(boolean value, Supplier<? extends X> exceptionSupplier) throws X {
        if (value) {
            throw exceptionSupplier.get();
        }
    }
}
