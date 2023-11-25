package nuricanozturk.dev.service.read.util;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@SuppressWarnings("unchecked")
public final class StreamUtil
{
    private StreamUtil()
    {

    }

    public static <T, R> Stream<R> toStream(Iterable<T> iterable, Function<? super T, ? extends R> mapper)
    {
        return StreamSupport.stream(iterable.spliterator(), false).map(mapper);
    }

    /**
     * Convert iterable to stream concurrent.
     *
     * @param iterable represent the iterable.
     * @param mapper   represent the mapper.
     * @param <T>      represent the type of iterable.
     * @param <R>      represent the type of stream.
     * @return Stream.
     */
    public static <T, R> Stream<R> toStreamConcurrent(Iterable<T> iterable, Function<? super T, ? extends R> mapper)
    {
        return StreamSupport.stream(iterable.spliterator(), true).map(mapper);
    }

    /**
     * Convert iterable to stream concurrent.
     *
     * @param iterable represent the iterable.
     * @param <T>      represent the type of iterable.
     * @return Stream.
     */
    public static <T> Stream<T> toStream(Iterable<T> iterable)
    {
        return StreamSupport.stream(iterable.spliterator(), false);
    }

    /**
     * Convert iterable to stream concurrent.
     *
     * @param iterable represent the iterable.
     * @param <T>      represent the type of iterable.
     * @return Stream.
     */
    public static <T> Stream<T> toStreamConcurrent(Iterable<T> iterable)
    {
        return StreamSupport.stream(iterable.spliterator(), true);
    }

    /**
     * Convert iterable to list.
     *
     * @param iterable represent the iterable.
     * @param mapper   represent the mapper.
     * @param <T>      represent the type of iterable.
     * @param <R>      represent the type of list.
     * @return List.
     */
    public static <T, R> List<R> toList(Iterable<T> iterable, Function<? super T, ? extends R> mapper)
    {
        return (List<R>) StreamSupport.stream(iterable.spliterator(), false).map(mapper).toList();
    }

    /**
     * Convert iterable to list concurrent.
     *
     * @param iterable represent the iterable.
     * @param mapper   represent the mapper.
     * @param <T>      represent the type of iterable.
     * @param <R>      represent the type of list.
     * @return List.
     */
    public static <T, R> List<R> toListConcurrent(Iterable<T> iterable, Function<? super T, ? extends R> mapper)
    {
        return (List<R>) StreamSupport.stream(iterable.spliterator(), true).map(mapper).toList();
    }

    /**
     * Convert iterable to list concurrent.
     *
     * @param iterable represent the iterable.
     * @param <T>      represent the type of iterable.
     * @return List.
     */
    public static <T, R> List<R> toListConcurrent(Iterable<T> iterable)
    {
        return (List<R>) StreamSupport.stream(iterable.spliterator(), true).toList();
    }

    /**
     * Convert iterable to list.
     *
     * @param iterable represent the iterable.
     * @param <T>      represent the type of iterable.
     * @return List.
     */
    public static <T, R> List<R> toList(Iterable<T> iterable)
    {
        return (List<R>) StreamSupport.stream(iterable.spliterator(), false).toList();
    }
}
