package movealarm.kmitl.net.common;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by oat on 7/16/16.
 */
public abstract class AbstractEntity {
    @Id
    @GeneratedValue()
    public Integer id;
    protected Date createdDate;
    protected Date modifiedDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    protected void updateModifiedDate()
    {
        modifiedDate = new Date();
    }
}
