package CaseRenicTest;

public class FormCards {
  private final String surname;
  private final String name;
  private final String telephone;
  private final String mail;

  public FormCards(String surname, String name, String telephone, String mail) {
    this.surname = surname;
    this.name = name;
    this.telephone = telephone;
    this.mail = mail;
  }

  public String getSurname() {
    return surname;
  }

  public String getName() {
    return name;
  }

  public String getTelephone() {
    return telephone;
  }

  public String getMail() {
    return mail;
  }
}
