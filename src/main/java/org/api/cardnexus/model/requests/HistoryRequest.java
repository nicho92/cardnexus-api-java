package org.api.cardnexus.model.requests;

import java.time.LocalDate;

import org.api.cardnexus.model.enums.EnumFinishes;
import org.api.cardnexus.model.enums.EnumMarketPlace;

public class HistoryRequest {

  private Integer idProduct;
  private LocalDate from;
  private LocalDate to;
  private EnumMarketPlace place;
  private EnumFinishes finish;
  
  public EnumFinishes getFinish() {
    return finish;
  }
  public void setFinish(EnumFinishes finish) {
    this.finish = finish;
  }
  public Integer getIdProduct() {
    return idProduct;
  }
  public void setIdProduct(Integer idProduct) {
    this.idProduct = idProduct;
  }
  public LocalDate getFrom() {
    return from;
  }
  public void setFrom(LocalDate from) {
    this.from = from;
  }
  public LocalDate getTo() {
    return to;
  }
  public void setTo(LocalDate to) {
    this.to = to;
  }
  public EnumMarketPlace getPlace() {
    return place;
  }
  public void setPlace(EnumMarketPlace place) {
    this.place = place;
  }
    
}
