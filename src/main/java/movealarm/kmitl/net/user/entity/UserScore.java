package movealarm.kmitl.net.user.entity;

import movealarm.kmitl.net.common.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by oat on 7/16/16.
 */
@Entity
@Table(name = "user_score")
public class UserScore extends AbstractEntity {

    Integer userID;
    Integer currentScore;
    Integer modifiedScore;

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(Integer currentScore) {
        this.currentScore = currentScore;
    }

    public Integer getModifiedScore() {
        return modifiedScore;
    }

    public void setModifiedScore(Integer modifiedScore) {
        this.modifiedScore = modifiedScore;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    String description;
}
