package ir.hamed.socialnetwork.repository.neo4j;

import java.util.ArrayList;
import java.util.List;

public class PathModel {
    private List<String> pathList = new ArrayList<>();

    public PathModel() {

    }


    public PathModel(List<String> pathList) {
        this.pathList = pathList;
    }

    public void setNewPath(String path){
        pathList.add(path);
    }

    public List<String> getPathList() {
        return pathList;
    }

    public void setPathList(List<String> pathList) {
        this.pathList = pathList;
    }

}
