
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
public class Ingredient extends BaseModel {

    @Column
    @PrimaryKey(autoincrement = true)
    public Integer id;

    @Column
    public Integer bakingId;

    @SerializedName("quantity")
    @Expose
    @Column
    public double quantity;

    @SerializedName("measure")
    @Expose
    @Column
    public String measure;

    @SerializedName("ingredient")
    @Expose
    @Column
    public String ingredient;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBakingId() {
        return bakingId;
    }

    public void setBakingId(Integer bakingId) {
        this.bakingId = bakingId;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "id=" + id +
                ", bakingId=" + bakingId +
                ", quantity=" + quantity +
                ", measure='" + measure + '\'' +
                ", ingredient='" + ingredient + '\'' +
                '}';
    }
}
