package com.rosgro.model;

public class Product {

  private String url;
  private String name;
  private double price;
  public Product(String url, String name, double price) {
    this.url = url;
    this.name = name;
    this.price = price;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Product jeans1 = (Product) o;

    if (Double.compare(jeans1.price, price) != 0) {
      return false;
    }
    if (!url.equals(jeans1.url)) {
      return false;
    }
    return name.equals(jeans1.name);
  }

  @Override
  public int hashCode() {
    int result;
    long temp;
    result = url.hashCode();
    result = 31 * result + name.hashCode();
    temp = Double.doubleToLongBits(price);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    return result;
  }

  @Override
  public String toString() {
    return "Jeans{" +
        "url='" + url + '\'' +
        ", name='" + name + '\'' +
        ", price=" + price +
        '}';
  }

}
