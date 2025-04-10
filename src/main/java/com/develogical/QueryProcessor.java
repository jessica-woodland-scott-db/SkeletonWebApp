package com.develogical;
import org.apache.commons.math3.primes.Primes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class QueryProcessor {

  public static final String ADDITION_PATTERN = "What is (\\d+) plus (\\d+)\\?";
  public static final String MINUS_PATTERN = "What is (\\d+) minus (\\d+)\\?";

  public static final String MULTIPLIED_PATTERN = "What is (\\d+) multiplied by (\\d+)\\?";
  public static final String CUBE = "cube";

  public String process(String query) {

    System.out.println("Received query:" + query);

    if (query.equals("What is your name?")) {
      return "Jess";
    }

    if (query.toLowerCase().contains("shakespeare")) {
      return "William Shakespeare (26 April 1564 - 23 April 1616) was an "
              + "English poet, playwright, and actor, widely regarded as the greatest "
              + "writer in the English language and the world's pre-eminent dramatist.";
    }

    if (query.toLowerCase().contains("henry")) {
      return "Henry VIII (28 June 1491 – 28 January 1547) was King of England from 22 April " +
              "1509 until his death in 1547. Henry is known for his six marriages and his efforts " +
              "to have his first marriage (to Catherine of Aragon) annulled. His disagreement with " +
              "Pope Clement VII about such an annulment led Henry to initiate the English Reformation, " +
              "separating the Church of England from papal authority. He appointed himself Supreme Head " +
              "of the Church of England and dissolved convents and monasteries, for which he was" +
              " excommunicated by the pope.";
    }

    if (query.startsWith("Which of the following numbers is the largest:")) {
      final String[] numberStr = getNumberStringAfter(query);
      return String.valueOf(Stream.of(numberStr)
              .map(Integer::valueOf)
              .mapToInt(i -> i)
              .max()
              .orElse(0));
    }

    if (query.matches(ADDITION_PATTERN)) {
      Result result = getResult(query,ADDITION_PATTERN);
      return String.valueOf(result.firstNum + result.secondNum);
    }

    if (query.matches(MINUS_PATTERN)) {
      Result result = getResult(query,MINUS_PATTERN);
      return String.valueOf(result.firstNum - result.secondNum);
    }

    if (query.matches(MULTIPLIED_PATTERN)) {
      Result result = getResult(query,MULTIPLIED_PATTERN);
      return String.valueOf(result.firstNum * result.secondNum);

    }

    if (query.contains(CUBE)) {
      final String[] numberStr = getNumberStringAfter(query);
      return Stream.of(numberStr)
              .map(Integer::valueOf)
              .mapToInt(i -> i)
              .filter(i -> Math.cbrt(i) % 1 == 0)
              .filter(i -> Math.sqrt(i) % 1 == 0)
              .mapToObj(i -> String.valueOf(i))
              .collect(Collectors.joining(","));

    }

    if (query.contains("prime")) {
      final String[] numberStr = getNumberStringAfter(query);
      return Stream.of(numberStr)
              .map(Integer::valueOf)
              .mapToInt(i -> i)
              .filter(Primes::isPrime)
              .mapToObj(i -> String.valueOf(i))
              .collect(Collectors.joining(","));
    }
    return "";
  }

  private static Result getResult(String query, String queryPattern) {
    final Pattern pattern = Pattern.compile(queryPattern);
    final Matcher matcher = pattern.matcher(query);
    matcher.find();
    final Integer firstNum = Integer.valueOf(matcher.group(1));
    final Integer secondNum = Integer.valueOf(matcher.group(2));
    Result result = new Result(firstNum, secondNum);
    return result;
  }

  private static class Result {
    public final Integer firstNum;
    public final Integer secondNum;

    public Result(Integer firstNum, Integer secondNum) {
      this.firstNum = firstNum;
      this.secondNum = secondNum;
    }
  }

  private static String[] getNumberStringAfter(String query) {
    final String numbersListStr = query.substring(0, query.length() - 1).split(": ")[1];
    final String[] numberStr = numbersListStr.split(",\\s");
    return numberStr;
  }
}
