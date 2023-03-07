package uz.unicorn.deeplearning.excel;

import com.poiji.bind.Poiji;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

/**
 * @author Doston Bokhodirov on 07 March 2023 at 8:11 PM
 */

@Service
public class ExcelFileService {

    public List<ExcelFileDTO> data2ExcelFile() {
        File file = new File("D:\\Programming\\Java\\Projects\\deep-learning\\file.xlsx");
        return Poiji.fromExcel(file, ExcelFileDTO.class);
    }
}
