package models;

import java.util.*;

import javax.persistence.*;
import play.db.jpa.*;

@Entity
public abstract class Commentable extends Likeable
{
  @OneToMany(mappedBy="parentObj", cascade=CascadeType.ALL)
  public List<Comment> comments;

  public Commentable(User owner) {
    super(owner);
  }

  public Commentable(User owner, Visibility v) {
    super(owner, v);
  }

  public Commentable addComment(User author, String content) {
    Comment newComment = new Comment(this, author, content).save();
    this.comments.add(newComment);
    this.save();
    return this;
  }

  public List<Comment> getComments(){
    return Comment.find("byParentObj", this).fetch();
  }
}
