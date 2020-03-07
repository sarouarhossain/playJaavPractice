package controllers;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import repository.ProductRepository;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

public class ProductController extends Controller {
    private ProductRepository productRepository;

    @Inject
    ProductController(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public CompletionStage<Result> getProducts(Http.Request request){
        var res = productRepository.get();
        return res.thenApply(products -> ok(Json.toJson(products)));
    }
}
