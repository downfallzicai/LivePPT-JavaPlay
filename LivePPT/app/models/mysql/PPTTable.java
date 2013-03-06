package models.mysql;

import javax.persistence.*;

import play.db.ebean.*;
import play.data.validation.*;

@Entity 
@Table(name="ppt")  
public class PPTTable extends Model {

  @Id
  @Constraints.Required
  @Column(name = "ppt_id")
  public Long ppt_id;
  
  @Constraints.Required
  @Column(name = "ppt_name")
  public String ppt_name;
  
  @Constraints.Required
  @Column(name = "owner_id")
  public Long owner_id;
  
  @Column(name = "convert_status")
  public Long convert_status;
  
  @Column(name = "ppt_pages")
  public Long ppt_pages;
  
  public static Finder<Long,PPTTable> find = new Finder<Long,PPTTable>(
    Long.class, PPTTable.class
  );
  
  @Override
  public String toString() {
	  return "---------\nid="+ppt_id+"\nname="+ppt_name+"\nowner_id="+owner_id+"\nconvert_status="+convert_status+"\nppt_pages="+ppt_pages+"\n---------\n";
  }

}