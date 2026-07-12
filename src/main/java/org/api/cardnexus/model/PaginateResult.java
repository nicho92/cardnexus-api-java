package org.api.cardnexus.model;

import java.util.List;

public class PaginateResult<T> {
    
   private List<T> data;
   private Pagination pagination;
   
   
   public List<T> getData() {
    return data;
   }
   public void setData(List<T> data) {
    this.data = data;
   }
   public Pagination getPagination() {
    return pagination;
   }
   public void setPagination(Pagination pagination) {
    this.pagination = pagination;
   }
    
    
    
    
    

}
