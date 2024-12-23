package org.example.customerrepositorymanagement.formatter;
import org.example.customerrepositorymanagement.model.Province;
import org.example.customerrepositorymanagement.service.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Optional;

@Component
public class ProvinceFormatter implements Formatter<Province> {
    //Giao diện Formatter cung cấp các phương thức để chuyển đổi dữ liệu giữa dạng chuỗi (String) và đối tượng (Province).

    private final IProvinceService provinceService;

    @Autowired
    public ProvinceFormatter(IProvinceService provinceService) {
        this.provinceService = provinceService;
    }

    @Override
    public Province parse(String text, Locale locale) {  //Chuyển một chuỗi (dạng String) thành một đối tượng Province.
        Optional<Province> provinceOptional = provinceService.findById(Long.parseLong(text));
        //Sử dụng Long.parseLong(text) để chuyển đổi chuỗi thành số (kiểu Long).
        //Gọi phương thức findById của IProvinceService để tìm Province tương ứng trong cơ sở dữ liệu hoặc kho dữ liệu.
        return provinceOptional.orElse(null);
        //Nếu tìm thấy Province, nó sẽ được trả về.
        //Nếu không tìm thấy, trả về null.
    }

//Giải thich parse
//    Nếu trong form, một trường tỉnh (Province) là một dropdown chọn tỉnh, khi người dùng chọn tỉnh, một ID tỉnh sẽ được gửi (ví dụ: 3).
//    Spring sẽ sử dụng phương thức parse để tìm Province có ID là 3 từ cơ sở dữ liệu và trả về đối tượng Province tương ứng.

    @Override
    public String print(Province object, Locale locale) { //Chuyển một đối tượng Province thành chuỗi để hiển thị.
        return "[" + object.getId() + ", " +object.getName() + "]";
    }
}