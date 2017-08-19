
package com.abdymalikmulky.masakah.app.data.baking.pojo;

import com.abdymalikmulky.masakah.app.data.DatabaseConfig;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.OneToMany;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.BaseModel;

import org.parceler.Parcel;

import java.util.List;

@Table(database = DatabaseConfig.class)
@Parcel
public class Baking extends BaseModel {

    @SerializedName("id")
    @Expose
    @Column
    @PrimaryKey(autoincrement = false)
    public Integer id;

    @SerializedName("name")
    @Expose
    @Column
    public String name;

    @SerializedName("ingredients")
    @Expose
    public List<Ingredient> ingredients = null;

    @SerializedName("steps")
    @Expose
    public List<Step> steps = null;

    @SerializedName("servings")
    @Expose
    @Column
    public Integer servings;

    @SerializedName("image")
    @Expose
    @Column
    public String image;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    public Integer getServings() {
        return servings;
    }

    public void setServings(Integer servings) {
        this.servings = servings;
    }

    public String getImageDrawable() {
        return getName().toLowerCase().replace(" ", "");
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public List<Step> getSteps() {
        return steps;
    }

    @OneToMany(methods = OneToMany.Method.ALL, variableName = "ingredients")
    public List<Ingredient> getIngredientsLocal() {
        if (ingredients == null) {
            ingredients = SQLite.select()
                    .from(Ingredient.class)
                    .where(Ingredient_Table.bakingId.eq(id))
                    .queryList();
        }
        return ingredients;
    }

    @OneToMany(methods = OneToMany.Method.ALL, variableName = "steps")
    public List<Step> getStepsLocal() {
        if (steps == null) {
            steps = SQLite.select()
                    .from(Step.class)
                    .where(Step_Table.bakingId.eq(id))
                    .queryList();
        }
        return steps;
    }

    @Override
    public String toString() {
        return "Baking{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ingredients=" + ingredients +
                ", steps=" + steps +
                ", servings=" + servings +
                ", image='" + image + '\'' +
                '}';
    }
}
