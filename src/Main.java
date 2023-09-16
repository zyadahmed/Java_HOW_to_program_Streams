import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class Main {
    public static void printCharOccurrences(String filename) throws IOException {
        String data = Files.readString(Paths.get(filename)).toLowerCase();

        Map<Character, Long> charOccurrences = data.chars()
                .filter(c -> c >= 'a' && c <= 'z')
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        charOccurrences.forEach((key, value) -> System.out.println("char = " + key + " freq = " + value));
    }
    public static void printDIrInfo(Path path) throws IOException {
        DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path);
        directoryStream.forEach(file-> System.out.println(file.getFileName()));
    }
    public static void sortByDecription(Invoice[] invoices){
        Arrays.stream(invoices).sorted(Comparator.comparing(Invoice::getPartDescription))
                .forEach(System.out::println);
    }
    public static void sortByPrice(Invoice[] invoices){
        Arrays.stream(invoices).sorted(Comparator.comparing(Invoice::getPrice))
                .forEach(System.out::println);
    }

    public static void mapToDescriptionandQuantity(Invoice[] invoices){
        Map<Integer,String> mapped = Arrays.stream(invoices).collect(Collectors.toMap(Invoice::getQuantity,Invoice::getPartDescription));
        TreeMap<Integer,String> sortedmapped = new TreeMap<>(mapped);
        sortedmapped.forEach((q,p)-> System.out.println("Quantity =  " + q + " part = "+ p));
    }
    public static void mapToDescriptionandandValue(Invoice[] invoices) {
        Function<Invoice, Double> totalValue = invoice -> invoice.getQuantity() * invoice.getPrice();
        Map<Double, String> valuemap = Arrays.stream(invoices).collect(Collectors.toMap(totalValue, Invoice::getPartDescription));
        TreeMap<Double, String> sortedvaluemap = new TreeMap<>(valuemap);
        sortedvaluemap.forEach((total, p) -> System.out.println("Total =  " + total + " part = " + p));
        sortedvaluemap.entrySet().stream()
                .filter(entry -> entry.getKey() >= 200.0 && entry.getKey() <= 500)
                .forEach(entry -> System.out.println("Total = " + entry.getKey() + " part = " + entry.getValue()));
    }
    public static void DuplicateRemoval(){
        Scanner input = new Scanner(System.in);
        System.out.println("Ente sentence");
        String senetce = input.nextLine();
        String[] words = senetce.split("//s+");
        Set<String> unique =  Arrays.stream(words).collect(Collectors.toSet());
        TreeSet<String> sortedunique = new TreeSet<>(unique);
        sortedunique.forEach(System.out::println);
    }
    public static void randomletters(){
        SecureRandom secureRandom = new SecureRandom();
        List<Character> randomChars =  new ArrayList<>(30);
        for (int i = 0; i < 30; i++) {
            char randomChar = (char) (secureRandom.nextInt(26) + 'a');
            randomChars.add(randomChar);
        }
        List<Character> chars = randomChars.stream().map(x->(char)(secureRandom.nextInt(26)+'a')).collect(Collectors.toList());
        System.out.println("Sort with duplication : ");
        chars.stream().sorted().forEach(System.out::println);
        System.out.println("Sort with duplication reverse order ");
        chars.stream().sorted(Comparator.reverseOrder()).forEach(System.out::println);

        TreeSet<Character> sorted = new TreeSet<>(chars);
        System.out.println("Sort without duplication");

        sorted.stream().forEach(System.out::println);


    }
    public static void mapThenReduce(int[] values){
        System.out.println(IntStream.of(values).map(x->x*x).reduce(0,(x,y)->x+y));
    }



    public static void main(String[] args) {
        try {
            printCharOccurrences("file.txt");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try {
            printDIrInfo(Paths.get("/home/ziad"));

        }catch (IOException e){
            e.getMessage();
        }
        Invoice[] invoices = {
                new Invoice(83,"Electric sander",7,57.98),
                new Invoice(24,"Power saw",18,99.99),
                new Invoice(7,"Sledge hammer",11,21.50),
                new Invoice(77,"Hammer",76,11.99),
                new Invoice(39,"LAwn moner",3,79.50)

        };
        sortByDecription(invoices);
        sortByPrice(invoices);
        mapToDescriptionandQuantity(invoices);
        mapToDescriptionandandValue(invoices);
        randomletters();
        int[] values = {3, 10, 6, 1, 4, 8, 2, 5, 9, 7};
        mapThenReduce(values);






    }
}