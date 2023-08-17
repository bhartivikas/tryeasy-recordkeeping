package in.tryeasy.recordkeeping.service.impl;

import com.opencsv.bean.CsvToBeanBuilder;
import in.tryeasy.recordkeeping.entity.Product;
import in.tryeasy.recordkeeping.repository.ProductRepository;
import in.tryeasy.recordkeeping.repository.model.ProductUploadRequest;
import in.tryeasy.recordkeeping.repository.model.ProductUploadResponse;
import in.tryeasy.recordkeeping.service.ProductService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductUploadResponse uploadProduct(MultipartFile multipartFile) {

        try {
            //step1 first we have to convert multipart file into a file reader object or its type

            InputStreamReader reader = new InputStreamReader(multipartFile.getInputStream());

            //step2 we will use csv library to convert csv row to a custom java object.
            //we will use opencsv library to convert csv row to a java object
            List<ProductUploadRequest> productUploadRequests =
                    new CsvToBeanBuilder<ProductUploadRequest>(reader)
                            .withType(ProductUploadRequest.class)
                            .build()
                            .parse();


            //step3 now we will have list of custom java object which is equal
            // to the no of rows present in csv file
            // we have to read every java object present in the list and add it
            // to another list of product entity
            List<Product> products = new ArrayList<>();
            for (ProductUploadRequest productUploadRequest : productUploadRequests) {
                Product product = new Product();
                product.setId(productUploadRequest.getId());
                product.setName(productUploadRequest.getName());
                product.setActive(productUploadRequest.getActive());
                product.setDescription(productUploadRequest.getDescription());

                products.add(product);
            }


            //step4 now we will use product repository saveAll method to save all product entity in one go
            this.productRepository.saveAll(products);
        } catch (IOException | DataAccessException exception) {
            ProductUploadResponse productUploadResponse = new ProductUploadResponse();
            productUploadResponse.setUploadStatus(ProductUploadResponse.Status.UPLOAD_FAILURE);
            return productUploadResponse;
        }


        //step5 As executing saveAll method from product entity no exception has occurred
        //therefore create a product upload response with status as upload successful.

        ProductUploadResponse productUploadResponse = new ProductUploadResponse();
        productUploadResponse.setUploadStatus(ProductUploadResponse.Status.UPLOAD_SUCCESSFUL);
        return productUploadResponse;
    }
}
