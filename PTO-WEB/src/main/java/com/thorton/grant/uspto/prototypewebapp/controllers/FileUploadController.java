package com.thorton.grant.uspto.prototypewebapp.controllers;
import java.io.IOException;
import java.util.stream.Collectors;

import com.thorton.grant.uspto.prototypewebapp.service.storage.StorageService;
import com.thorton.grant.uspto.prototypewebapp.service.storage.error.StorageFileNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



@Controller
public class FileUploadController {

    private final StorageService storageService;

    @Autowired
    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/application/fileUploadAttorney")
    public String listUploadedFiles(Model model) throws IOException {


        System.out.println("111111111111111111111111111111111111111111111111111111111111111");

        model.addAttribute("files", storageService.loadAll().map(
                path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                        "serveFile", path.getFileName().toString()).build().toString())
                .collect(Collectors.toList()));

        return "uploadFormBS3";
    }

    @PostMapping("/application/fileUploadAttorney")
    public String handleFileUpload(@RequestParam(name="file", required=false) MultipartFile file,
                                   RedirectAttributes redirectAttributes, Model model) {
        System.out.println("2222222222222222222222222222222222222222222222222222222");


        if(file != null){

            storageService.store(file);
            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded " + file.getOriginalFilename() + "!");

            return "redirect:/application/fileUploadAttorney";
        }
        else{
            System.out.println("file object is null ...");
        }

        model.addAttribute("message", "no file was submitted with the upload");

        return "uploadFormBS3";


    }





    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        //return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
        //"attachment; filename=\"" + file.getFilename() + "\"").body(file);

        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(file);


    }



    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }



    @RequestMapping("/showForm")
    public String showForm(){


        return "uploadFormBS3";
    }




}
