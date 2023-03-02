package hw7;

import java.util.*;

public class FileNavigator {
    private final Map<String, List<FileData>> filesMap = new HashMap<>();

    public Map<String, List<FileData>> getFilesMap() {
        return filesMap;
    }

    public void add(FileData file) {
        List<FileData> unitedFiles;
        String path = file.path();
        if (!filesMap.containsKey(file.path())) {
            filesMap.put(file.path(), Collections.singletonList(file));
        } else {
            unitedFiles = new ArrayList<>(filesMap.get(file.path()));
            unitedFiles.add(file);
            filesMap.put(file.path(), unitedFiles);
        }
        if (!path.equals(filesMap.get(path).get(0).path())) {
            throw new InconsistentPathException("Cannot add file with path " + path +
                    " to directory " + filesMap.get(path).get(0).path());
        }
    }

    public List<FileData> find(String path) {
        return filesMap.get(path);
    }

    public List<FileData> filterBySize(int maxSize) {
        List<FileData> filesBySize = new ArrayList<>();
        for (Map.Entry<String, List<FileData>> getPosition : filesMap.entrySet()) {
            List<FileData> valueList = getPosition.getValue();
            for (FileData item : valueList) {
                if (item.fileSize() < maxSize) {
                    filesBySize.add(item);
                }
            }
        }
        return filesBySize;
    }

    public void remove(String path) {
        filesMap.remove(path);
    }

    public List<FileData> sortBySize() {
        List<FileData> filesSortedBySize = new LinkedList<>();
        for (List<FileData> fileList : filesMap.values()) {
            filesSortedBySize.addAll(fileList);
        }
        filesSortedBySize.sort(Comparator.comparingLong(FileData::fileSize));
        return filesSortedBySize;
    }
}
