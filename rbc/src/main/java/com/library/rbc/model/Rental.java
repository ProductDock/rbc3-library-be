package com.library.rbc.model;

import com.library.rbc.model.enums.RentalStatus;
import com.library.rbc.model.enums.RentalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("rentals")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Rental {

    @Id
    private String id;
    private RentalType rentalType;
    private String userId;
    private BookRentalDetails bookRentalDetails;
    private RentalStatus rentalStatus;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
