package com.develogical;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class QueryProcessor {

  public static final String ADDITION_PATTERN = "What is (\\d+) plus (\\d+)\\?";
  public static final String MULTIPLIED_PATTERN = "What is (\\d+) multiplied by (\\d+)\\?";

  public static final String CUBE_PATTERN = "Which of the following numbers is both a square and a cube: (\\d+) multiplied by (\\d+)\\?";
  
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
      final String numbersListStr = query.substring(0, query.length() - 1).split(": ")[1];
      final String[] numberStr = numbersListStr.split(",\\s");
      return String.valueOf(Stream.of(numberStr)
              .map(Integer::valueOf)
              .mapToInt(i -> i)
              .max()
              .orElse(0));
    }

    if (query.matches(ADDITION_PATTERN)) {
      final Pattern pattern = Pattern.compile(ADDITION_PATTERN);
      final Matcher matcher = pattern.matcher(query);
      matcher.find();
      final Integer firstNum = Integer.valueOf(matcher.group(1));
      final Integer secondNum = Integer.valueOf(matcher.group(2));

      return String.valueOf(firstNum + secondNum);

    }

    if (query.matches(MULTIPLIED_PATTERN)) {
      final Pattern pattern = Pattern.compile(MULTIPLIED_PATTERN);
      final Matcher matcher = pattern.matcher(query);
      matcher.find();
      final Integer firstNum = Integer.valueOf(matcher.group(1));
      final Integer secondNum = Integer.valueOf(matcher.group(2));

      return String.valueOf(firstNum * secondNum);

    }


    if (query.matches(MULTIPLIED_PATTERN)) {
      final Pattern pattern = Pattern.compile(MULTIPLIED_PATTERN);
      final Matcher matcher = pattern.matcher(query);
      matcher.find();
      final Integer firstNum = Integer.valueOf(matcher.group(1));
      final Integer secondNum = Integer.valueOf(matcher.group(2));

      return String.valueOf(firstNum * secondNum);

    }
    return "";
  }
}
