package controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import model.Music;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import service.MusicService;

import java.io.File;
import java.io.IOException;

@Controller
public class MusicController {
    @Value("${file-upload}")
    private String fileUpload;
    private MusicService musicService = new MusicService();
    @GetMapping("/")
    public ModelAndView showAlbum() {
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("album", musicService.album);
        return modelAndView;
    }
    @GetMapping("/create")
    public ModelAndView showCreate() {
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("music", new Music());
        modelAndView.addObject("types", musicService.types);
        return modelAndView;
    }
    @GetMapping("/delete")
    public ModelAndView delete(@RequestParam int index) {
        musicService.delete(index);
        return new ModelAndView("redirect:/");
    }
    @PostMapping("/create")
    public ModelAndView create(@ModelAttribute Music music, @RequestParam MultipartFile linkUp) {
        ModelAndView modelAndView = new ModelAndView("redirect:/");
        String songName = linkUp.getOriginalFilename();
        try {
            FileCopyUtils.copy(linkUp.getBytes(), new File(fileUpload + songName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        music.setLink(fileUpload + songName);
        musicService.save(music);
        return modelAndView;
    }

}
