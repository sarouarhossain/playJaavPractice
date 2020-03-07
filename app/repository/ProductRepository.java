package repository;

import com.google.inject.ImplementedBy;
import models.Product;

import java.util.List;
import java.util.concurrent.CompletionStage;

@ImplementedBy(JPAProductRepositoryImpl.class)
public interface ProductRepository {
    public CompletionStage<List<Product>> get();
}
