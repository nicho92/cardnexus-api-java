package org.api.cardnexus.model;

import org.api.cardnexus.model.enums.EnumKindsRun;

public class RunOption {

    private EnumKindsRun kind;
    private int sellerCount;
    private Amount total;
    private Amount subtotal;
    private Amount shipping;
    private Amount buyerFee;
    
    
    public Amount getBuyerFee() {
	return buyerFee;
    }
    public void setBuyerFee(Amount buyerFee) {
	this.buyerFee = buyerFee;
    }
   public void setKind(EnumKindsRun kind) {
       this.kind = kind;
   }
   public EnumKindsRun getKind() {
       return kind;
   }
    public int getSellerCount() {
        return sellerCount;
    }
    public void setSellerCount(int sellerCount) {
        this.sellerCount = sellerCount;
    }
    public Amount getTotal() {
        return total;
    }
    public void setTotal(Amount total) {
        this.total = total;
    }
    public Amount getSubtotal() {
        return subtotal;
    }
    public void setSubtotal(Amount subtotal) {
        this.subtotal = subtotal;
    }
    public Amount getShipping() {
        return shipping;
    }
    public void setShipping(Amount shipping) {
        this.shipping = shipping;
    }
    
    
    
    
}
