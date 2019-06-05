/**********************************************************************************************************************************
 *     __                       __                         ____                                  __                               *
 *    /\ \                     /\ \                       /\  _`\                               /\ \                              *
 *    \ \ \         __     __  \ \ \/'\      __   _ __    \ \,\L\_\     __     __     _ __   ___\ \ \___      __   _ __           *
 *     \ \ \  __  /'__`\ /'__`\ \ \ , <    /'__`\/\`'__\   \/_\__ \   /'__`\ /'__`\  /\`'__\/'___\ \  _ `\  /'__`\/\`'__\         *
 *      \ \ \L\ \/\  __//\ \L\.\_\ \ \\`\ /\  __/\ \ \/      /\ \L\ \/\  __//\ \L\.\_\ \ \//\ \__/\ \ \ \ \/\  __/\ \ \/          *
 *       \ \____/\ \____\ \__/.\_\\ \_\ \_\ \____\\ \_\      \ `\____\ \____\ \__/.\_\\ \_\\ \____\\ \_\ \_\ \____\\ \_\          *
 *        \/___/  \/____/\/__/\/_/ \/_/\/_/\/____/ \/_/       \/_____/\/____/\/__/\/_/ \/_/ \/____/ \/_/\/_/\/____/ \/_/          *
 *                                                                                                                                *
 *    Advanced Leaker Searcher in files...                                                                                        *
 *    Coded By Virgula in December 2017 switched to java and improved in June 2019 with 2nd version                               *
 *    For more information visit the project page: https://github.com/Virgula0/LeakerSearcher2.0                                  *
 *    Suggestions and bugs: https://github.com/Virgula0/LeakerSearcher2.0/issues                                                  *
 *    Coded in Java from Italy with ❤️                                                                                            *
 **********************************************************************************************************************************
 */

package LeakerSearcher;

import MultipleFileSearch.MultipleFileSearchNoNumbered;
import MultipleFileSearch.NumberSearch;
import Processes.ProcessSingleFile;
import SearchAllFilesInAPathAndSub.RecursiveFileSearchNoNumbered;
import SingleFileSearch.SingleSearch;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class start {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    private static Set<String> result;
    private static long File_Size;
    private static List<String> colors = new ArrayList<>(Arrays.asList(ANSI_RED, ANSI_GREEN, ANSI_YELLOW, ANSI_BLUE, ANSI_PURPLE, ANSI_CYAN, ANSI_WHITE));
    private static Random rand = new Random();


    public static void resetForeground() {
        System.out.println(ANSI_RESET);
    }

    public static void initial() {
        System.out.println(colors.get(rand.nextInt(colors.size()-1)));

        String gg = "" +
                "\t\t+-------------------------------------------------------------------------------+\n" +
                "\t\t|  _                _                _____                     _                |\n" +
                "\t\t| | |              | |              / ____|                   | |               |\n" +
                "\t\t| | |     ___  ____| | _____ ____  | (___   ___  __ _ _ __ ___| |__   ___ ____  |\n" +
                "\t\t| | |    / _  / _  | |/ / _    __|  |___ | / _  / __ | ___/ __|  _   / _ |  __| |\n" +
                "\t\t| | |___|  __/ (_| | <  | __/ |     ____) |  __/ (_| | | | (__| | | |  __/ |    |\n" +
                "\t\t| |______ ___|_____|_|__/___|_|    |_____/ \\___| ____|_|  \\___|_| |_| ___|_|    |\n" +
                "\t\t|                                                                               |\n" +
                "\t\t| Quick and simple algorithm to search in big files very fast mode              |\n" +
                "\t\t| Coded By Virgula @2017 updated in June 2019                                   |\n" +
                "\t\t| Git: https://github.com/Virgula0/LeakerSearcher                               |\n" +
                "\t\t| Version: {2.0#Stable}                                                         |\n" +
                "\t\t| Note: Close your other opened programs before to launch this.                 |\n" +
                "\t\t+-------------------------------------------------------------------------------+\n";

        System.out.println(gg);
        resetForeground();
    }

    public static void menu() {
        System.out.println("+-----------------------------------------------------------------------------+");
        System.out.println("|(1) Search in a single file.                                                 |");
        System.out.println("|(2) Search in number ordered files (Example: from 1.txt to n.txt.)           |");
        System.out.println("|(3) Search in all .ext files in a single path.                               |");
        System.out.println("|(4) Search in all .ext files searching also in subdirectories if there are.  |");
        System.out.println("+-----------------------------------------------------------------------------+");
    }

    public static void usage() {
        System.out.println("Usage: java -jar lks.jar --mode 1 --path /path/of/the/file --search stringtosearch");
        System.out.println("Optional:\n[--noverbose] disable verbosity while searching");
        System.out.println("[--output=file.txt] save results in an output file");
        System.out.println("[--bound=0,6] If you use mode 2 specify the upper bound of the name of files(each number to n will be the name file)");
        System.out.println("[--ext=txt] If you use mode 2,3,4 specify the extension of the files to read");

        System.out.println("Mode available: ");
        menu();
        System.out.println(ANSI_RESET);
    }


    public static void main(String[] args) throws IOException {
        start.initial();
        String read = "", file = "", output_file = "", ext = "";
        int ch = 0, upbound = 0, lowbound = 0;
        boolean noverbose = false;
        boolean bound_present = false, ext_present = false;


        if (args.length > 0) {
            if (args[0].equals("--mode") && args[2].equals("--path") && args[4].equals("--search")) {
                ch = Integer.parseInt(args[1]);
                if (ch <= 0 || ch > 4) {
                    System.out.println(ANSI_RED);
                    System.out.println("[CRITICAL] Not a valid option");
                    usage();
                    System.exit(1);
                }

                file = args[3];
                if (!Files.exists(Paths.get(file))) {
                    System.out.println(ANSI_RED);
                    System.out.println("[CRITICAL] File not found!");
                    resetForeground();
                    System.exit(1);
                }
                read = args[5];
            } else {
                System.out.println(ANSI_RED);
                System.out.println("[CRITICAL] No valid input");
                usage();
                System.exit(3);
            }
            for (String arg : args) {
                if (arg == null || arg.equals("") || arg.equals(" ")) {
                    System.out.println(ANSI_RED);
                    System.out.println("[CRITICAL] No valid input.");
                    usage();
                    System.exit(2);
                }
                if (arg.equals("--noverbose"))
                    noverbose = true;
                if (arg.startsWith("--output="))
                    output_file = arg.split("=")[1];
                if (arg.startsWith("--bound=")) {
                    String temp = arg.split("=")[1];
                    lowbound = Integer.parseInt(temp.split(",")[0]);
                    upbound = Integer.parseInt(temp.split(",")[1]);
                    bound_present = true;
                }
                if (arg.startsWith("--ext=")) {
                    ext = arg.split("=")[1];
                    ext_present = true;
                }
            }
            if (ch == 2 && (!bound_present || !ext_present)) {
                System.out.println(ANSI_RED);
                System.out.println("[CRITICAL] Option 2 requires bound and extension in input.");
                usage();
                System.exit(10);
            } else if ((ch == 3 || ch == 4) && !ext_present) {
                System.out.println(ANSI_RED);
                System.out.println("[CRITICAL] Option 3 and 4 requires extension in input.");
                usage();
                System.exit(10);
            }
        } else {
            start.menu();

            System.out.println(colors.get(rand.nextInt(colors.size()-1)));

            System.out.print("Insert your string to search=> ");

            Scanner word = new Scanner(System.in);
            System.out.print(ANSI_RESET);

            read = word.nextLine();

            Scanner location = new Scanner(System.in);

            do {
                System.out.println(colors.get(rand.nextInt(colors.size()-1)));
                System.out.print("Insert your file path=> ");
                System.out.print(ANSI_RESET);
                file = location.nextLine();
                if (!Files.exists(Paths.get(file))) {
                    System.out.println("File not found please try again");
                }
            } while (!Files.exists(Paths.get(file)));

            Scanner option = new Scanner(System.in);
            do {
                System.out.println(colors.get(rand.nextInt(colors.size()-1)));
                System.out.print("Choose your option: ");
                System.out.print(ANSI_RESET);
                ch = option.nextInt();
                if (ch <= 0 || ch > 4) {
                    System.out.println(ANSI_RED);
                    System.out.println("[WARNING] Not a valid option try again");
                }
            } while (ch <= 0 || ch > 4);

            Scanner choose_2 = new Scanner(System.in);

            if (ch == 2 || ch == 3 || ch == 4) {
                do {
                    System.out.println(colors.get(rand.nextInt(colors.size()-1)));
                    System.out.print("Insert your extension: ");
                    System.out.print(ANSI_RESET);
                    ext = choose_2.nextLine().trim();
                    if (ext.isEmpty()) {
                        System.out.println(ANSI_RED);
                        System.out.println("[WARNING] Not a valid extension try again");
                    }
                } while (ext.isEmpty());
            }

            if (ch == 2) {
                do {
                    System.out.println(colors.get(rand.nextInt(colors.size()-1)));
                    System.out.print("Insert you lowerbound and upper bound separated by a ,: ");
                    System.out.print(ANSI_RESET);
                    String bound = choose_2.nextLine();

                    lowbound = Integer.parseInt(bound.split(",")[0]);
                    upbound = Integer.parseInt(bound.split(",")[1]);

                    if (upbound < 0 || lowbound < 0) {
                        System.out.println(ANSI_RED);
                        System.out.println("[WARNING] Not a valid bound try again");
                    }
                } while (upbound < 0 || lowbound < 0);
            }

            Scanner out = new Scanner(System.in);
            System.out.println(colors.get(rand.nextInt(colors.size()-1)));
            System.out.print("Insert your output file (Blank for skipping): ");
            System.out.print(ANSI_RESET);
            output_file = out.nextLine();

            Scanner verb = new Scanner(System.in);
            System.out.println(colors.get(rand.nextInt(colors.size()-1)));
            System.out.print("Active noverbose?(true/false): ");
            System.out.print(ANSI_RESET);
            noverbose = verb.nextBoolean();

        }

        //System.out.println(ch);
        long start = System.currentTimeMillis();
        System.out.println(colors.get(rand.nextInt(colors.size()-1)));
        System.out.println("[DONE] Process started please wait...");
        System.out.print(ANSI_RESET);

        ProcessSingleFile file1 = new ProcessSingleFile();
        long elapsedTime = 0;


        switch (ch) {
            case 1:
                SingleSearch single = new SingleSearch(file, read);
                result = single.search(noverbose);
                elapsedTime = (System.currentTimeMillis() - start) / 1000;
                File_Size = single.getTotalFileSize();
                break;
            case 2:
                NumberSearch number = new NumberSearch(file, read);
                result = number.search(noverbose, ext, upbound, lowbound);
                elapsedTime = (System.currentTimeMillis() - start) / 1000;
                File_Size = number.getTotalFileSize(ext, lowbound, upbound);
                break;
            case 3:
                MultipleFileSearchNoNumbered th3 = new MultipleFileSearchNoNumbered(file, read);
                result = th3.search(noverbose, ext);
                elapsedTime = (System.currentTimeMillis() - start) / 1000;
                File_Size = th3.getTotalFileSize(ext);
                break;
            case 4:
                RecursiveFileSearchNoNumbered recursive = new RecursiveFileSearchNoNumbered(file, read);
                result = recursive.search(noverbose, ext);
                elapsedTime = (System.currentTimeMillis() - start) / 1000;
                File_Size = recursive.getTotalFileSize(ext);
                break;
            default:
                System.out.print(ANSI_RED);
                System.out.println("No valid option selected");
                System.exit(5);
        }

        if (!file1.checkEmpty()) {
            System.out.print(colors.get(rand.nextInt(colors.size()-1)));
            System.out.println("\nFound " + result.size() + " matches");
            result.forEach(System.out::println);
            System.out.print(colors.get(rand.nextInt(colors.size()-1)));
            System.out.println("\nProgram has finished and has matched succesfully in " + elapsedTime + "s.\nTotal size examined is approximately: " + File_Size + "GB \n");
            if (!output_file.equals("")) {
                Files.write(Paths.get(output_file), result);
                System.out.println("Created output file at: " + output_file);
            }
            System.out.println(ANSI_RESET);

        } else {
            System.out.println(ANSI_RED);
            System.out.println("No match found in file/s! Elapsed time: " + elapsedTime + "s.\nTotal size examined was: " + File_Size + "GB \n");
        }
        System.out.println(ANSI_RESET);
    }
}