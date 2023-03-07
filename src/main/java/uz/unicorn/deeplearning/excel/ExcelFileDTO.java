package uz.unicorn.deeplearning.excel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.poiji.annotation.ExcelCellName;
import lombok.*;

/**
 * @author Doston Bokhodirov on 07 March 2023 at 8:14 PM
 */

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExcelFileDTO {

    @ExcelCellName("id")
    private Long id;

    @ExcelCellName("first_name")
    private String firstName;

    @ExcelCellName("last_name")
    private String lastName;

    @ExcelCellName("phone")
    private String phone;

    @ExcelCellName("address")
    private String address;

}
