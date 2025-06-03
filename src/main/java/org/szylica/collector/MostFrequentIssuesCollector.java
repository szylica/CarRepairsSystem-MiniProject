package collector;

import model.IssueType;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class MostFrequentIssuesCollector implements Collector<IssueType, Map<IssueType, Integer>, Set<IssueType>> {


    @Override
    public Supplier<Map<IssueType, Integer>> supplier() {
        return () -> new HashMap<>();
    }

    @Override
    public BiConsumer<Map<IssueType, Integer>, IssueType> accumulator() {
        return (map, issue) -> {
            map.merge(issue, 1, Integer::sum);
        };
    }

    @Override
    public BinaryOperator<Map<IssueType, Integer>> combiner() {
        return (map1, map2) -> {
            map2.forEach((k, v) -> map1.merge(k, v, Integer::sum));
            return map1;
        };
    }

    @Override
    public Function<Map<IssueType, Integer>, Set<IssueType>> finisher() {
        return a -> a
                .entrySet()
                .stream()
                .collect(groupingBy(
                        entry -> entry.getValue(),
                        mapping(Map.Entry::getKey, toSet())
                ))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByKey())
                .map(entry -> entry.getValue())
                .orElseGet(Collections::emptySet);
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Set.of(Characteristics.UNORDERED);
    }
}
