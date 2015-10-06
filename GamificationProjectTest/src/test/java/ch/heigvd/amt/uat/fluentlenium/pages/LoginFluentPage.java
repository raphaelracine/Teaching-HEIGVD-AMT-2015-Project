package ch.heigvd.amt.uat.fluentlenium.pages;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This class is used to test the "Login" page in the MVCDemo app.
 *
 * @author Olivier Liechti
 */
public class LoginFluentPage extends AbstractGamificationProjectFluentPage {

  private final static String inputEmail = "#login"; // id in the html code
  private final static String inputPassword = "#inputPassword"; // id in the html code
  private final static String buttonSignin = "#blogin"; // id in the html code

  @Override
  public void isAt() {
    assertThat(title()).isEqualTo("Login Page");
  }

  public void typeEmailAddress(String email) {
    fill(inputEmail).with(email);
  }

  public void typePassword(String password) {
    fill(inputPassword).with(password);
  }

  public void clickSignin() {
    click(buttonSignin);
  }

  public String getUrl() {
    return "/";
  }

}
