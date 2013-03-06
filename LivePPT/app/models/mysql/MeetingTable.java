package models.mysql;

import javax.persistence.*;

import play.db.ebean.*;
import play.data.validation.*;

@Entity 
@Table(name="meeting")  
public class MeetingTable extends Model {

  @Id
  @Column(name = "meeting_id")
  @Constraints.Required
  public Long meeting_id;
  
  @Column(name = "ppt_id")
  @Constraints.Required
  public Long ppt_id;
  
  @Column(name = "meeting_status")
  @Constraints.Required
  public Long meeting_status;
  
  public static Finder<Long,MeetingTable> find = new Finder<Long,MeetingTable>(
    Long.class, MeetingTable.class
  ); 

}