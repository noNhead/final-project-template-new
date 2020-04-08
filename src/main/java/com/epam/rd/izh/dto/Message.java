package com.epam.rd.izh.dto;

public class Message {
    /**
     * Пример DTO класса;
     * Взамен данного класса необходимо создать свой, подходяший для выбранного финального проекта.
     *
     * Можно использовать библиотеку Lombok для кодогенерации сеттеров и геттеров:
     * https://mvnrepository.com/artifact/org.projectlombok/lombok
     */

    private String id;
    private String message;

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }
}
