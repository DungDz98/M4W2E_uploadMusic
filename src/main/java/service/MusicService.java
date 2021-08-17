package service;

import model.Music;

import java.util.ArrayList;
import java.util.List;

public class MusicService {
    public List<Music> album = new ArrayList<>();
    public List<String> types = new ArrayList<>();
    public MusicService() {
        types.add("Pop");
        types.add("Rap");
        types.add("RnB");
    }

    public void save(Music music) {
        album.add(music);
    }
    public void delete(int index) {
        album.remove(index);
    }
}
