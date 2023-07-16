package io.github.kavishkamk.testapp.eventbus;

public class Ping {
  private String message;
  private boolean isValid;

  public Ping() {

  }

  public boolean isValid() {
    return isValid;
  }

  public String getMessage() {
    return message;
  }

  public Ping(String message, boolean isValid) {
    this.message = message;
    this.isValid = isValid;
  }

  @Override
  public String toString() {
    return "Ping{" +
      "message='" + message + '\'' +
      ", isValid=" + isValid +
      '}';
  }
}
