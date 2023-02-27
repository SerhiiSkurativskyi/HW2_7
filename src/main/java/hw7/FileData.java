package hw7;

public record FileData (String name, int fileSize, String path){
    @Override
    public int fileSize() {
        return fileSize;
    }
}
