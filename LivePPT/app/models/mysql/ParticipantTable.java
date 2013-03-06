package models.mysql;

import javax.persistence.*;

import play.db.ebean.*;
import play.data.validation.*;

@Entity 
@Table(name="participant")  
public class ParticipantTable extends Model {

  @Id
  @Column(name = "part_id")
  @Constraints.Required
  public Long part_id;
  
  @Column(name = "meeting_id")
  @Constraints.Required
  public Long meeting_id;
  
  @Column(name = "user_id")
  @Constraints.Required
  public Long user_id;
  
  public static Finder<Long,ParticipantTable> find = new Finder<Long,ParticipantTable>(
    Long.class, ParticipantTable.class
  ); 

}