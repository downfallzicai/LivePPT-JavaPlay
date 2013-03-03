package models.mysql;

import javax.persistence.*;

import play.db.ebean.*;
import play.data.validation.*;

@Entity 
@Table(name="ppt")  
public class PPTTable extends Model {

  @Id
  public Long id;
  
  @Constraints.Required
  public String name;
  
  @Constraints.Required
  public Long owner_id;
  
  public Long convert_status;
  
  public Long ppt_pages;
  
  public static Finder<Long,PPTTable> find = new Finder<Long,PPTTable>(
    Long.class, PPTTable.class
  ); 

}