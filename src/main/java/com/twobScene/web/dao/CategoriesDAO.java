package com.twobScene.web.dao;

import java.util.List;

import com.twobScene.web.model.Categories;

public interface CategoriesDAO {
	
	Categories addCategory(Categories categories);
	Categories deleteCategory(Long id);
	List<Categories> listCategories();
	Categories updateCategory(Categories categories);
	Categories getCategoryById(Long idCat);

}
