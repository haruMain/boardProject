package com.example.app.controller;

import com.example.app.domain.vo.FileVO;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/file/*")
public class FileController {
    @PostMapping("/upload")
//    작성하기 페이지, 수정하기 페이지에서 /upload를 사용하게 될 것이다. 그때 파일 버튼에 change 이벤트를 줄때 Ajax를 사용하여
//    /upload로 이동하게 설정 할 것이기 때문에 postMapping을 사용한 것이다.

    public List<FileVO> upload(List<MultipartFile> upload) throws IOException {
        String rootPath = "C:/upload";
//        외부 경로로 파일 경로 설정 -> C드라이브에 upload 파일을 생성해놔야 한다.
        String uploadPath = getUploadPath();
        List<FileVO> files = new ArrayList<>();

        File uploadFullPath = new File(rootPath, uploadPath);
        if(!uploadFullPath.exists()){uploadFullPath.mkdirs();}

        for(MultipartFile multipartFile : upload){
//            화면쪽에서 여러 파일이 들어오기 때문에 하나씩 반복문에 담게 된다.
            FileVO fileVO = new FileVO();
            UUID uuid = UUID.randomUUID();
//            매 반복마다 UUID 실행
            String fileName = multipartFile.getOriginalFilename();
//            사용자가 업로드한 원본 파일의 이름
            String uploadFileName = uuid.toString() + "_" + fileName;
//            실제 업로드된 파일의 이름

            fileVO.setFileName(fileName);
            fileVO.setFileUuid(uuid.toString());
            fileVO.setFileUploadPath(getUploadPath());
            fileVO.setFileSize(multipartFile.getSize());

            File fullPath = new File(uploadFullPath, uploadFileName);
            multipartFile.transferTo(fullPath);

//            checkImageType
//            사용자가 업로드한 파일이 이미지인지 아닌지 검사
            if(Files.probeContentType(fullPath.toPath()).startsWith("image")){
                FileOutputStream out = new FileOutputStream(new File(uploadFullPath, "s_" + uploadFileName));
                Thumbnailator.createThumbnail(multipartFile.getInputStream(), out, 100, 100);
//                원본파일의 이름에 _s를 붙인다음 출력을하여 사이즈 설정까지 하는 쿼리

                out.close();
                fileVO.setFileImageCheck(true);
//                가져온 파일이 img인지 검사 ->jpg, png...이미지 파일들은 모두 image로 시작한다.
            }

            files.add(fileVO);
        }
        return files;
    }

    @GetMapping("/display")
    public byte[] display(String fileName) throws IOException{
        return FileCopyUtils.copyToByteArray(new File("C:/upload", fileName));
//        실제 경로에 있는 것을 Byte별로 return 한다.
    }

    @PostMapping("/delete")
    public void delete(FileVO fileVO) {
        File file = new File("C:/upload", fileVO.getFileUploadPath() + "/" + fileVO.getFileName());
//        업로드된 이미파일 삭제
        if(file.exists()){
            file.delete();
        }
        if(fileVO.isFileImageCheck()){
            file = new File("C:/upload", fileVO.getFileUploadPath() + "/s_" + fileVO.getFileName());
//            업로드된 썸네일파일 삭제
            if(file.exists()){
                file.delete();
            }
        }
    }

    @GetMapping("/download")
    public ResponseEntity<Resource> download(String fileName) throws UnsupportedEncodingException {
        Resource resource = new FileSystemResource("C:/upload/" + fileName);
//        사용자에게 전달받은 파일의 full경로를 resorce에 전달을 한다.
        HttpHeaders header = new HttpHeaders();
        String name = resource.getFilename();
//        전달받은 resorce에서 해당 경로를 지우고 실제 파일이름만 가져온다. -> 다운할때에는 UUID가 삭제되고 원본 파일이름만 나와야 하기 때문에
        name = name.substring(name.indexOf("_") + 1);
        header.add("Content-Disposition", "attachment;filename=" + new String(name.getBytes(), "UTF-8"));
        return new ResponseEntity<>(resource, header, HttpStatus.OK);
    }

    private String getUploadPath(){
//        rooPath에 업로드 될때 년/월/일 붙여주기
        return new SimpleDateFormat("yyyy/MM/dd").format(new Date());
    }

}





















