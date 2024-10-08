package com.stdbank.assessment.resource;

import com.stdbank.assessment.model.Product;
import com.stdbank.assessment.service.ProductService;
import jakarta.inject.Inject;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

public class ProductResource {


    private final ProductService productService;

    @Inject
    public ProductResource(ProductService productService){
        this.productService = productService;
    }
    @POST
    @Path("/createProduct")
    public Response createProduct(Product Product) {
        productService.addProduct(new Product());
        return Response.status(Response.Status.CREATED).entity(Product).build();
    }

    @GET
    @Path("/getProduct/{id}")
    public Product getProduct(@PathParam("id") String id) {
        return productService.findProductById(Integer.parseInt(id));
    }
    @GET
    @Path("/getProducts")
    public List<Product> getProduct() {
        return productService.getAllProducts();
    }

    @DELETE
    @Path("/deleteProduct/{id}")
    public Response deleteProduct(@PathParam("id") String id) {
        Product product = productService.findProductById(Integer.parseInt(id));
        productService.deleteProduct(product);
        return Response.noContent().build();
    }

    @PUT
    @Path("/updateProduct/{id}")
    public Response updateProduct(@PathParam("id") String id, Product product) {
        Product existingProduct = productService.findProductById(Integer.parseInt(id));
        if (existingProduct == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        productService.updateProduct(product);
        return Response.ok(product).build();
    }

}
