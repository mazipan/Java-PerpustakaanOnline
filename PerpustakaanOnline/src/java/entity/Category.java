/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Administrator
 */
public class Category {

    private final Category category;
    private Integer idCategory;
    private String categoryName;
    private GeneralModel gmCategory;

    public Category() {
        category = this;
    }

    public GeneralModel getGmCategory() {
        gmCategory = new GeneralModel(category.getIdCategory(), category.getCategoryName(), "Category", category);

        return gmCategory;
    }

    public Integer getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Integer idCategory) {
        this.idCategory = idCategory;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "Category[idCategory=" + idCategory + ",categoryName=" + categoryName + "]";
    }

}
