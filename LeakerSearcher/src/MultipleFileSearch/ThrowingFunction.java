package MultipleFileSearch;

/*
    This interface was took from StackOverflow.
    It is used as wrapper for streams that throws an exception of T.
    For example in the line 90 of ProcessSingleFile class:
    .flatMap(ThrowingFunction.wrap(x -> Files.lines(x,StandardCharsets.ISO_8859_1)))
    Files.lines(...) throws an IOException, so to make this trowable in flatmap you can use the following redefinition of the functional interface
 */

import java.util.function.Function;

@FunctionalInterface
public interface ThrowingFunction<T,R> extends Function<T,R> {

    @Override
    public default R apply(T t) {
        try {
            return throwingApply(t);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static<T,R> Function<T,R> wrap(ThrowingFunction<T,R> f) {
        return f;
    }

    R throwingApply(T t) throws Exception;
}
