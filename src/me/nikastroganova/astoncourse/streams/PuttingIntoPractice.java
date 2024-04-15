package me.nikastroganova.astoncourse.streams;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class PuttingIntoPractice {

    public static void main(String... args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        List<Transaction> first =
                transactions.stream()
                        .filter(transaction -> transaction.getYear() == 2011)
                        .sorted(comparing(Transaction::getValue))
                        .collect(toList());
        System.out.println(first);

        List<String> second =
                transactions.stream()
                        .map(transaction -> transaction.getTrader().getCity())
                        .distinct()
                        .collect(toList());
        System.out.println(second);

        List<Trader> third =
                transactions.stream()
                        .map(Transaction::getTrader)
                        .filter(trader -> trader.getCity().equals("Cambridge"))
                        .sorted(comparing(Trader::getName))
                        .collect(toList());
        System.out.println(third);

        String four =
                transactions.stream()
                        .map(transaction -> transaction.getTrader().getName())
                        .distinct()
                        .sorted()
                        .reduce("", (a, b) -> a + b + " ");
        System.out.println(four);

        Boolean five =
                transactions.stream()
                        .map(transaction -> transaction.getTrader().getCity())
                        .anyMatch(city -> city.equals("Milan"));
        System.out.println(five);


        transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .forEach(System.out::println);


        Optional<Integer> max =
                transactions.stream()
                        .map(Transaction::getValue)
                        .reduce(Integer::max);


        Optional<Transaction> min =
                transactions.stream()
                        .reduce((x, y) -> x.getValue() < y.getValue() ? x : y);



    }
}
