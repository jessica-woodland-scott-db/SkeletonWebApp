package com.develogical;

public class QueryProcessor {

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

    return "";
  }
}
