package uz.unicorn.deeplearning.excel;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.unicorn.deeplearning.response.ResponseEntity;

import java.util.List;

/**
 * @author Doston Bokhodirov on 07 March 2023 at 8:21 PM
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/excel")
public class ExcelFileController {

    private final ExcelFileService service;

    @GetMapping("/data")
    public ResponseEntity<List<ExcelFileDTO>> getDataFromExcelFile() {
        return ResponseEntity.ok(service.data2ExcelFile());
    }
}
