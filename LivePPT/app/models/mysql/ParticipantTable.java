package models.mysql;

import javax.persistence.*;

import play.db.ebean.*;

@Entity 
@Table(name="participant")  
public class ParticipantTable extends Model {

  @Id
  public Long id;
  
  public Long meeting_id;
  
  public Long member_id;
  
  public static Finder<Long,ParticipantTable> find = new Finder<Long,ParticipantTable>(
    Long.class, ParticipantTable.class
  ); 

}