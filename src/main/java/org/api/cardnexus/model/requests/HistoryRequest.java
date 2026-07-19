package org.api.cardnexus.model.requests;

import java.time.LocalDate;

import org.api.cardnexus.model.enums.EnumFinishes;
import org.api.cardnexus.model.enums.EnumMarketPlace;

public class HistoryRequest extends AbstractGetRequest {

    private int idProduct;
    private LocalDate from;
    private LocalDate to;
    private EnumMarketPlace marketplace;
    private EnumFinishes finish;

    private HistoryRequest() {
    }

    public static HistoryRequest create() {
	return new HistoryRequest();
    }
    public HistoryRequest setFinish(EnumFinishes finish) {
	this.finish = finish;
	return this;
    }
    public HistoryRequest setIdProduct(int idProduct) {
	this.idProduct = idProduct;
	return this;
    }
    public HistoryRequest setFrom(LocalDate from) {
	this.from = from;
	return this;
    }

    public HistoryRequest setTo(LocalDate to) {
	this.to = to;
	return this;
    }
    public HistoryRequest setMarketplace(EnumMarketPlace marketplace) {
	this.marketplace = marketplace;
	return this;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public LocalDate getFrom() {
        return from;
    }

    public LocalDate getTo() {
        return to;
    }

    public EnumMarketPlace getMarketplace() {
        return marketplace;
    }

    public EnumFinishes getFinish() {
        return finish;
    }

}
