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

  @Test
  public void knowsHowToAddNumbers() throws Exception {
    assertThat(queryProcessor.process(
            "What is 47 plus 62?"),
            is("109"));
  }

  @Test
  public void knowsHowToFindCubeAndSquare() throws Exception {
    assertThat(queryProcessor.process(
                    "Which of the following numbers is both a square and a cube: 3476, 961, 2468, 64, 1535, 125, 2280?"),
            is("64"));
  }


  @Test
  public void knowsHowToTimesNumbers() throws Exception {
    assertThat(queryProcessor.process(
                    "What is 2 multiplied by 35?"),
            is("70"));
  }

  @Test
  public void knowsIfANumberIsPrime() throws Exception {
    assertThat(queryProcessor.process(
                    "Which of the following numbers are primes: 80, 79, 75, 32, 71?"),
            is("79,71"));
  }

  @Test
  public void knowsHowToMinusNumbers() throws Exception {
    assertThat(queryProcessor.process(
                    "What is 48 minus 9?"),
            is("39"));
  }

}
