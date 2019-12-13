package micronaut.mongo.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class VendorResponse {
    private String id;

    private String name;

    private String displayName;

    private String description;

    private String customerId;

    private double multiplier;
}
