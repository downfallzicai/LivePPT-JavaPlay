package models.mysql;

import javax.persistence.*;

import play.db.ebean.*;
import play.data.validation.*;

@Entity 
@Table(name="user")  
public class UserTable extends Model {

  @Id
  public Long id;
  
  @Constraints.Required
  public String username;
  
  @Constraints.Required
  public String password;
  
  public static Finder<Long,UserTable> find = new Finder<Long,UserTable>(
    Long.class, UserTable.class
  ); 

}