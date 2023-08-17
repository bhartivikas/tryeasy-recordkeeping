package in.tryeasy.recordkeeping.service;

import in.tryeasy.recordkeeping.repository.model.ProductUploadResponse;
import org.springframework.web.multipart.MultipartFile;

public interface ProductService {

    ProductUploadResponse uploadProduct(MultipartFile multipartFile);

}
