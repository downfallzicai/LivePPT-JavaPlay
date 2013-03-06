package models.mysql;

import javax.persistence.*;

import play.db.ebean.*;
import play.data.validation.*;

@Entity 
@Table(name="user")  
public class UserTable extends Model {

  @Id
  @Constraints.Required
  @Column(name = "user_id")
  public Long user_id;
  
  @Constraints.Required
  @Column(name = "username")
  public String username;
  
  @Constraints.Required
  @Column(name = "password")
  public String password;
  
  @Column(name = "expires_in")
  public long expires_in;
  
  @Column(name = "access_token")
  public String access_token;
  
  public static Finder<Long,UserTable> find = new Finder<Long,UserTable>(
    Long.class, UserTable.class
  ); 

}