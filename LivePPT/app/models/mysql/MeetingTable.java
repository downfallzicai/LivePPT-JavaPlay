package models.mysql;

import javax.persistence.*;

import play.db.ebean.*;

@Entity 
@Table(name="meeting")  
public class MeetingTable extends Model {

  @Id
  public Long id;
  
  public Long ppt_id;
  
  public Long status;
  
  public static Finder<Long,MeetingTable> find = new Finder<Long,MeetingTable>(
    Long.class, MeetingTable.class
  ); 

}