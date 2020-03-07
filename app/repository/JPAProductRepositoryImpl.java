package repository;

import models.Product;
import play.db.jpa.JPAApi;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;
import static java.util.concurrent.CompletableFuture.supplyAsync;


public class JPAProductRepositoryImpl implements ProductRepository{
    private final JPAApi jpaApi;
    private final DatabaseExecutionContext databaseExecutionContext;

    @Inject
    JPAProductRepositoryImpl(JPAApi jpaApi,DatabaseExecutionContext executionContext){
        this.jpaApi = jpaApi;
        this.databaseExecutionContext = executionContext;
    }

    private <T> T wrap(Function<EntityManager, T> function){
        return jpaApi.withTransaction(function);
    }

    @Override
    public CompletionStage<List<Product>> get(){
        return supplyAsync(() -> wrap(this::getList),this.databaseExecutionContext);
    }

    public List<Product> getList(EntityManager em){
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Product> query = builder.createQuery(Product.class);
        Root<Product> root = query.from(Product.class);
        query.select(root);
        return em.createQuery(query).getResultList();
    }
}
