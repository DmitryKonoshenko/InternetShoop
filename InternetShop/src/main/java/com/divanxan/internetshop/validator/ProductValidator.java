package com.divanxan.internetshop.validator;

import com.divanxan.internetshop.dto.Product;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ProductValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Product.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Product product = (Product) target;

        if (product.getFile() == null || product.getFile().getOriginalFilename().equals("")) {
            errors.rejectValue("file", null, "Выбирите изображение!");
            return;
        }

        if (!(
                product.getFile().getContentType().equals("image/jpeg") ||
                        product.getFile().getContentType().equals("image/png") ||
                        product.getFile().getContentType().equals("image/gif"))) {
            errors.rejectValue("file", null, "Используйте только файлы изображения");
            return;
        }
    }
}
