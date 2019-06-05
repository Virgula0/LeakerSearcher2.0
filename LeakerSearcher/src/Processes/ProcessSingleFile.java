package Processes;

import MultipleFileSearch.ThrowingFunction;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.*;
import java.nio.file.Files;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ProcessSingleFile {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    private static List<String> colors = new ArrayList<>(Arrays.asList(ANSI_RED, ANSI_GREEN, ANSI_YELLOW, ANSI_BLUE, ANSI_PURPLE, ANSI_CYAN, ANSI_WHITE));
    private static Random rand = new Random();

    private String File_Path;
    private static Set<String> check = Collections.emptySet();

    public ProcessSingleFile() {
    }

    public ProcessSingleFile(String file_Path) {
        this.File_Path = file_Path;
    }

    public Set<String> searchInNumberedFiles(String toMatch, boolean noVerbose, int upbound, String ext, int lowbound) throws IOException {
        check = noVerbose ?
                IntStream.range(lowbound, upbound)
                        .parallel()
                        .mapToObj(Objects::toString)
                        .map(x -> this.File_Path + x + "." + ext)
                        .map(Paths::get)
                        .flatMap(ThrowingFunction.wrap(x -> Files.lines(x, StandardCharsets.ISO_8859_1)))
                        .filter(x -> x.contains(toMatch))
                        .collect(Collectors.toSet())
                :
                IntStream.range(lowbound, upbound)
                        .parallel()
                        .mapToObj(Objects::toString)
                        .map(x -> this.File_Path + x + "." + ext)
                        .map(Paths::get)
                        .flatMap(ThrowingFunction.wrap(x -> Files.lines(x, StandardCharsets.ISO_8859_1)))
                        .filter(x -> x.contains(toMatch))
                        .peek(x -> System.out.println(colors.get(rand.nextInt(colors.size()-1)) + "Found something till now! _> " + x))
                        .collect(Collectors.toSet());

        return check;
    }

    public Set<String> searchInAFile(String toMatch, boolean noVerbose) throws IOException {
        //We use Set beacuse we don't care if the result it's ordered or not.
        check = noVerbose ?
                Files.lines(Paths.get(this.File_Path), StandardCharsets.ISO_8859_1)
                        .parallel()
                        .unordered()
                        .filter(x -> x.contains(toMatch))
                        .collect(Collectors.toSet())
                :
                Files.lines(Paths.get(this.File_Path), StandardCharsets.ISO_8859_1)
                        .parallel()
                        .unordered()
                        .filter(x -> x.contains(toMatch))
                        .peek(x -> System.out.println(colors.get(rand.nextInt(colors.size()-1)) + "Found something till now! _> " + x))
                        //.distinct() // Distinct is redundant, a Set cannot contain duplicates
                        //.flatMap(s-> Arrays.stream(s.split("\\PL+"))) //Useless operation
                        .collect(Collectors.toSet());
        return check;
    }

    public Set<String> searchNoRecursive(String toMatch, boolean noVerbose, String ext) throws IOException {
        check = noVerbose ?
                Files.list(Paths.get(this.File_Path))
                        .parallel()
                        .unordered()
                        .map(Objects::toString)
                        .filter(x -> x.endsWith("." + ext))
                        .map(Paths::get)
                        .flatMap(ThrowingFunction.wrap(x -> Files.lines(x, StandardCharsets.ISO_8859_1)))
                        .filter(x -> x.contains(toMatch))
                        .collect(Collectors.toSet())
                :
                Files.list(Paths.get(this.File_Path))
                        .parallel()
                        .unordered()
                        .map(Objects::toString)
                        .filter(x -> x.endsWith("." + ext))
                        .map(Paths::get)
                        .flatMap(ThrowingFunction.wrap(x -> Files.lines(x, StandardCharsets.ISO_8859_1)))
                        .filter(x -> x.contains(toMatch))
                        .peek(x -> System.out.println(colors.get(rand.nextInt(colors.size()-1)) + "Found something till now! _> " + x))
                        .collect(Collectors.toSet());
        return check;
    }

    public Set<String> searchRecursive(String toMatch, boolean noVerbose, String ext) throws IOException {
        check = noVerbose ?
                Files.walk(Paths.get(this.File_Path))
                        .parallel()
                        .unordered()
                        .map(Objects::toString)
                        .filter(x -> x.endsWith("." + ext))
                        .map(Paths::get)
                        .flatMap(ThrowingFunction.wrap(x -> Files.lines(x, StandardCharsets.ISO_8859_1)))
                        .filter(x -> x.contains(toMatch))
                        .collect(Collectors.toSet())
                :
                Files.walk(Paths.get(this.File_Path))
                        .parallel()
                        .unordered()
                        .map(Objects::toString)
                        .filter(x -> x.endsWith("." + ext))
                        .map(Paths::get)
                        .flatMap(ThrowingFunction.wrap(x -> Files.lines(x, StandardCharsets.ISO_8859_1)))
                        .filter(x -> x.contains(toMatch))
                        .peek(x -> System.out.println(colors.get(rand.nextInt(colors.size()-1)) + "Found something till now! _> " + x))
                        .collect(Collectors.toSet());
        return check;
    }

    public long getFileSizeMultipleRecursiveAndNot(String ext, boolean what) throws IOException {
        long sum = 0;
        sum = what ?
                Files.list(Paths.get(this.File_Path))
                        .parallel()
                        .map(Objects::toString)
                        .filter(x -> x.endsWith("." + ext))
                        .map(Paths::get)
                        .map(ThrowingFunction.wrap(Files::size))
                        .mapToLong(x -> x)
                        .sum()
                :
                Files.walk(Paths.get(this.File_Path))
                        .parallel()
                        .map(Objects::toString)
                        .filter(x -> x.endsWith("." + ext))
                        .map(Paths::get)
                        .map(ThrowingFunction.wrap(Files::size))
                        .mapToLong(x -> x)
                        .sum();
        return sum / (1024 * 1024 * 1024);
    }

    public long getFileSize() throws IOException {
        return (Files.size(Paths.get(this.File_Path)) / (1024 * 1024 * 1024));
    }

    public long getFileSizeMultipleNumbered(String ext, int upbound, int lowbound) throws IOException {
        long sum = 0;
        int count = lowbound;
        sum = IntStream.range(lowbound, upbound)
                .parallel()
                .mapToObj(Objects::toString)
                .map(x -> this.File_Path + x + "." + ext)
                .map(Paths::get)
                .map(ThrowingFunction.wrap(Files::size))
                .mapToLong(x -> x)
                .sum();
        /*
        while (count < upbound){
            sum += Files.size(Paths.get(this.File_Path  + count +"." +ext));
            count++;
        }
        */
        return sum / (1024 * 1024 * 1024);
    }


    public boolean checkEmpty() {
        return check.isEmpty();
    }
}
