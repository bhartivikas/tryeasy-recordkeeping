package in.tryeasy.recordkeeping.controller;

import in.tryeasy.recordkeeping.repository.model.ProductUploadResponse;
import in.tryeasy.recordkeeping.service.ProductService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @PostMapping(path = "/api/products/upload")
    public ProductUploadResponse uploadProduct(@RequestParam("productFile") MultipartFile multipartFile) {
        return this.productService.uploadProduct(multipartFile);
    }

}
