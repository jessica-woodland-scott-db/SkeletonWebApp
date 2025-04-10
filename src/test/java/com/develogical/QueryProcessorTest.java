package com.develogical;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class QueryProcessorTest {

  QueryProcessor queryProcessor = new QueryProcessor();

  @Test
  public void returnsEmptyStringIfCannotProcessQuery() throws Exception {
    assertThat(queryProcessor.process("test"), is(""));
  }

  @Test
  public void knowsAboutShakespeare() throws Exception {
    assertThat(queryProcessor.process("Shakespeare"), containsString("playwright"));
  }

  @Test
  public void knowsWhatIsYourName() throws Exception {
    assertThat(queryProcessor.process("What is your name?"), containsString("Jess"));
  }

  @Test
  public void knowsHowToFindMaxNumberInList() throws Exception {
    assertThat(queryProcessor.process(
            "Which of the following numbers is the largest: 16, 6, 64?"),
            is("64"));
  }

}
