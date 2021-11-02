package uz.pdp.codingbatapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.codingbatapi.entity.Category;
import uz.pdp.codingbatapi.payload.ApiResponse;
import uz.pdp.codingbatapi.payload.CategoryDto;
import uz.pdp.codingbatapi.repository.CategoryRepository;
import uz.pdp.codingbatapi.repository.LanguageRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    LanguageRepository languageRepository;

    public List<Category> getCategory(){
        List<Category> categories = categoryRepository.findAll();
        return categories;
    }

    public Category getCategoryById(Integer id){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        return optionalCategory.orElse(null);
    }

    public ApiResponse addCategory(CategoryDto categoryDto){
        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setLanguagesId(languageRepository.getById(categoryDto.getLanguagesId()));
        categoryRepository.save(category);
        return new ApiResponse("Category saqlandi!",true);
    }

    public ApiResponse editCategory(Integer id, CategoryDto categoryDto){
        boolean existsByNameAndIdNot = categoryRepository.existsByNameAndIdNot(categoryDto.getName(), id);
        if (existsByNameAndIdNot){
            return new ApiResponse("Bunday idlik category mavjud emas!",false);
        }
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent()){
            return new ApiResponse("Bunday idlik category mavjud!",false);
        }
        Category category = optionalCategory.get();
        category.setName(categoryDto.getName());
        category.setLanguagesId(languageRepository.getById(categoryDto.getLanguagesId()));
        categoryRepository.save(category);
        return new ApiResponse("Category tahrirlandi",true);
    }

    public ApiResponse deleteCategory(Integer id){
        categoryRepository.deleteById(id);
        return new ApiResponse("Category ochirildi!",true);
    }
}
