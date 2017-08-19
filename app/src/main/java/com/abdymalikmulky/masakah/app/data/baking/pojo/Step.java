
package com.abdymalikmulky.masakah.app.data.baking.pojo;

import com.abdymalikmulky.masakah.app.data.DatabaseConfig;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import org.parceler.Parcel;


@Table(database = DatabaseConfig.class)
@Parcel
public class Step extends BaseModel{
    @Column
    @PrimaryKey(autoincrement = true)
    public Integer realId;
    @SerializedName("id")
    @Expose
    @Column
    public Integer id;
    @Column
    public Integer bakingId;
    @SerializedName("shortDescription")
    @Expose
    @Column
    public String shortDescription;
    @SerializedName("description")
    @Expose
    @Column
    public String description;
    @SerializedName("videoURL")
    @Expose
    @Column
    public String videoURL;
    @SerializedName("thumbnailURL")
    @Expose
    @Column
    public String thumbnailURL;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRealId() {
        return realId;
    }

    public void setRealId(Integer realId) {
        this.realId = realId;
    }

    public Integer getBakingId() {
        return bakingId;
    }

    public void setBakingId(Integer bakingId) {
        this.bakingId = bakingId;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }

    @Override
    public String toString() {
        return "Step{" +
                "realId=" + realId +
                ", id=" + id +
                ", bakingId=" + bakingId +
                ", shortDescription='" + shortDescription + '\'' +
                ", description='" + description + '\'' +
                ", videoURL='" + videoURL + '\'' +
                ", thumbnailURL='" + thumbnailURL + '\'' +
                '}';
    }
}
